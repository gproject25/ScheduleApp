package com.scheduleapp.Repository;

import com.scheduleapp.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.schedule.id = :scheduleId")
    long countComment(Long scheduleId);

}
