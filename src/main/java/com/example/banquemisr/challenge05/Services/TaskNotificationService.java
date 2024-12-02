package com.example.banquemisr.challenge05.Services;
import com.example.banquemisr.challenge05.models.Task;
import com.example.banquemisr.challenge05.Repositories.TaskRepository;
import com.example.banquemisr.challenge05.models.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskNotificationService {

    private final TaskRepository taskRepository;
    private final EmailService emailService;

    public TaskNotificationService(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void sendTaskNotifications() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate now = LocalDate.now();

        for (Task task : tasks) {
            if (task.getDueDate() != null && task.getDueDate().isBefore(now.plusDays(1))) {
                User assignedUser = task.getUser();
                if (assignedUser != null && assignedUser.getEmail() != null) {
                    emailService.sendEmail(
                            assignedUser.getEmail(),
                            "Task Deadline Reminder: " + task.getTitle(),
                            "The task \"" + task.getTitle() + "\" is due on " + task.getDueDate() + ". Please take action."
                    );
                }
            }
        }
    }
}

