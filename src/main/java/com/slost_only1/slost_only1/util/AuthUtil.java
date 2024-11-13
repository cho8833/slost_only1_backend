package com.slost_only1.slost_only1.util;

import com.slost_only1.slost_only1.enums.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthUtil {

    public Long getLoginMemberId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public MemberRole getMemberRole() {
        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return (MemberRole) roles.stream().findFirst().orElseThrow();

    }
}
