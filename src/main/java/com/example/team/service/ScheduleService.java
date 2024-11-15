package com.example.team.service;

import com.example.team.model.Schedule;
import com.example.team.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public String addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return "Schedule added successfully!";
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public String updateSchedule(Long id, Schedule updatedSchedule) {
        if (scheduleRepository.existsById(id)) {
            updatedSchedule.setScheduleId(id);
            scheduleRepository.save(updatedSchedule);
            return "Schedule updated successfully!";
        }
        return "Schedule not found!";
    }

    public String deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return "Schedule deleted successfully!";
        }
        return "Schedule not found!";
    }
}
