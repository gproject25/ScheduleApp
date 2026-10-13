package com.scheduleapp.Repository;

import com.scheduleapp.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAuthor(String author);
}
