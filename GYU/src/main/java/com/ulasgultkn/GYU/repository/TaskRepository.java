package com.ulasgultkn.GYU.repository;

import com.ulasgultkn.GYU.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);// select * from Task where user_id = ?;
}