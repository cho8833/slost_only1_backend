package com.slost_only1.slost_only1.data.req;


import com.slost_only1.slost_only1.data.KakaoOAuthToken;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInReq {

    String phoneNumber;

    AuthProvider authProvider;

    MemberRole role;

    KakaoOAuthToken kakaoToken;

}
