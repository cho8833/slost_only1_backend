package com.slost_only1.slost_only1.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.AuthorizationTokenData;
import com.slost_only1.slost_only1.data.JwtPublicKeyRes;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.JwtPublicKey;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.PublicKeyRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;

@Slf4j
@Component
public class AuthorizationTokenProvider {

    private final PublicKeyRepository publicKeyRepository;

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7;    // 7일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 30;   // 30일

    private final Key key;

    public AuthorizationTokenProvider(@Value("${jwt.secret}") String secretKey, @Autowired PublicKeyRepository publicKeyRepository) {
        this.publicKeyRepository = publicKeyRepository;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthorizationTokenData generateAuthorizationTokenData(Member authentication) {

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(authentication.getId()))                     // payload "sub": "{id}"
                .setExpiration(accessTokenExpiresIn) // payload "exp": 1516239022
                .claim(AUTHORITIES_KEY, authentication.getRole())
                .signWith(key, SignatureAlgorithm.HS512)                                // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return AuthorizationTokenData.builder()
                .accessToken(accessToken)
                .id(authentication.getId())
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        MemberRole role =
                MemberRole.valueOf(claims.get(AUTHORITIES_KEY).toString());

        Long memberId = Long.valueOf(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(memberId, null, List.of(role));
    }

    public Long getSubject(String accessToken) {
        Claims claims = parseClaims(accessToken);
        String subject = claims.getSubject();
        if (subject == null) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }
        return Long.valueOf(subject);
    }

    public void validateAuthorizationToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        }
    }

    public String getAuthorizationToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        try {
            return authorizationHeader.substring(7);
        } catch (Exception e) {
            return null;
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    @Transactional
    public Claims parseClaims(AuthProvider authProvider, String idToken) {
        try {
            // DB 에서 먼저 찾아봄
            List<JwtPublicKey> keys = publicKeyRepository.findByAuthProvider(authProvider);
            String headerOfIdentityToken = idToken.substring(0, idToken.indexOf("."));
            Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);
            Optional<JwtPublicKey> matchKey = keys.stream().filter(key -> key.isMatch(header.get("kid"), header.get("alg")))
                    .findFirst();

            // DB 에서 찾아봤는데 없으면 불러옴
            if (matchKey.isEmpty()) {
                JwtPublicKeyRes res = publicKeyRepository.fetchPublicKey(authProvider);
                List<JwtPublicKey> newKeys = res.parseKeys(authProvider);
                matchKey = newKeys.stream().filter(key -> key.isMatch(header.get("kid"), header.get("alg")))
                        .findFirst();

                if (matchKey.isEmpty()) {
                    // 해당 Exception 이 발생하면 빠르게 수정 필요
                    throw new CustomException(ResponseCode.FATAL_ERROR);
                }

                // DB 갱신
                publicKeyRepository.deleteByAuthProvider(authProvider);
                publicKeyRepository.saveAll(newKeys);
            }

            JwtPublicKey publicKey = matchKey.get();
            byte[] nBytes = Base64.getUrlDecoder().decode(publicKey.getN());
            byte[] eBytes = Base64.getUrlDecoder().decode(publicKey.getE());


            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
            PublicKey key = keyFactory.generatePublic(publicKeySpec);

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(idToken).getBody();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CustomException(ResponseCode.WRONG_TOKEN);
        } catch (Exception e) {
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }
}
