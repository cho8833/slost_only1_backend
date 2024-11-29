package com.slost_only1.slost_only1.data;


public record ChatRoomRes(
        Long id,
        String name,
        String sendbirdChannelUrl,
        Long teacherId,
        Long parentId) {
}
