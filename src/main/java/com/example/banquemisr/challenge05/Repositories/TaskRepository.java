package com.example.banquemisr.challenge05.Repositories;

import com.example.banquemisr.challenge05.models.Task;
import com.example.banquemisr.challenge05.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t " +
            "WHERE (:title IS NULL OR t.title LIKE %:title%) " +
            "OR (:description IS NULL OR t.description LIKE %:description%) " +
            "OR (:status IS NULL OR t.status = :status) " +
            "OR (:dueDate IS NULL OR t.dueDate = :dueDate)")
    List<Task> searchTasks(
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") TaskStatus status,
            @Param("dueDate") LocalDateTime dueDate
    );
}



