package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.AuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtPublicKey extends BaseEntity {

    @Column
    private String kty;

    @Column
    private String kid;

    @Column
    private String alg;

    @Column(length = 1024)
    private String n;

    @Column
    private String e;

    @Column
    private AuthProvider authProvider;

    public boolean isMatch(String kid, String alg) {
        return this.alg.equals(alg) && this.kid.equals(kid);
    }
}
