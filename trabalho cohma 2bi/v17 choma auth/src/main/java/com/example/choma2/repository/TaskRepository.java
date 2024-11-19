package com.example.choma2.repository;

import com.example.choma2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatusOrderByPriorityAsc(String status);

    List<Task> findAllByOrderByStatusAscPriorityAsc();

    List<Task> findByPriorityAndCreatedDateBeforeOrderByCreatedDateAsc(String priority, LocalDateTime limitDate);

}

