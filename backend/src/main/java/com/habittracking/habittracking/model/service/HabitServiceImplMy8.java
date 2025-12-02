package com.habittracking.habittracking.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.dto.HabitRegisterDto;
import com.habittracking.habittracking.model.dto.HabitUpdateDto;
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

    /**
     * Convierte un HabitUpdateDto a una entidad Habit existente.
     * Solo actualiza los campos que no son null en el DTO.
     */
    private Habit updateDtoToHabit(HabitUpdateDto hudto, Long id){
        Habit habit = hrepo.findById(id).orElse(null);
        if (habit == null) {
            return null;
        }
        
        // Actualizar solo los campos que no son null (actualización parcial)
        if (hudto.getNombre() != null) {
            habit.setNombre(hudto.getNombre());
        }
        if (hudto.getDescripcion() != null) {
            habit.setDescripcion(hudto.getDescripcion());
        }
        if (hudto.getFrecuencia() != null) {
            habit.setFrecuencia(hudto.getFrecuencia());
        }
        
        return habit;
    }

    @Override
    public Habit update(HabitUpdateDto hudto, Long id) {
        try {
            return hrepo.save(updateDtoToHabit(hudto, id));
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
