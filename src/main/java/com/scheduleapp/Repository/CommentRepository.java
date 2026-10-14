package com.scheduleapp.Repository;

import com.scheduleapp.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.schedule.id = :scheduleId")
    long countComment(Long scheduleId);

    //List<Comment> findBySchedule_Id(Long scheduleId); 같은 동작
    @Query("SELECT c FROM Comment c WHERE c.schedule.id = :scheduleId")
    List<Comment> findByScehduleId(Long scheduleId);

    void deleteBySchedule_Id(Long scheduleId);
}
