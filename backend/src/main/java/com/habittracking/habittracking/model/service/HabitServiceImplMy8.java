package com.habittracking.habittracking.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.dto.HabitRegisterDto;
import com.habittracking.habittracking.model.entities.Habit;
import com.habittracking.habittracking.model.entities.User;
import com.habittracking.habittracking.repository.HabitRepository;

@Service
public class HabitServiceImplMy8 implements HabitService {

    @Autowired
    HabitRepository hrepo;
    
    @Autowired
    UserService userService;

    @Override
    public List<Habit> findByUserId(Long userId) {
        try {
            return hrepo.findByUserId(userId);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public int delete(Long habitId) {
        try {
            if(findById(habitId) == null){
                return 0; // no existe
            }
            hrepo.delete(findById(habitId));
            return 1; // exito
        } catch (Exception e) {
            return -1; // excepcion
        }
    }

    @Override
    public Habit create(HabitRegisterDto habitDto, Long userId) {
        try {
            User user = userService.findOne(userId);
            if (user == null) {
                return null; // usuario no existe
            }
            Habit habit = registerDtoToHabit(habitDto, user);
            return hrepo.save(habit);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    /**
     * Convierte un HabitRegisterDto a una entidad Habit
     */
    private Habit registerDtoToHabit(HabitRegisterDto dto, User user) {
        Habit habit = new Habit();
        habit.setNombre(dto.getNombre());
        habit.setDescripcion(dto.getDescripcion());
        habit.setFrecuencia(dto.getFrecuencia());
        habit.setUser(user);
        habit.setFechaCreacion(LocalDate.now());
        return habit;
    }

    @Override
    public Habit update(Habit habit) {
        try {
            return hrepo.save(habit);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Habit findById(Long habitId) {
        return hrepo.findById(habitId).orElse(null);
    }

    @Override
    public List<Habit> findAll() {
        return hrepo.findAll();
    }
}
