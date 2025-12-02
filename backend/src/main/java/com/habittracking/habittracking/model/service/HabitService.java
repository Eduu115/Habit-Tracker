package com.habittracking.habittracking.model.service;

import java.util.List;

import com.habittracking.habittracking.model.dto.HabitRegisterDto;
import com.habittracking.habittracking.model.entities.Habit;

public interface HabitService {
    List<Habit> findByUserId(Long userId);
    int delete(Long habitId);
    Habit create(HabitRegisterDto habitDto, Long userId);
    Habit update(Habit habit);
    Habit findById(Long habitId);
    List<Habit> findAll();
}
