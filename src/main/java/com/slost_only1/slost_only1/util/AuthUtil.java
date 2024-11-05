package com.slost_only1.slost_only1.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public Long getLoginMemberId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
