package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByParent_IdAndTeacher_Id(Long parentId, Long teacherId);

}
