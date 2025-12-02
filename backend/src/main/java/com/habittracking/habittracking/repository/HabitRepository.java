package com.habittracking.habittracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habittracking.habittracking.model.entities.Habit;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId); 
}
