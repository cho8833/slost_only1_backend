package com.slost_only1.slost_only1.data;

import java.util.List;

public record SendbirdCreateGroupChannelRes
        (String name,
         String channel_url,
         String cover_url,
         String custom_type,
         Integer unread_message_count,
         String data,
         Boolean is_distinct,
         Boolean is_public,
         Boolean is_super,
         Boolean is_ephemeral,
         Boolean is_access_code_required,
         Integer member_count,
         Integer joined_member_count,
         Integer unread_mention_count,
         CreatedBy created_by,
         List<Member> members,
         List<Operator> operators,
         Object last_message,
         Integer message_survival_seconds,
         Integer max_length_message,
         Integer created_at,
         Boolean freeze) {

    public record CreatedBy(
            String user_id,
            String nickname,
            String profile_url,
            Boolean require_auth_for_profile_image
    ) {
    }

    public record Member
            (String user_id,
             String nickname,
             String profile_url,
             Boolean is_active,
             Boolean is_online,
             Object last_seen_at,
             String state,
             String role,
             Metadata metadata) {
    }

    public record Metadata(
            String font_preference,
            String font_color) {
    }

    public record Operator(
            String user_id,
            String nickname,
            String profile_url,
            Boolean is_active,
            Boolean is_online,
            Integer last_seen_at,
            String state,
            Metadata metadata) {
    }
}
