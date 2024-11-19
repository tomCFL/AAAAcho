package com.example.choma2.controller;

import com.example.choma2.model.Task;
import com.example.choma2.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        task.setStatus("A Fazer");
        return taskService.updateTask(task);
    }



    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


    @PutMapping("/{id}/move")
    public Task moveTaskToNextStatus(@PathVariable Long id) {
        return taskService.moveTaskToNextStatus(id);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.getTasksByStatusOrderedByPriority(status);
    }

    @GetMapping("/priority/{priority}/limitDate/{limitDate}")
    public List<Task> getTasksByPriorityAndLimitDate(
            @PathVariable String priority,
            @PathVariable String limitDate
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SSSSSSS]");
        LocalDateTime date = LocalDateTime.parse(limitDate, formatter);
        return taskService.getTasksByPriorityAndLimitDateOrdered(priority, date);
    }

    @GetMapping("/report")
    public List<Task> getTaskReport() {
        return taskService.getReport();
    }

}
