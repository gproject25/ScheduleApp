package com.scheduleapp.Repository;

import com.scheduleapp.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
        SELECT s FROM Schedule s
        WHERE (:author IS NULL OR :author = '' OR s.author = :author)
        ORDER BY s.modifiedAt DESC
        """)
    List<Schedule> findSchedules(@Param("author") String author);



}
