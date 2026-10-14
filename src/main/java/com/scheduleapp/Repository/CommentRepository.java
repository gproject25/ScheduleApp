package com.scheduleapp.Repository;

import com.scheduleapp.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.scheduleId = :scheduleId")
    long countComment(@Param("scheduleId") Long scheduleId);

    List<Comment> findByScheduleId(Long scheduleId);

    void deleteByScheduleId(Long scheduleId);
}
