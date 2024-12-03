package com.example.banquemisr.challenge05.Services;

import com.example.banquemisr.challenge05.Repositories.TaskRepository;
import com.example.banquemisr.challenge05.models.Task;
import com.example.banquemisr.challenge05.models.TaskStatus;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

    }


    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public List<Task> searchTasks(String title, String description, TaskStatus status, LocalDateTime dueDate) {
        return taskRepository.searchTasks(
                title != null ? title : null,
                description != null ? description : null,
                status,
                dueDate
        );
    }
}
