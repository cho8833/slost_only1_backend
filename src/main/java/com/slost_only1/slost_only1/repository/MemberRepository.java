package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.username = ?1 and m.password = ?2")
    Optional<Member> findByUsernameAndPassword(String username, String password);

    @Query("select m from Member m where m.username = ?1")
    List<Member> findByUsername(String username);


}
