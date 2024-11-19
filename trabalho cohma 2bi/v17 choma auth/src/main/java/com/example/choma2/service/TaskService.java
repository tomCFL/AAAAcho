package com.example.choma2.service;

import com.example.choma2.model.Task;
import com.example.choma2.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByPriorityAndLimitDateOrdered(String priority, LocalDateTime limitDate) {
        return taskRepository.findByPriorityAndCreatedDateBeforeOrderByCreatedDateAsc(priority, limitDate);
    }

    public Task createTask(Task task) {
        task.setStatus("A Fazer");
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatusOrderedByPriority(String status) {
        return taskRepository.findByStatusOrderByPriorityAsc(status);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByStatusAscPriorityAsc();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task moveTaskToNextStatus(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            String currentStatus = task.get().getStatus();
            switch (currentStatus) {
                case "A Fazer":
                    task.get().setStatus("Em Progresso");
                    break;
                case "Em Progresso":
                    task.get().setStatus("Concluído");
                    break;
                default:
                    throw new IllegalArgumentException("Task already completed");
            }
            return taskRepository.save(task.get());
        }
        return null;
    }

    public List<Task> getReport() {

        List<Task> tasks = taskRepository.findAllByOrderByStatusAscPriorityAsc();


        LocalDateTime now = LocalDateTime.now();
        tasks.forEach(task -> {
            if (task.getCreatedDate().isBefore(now) && !task.getStatus().equals("Concluído")) {

                task.setStatus(task.getStatus() + " (Atrasada)");
            }
        });

        return tasks;
    }

}
