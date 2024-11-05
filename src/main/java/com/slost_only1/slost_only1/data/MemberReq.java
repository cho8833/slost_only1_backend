package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberReq {
    private Role role;
}
