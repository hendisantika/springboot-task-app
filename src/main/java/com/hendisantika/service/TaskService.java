package com.hendisantika.service;

import com.hendisantika.model.Task;
import com.hendisantika.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-task-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/09/21
 * Time: 07.49
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * GET all tasks from DB
     *
     * @return all tasks from Database
     */
    public Set<Task> getTasks() {
        Set<Task> taskSet = new HashSet<>();
        taskRepository.findAll().iterator().forEachRemaining(taskSet::add);
        return taskSet;
    }

    /**
     * finds a task by its ID
     *
     * @param taskId Database ID of task
     * @return task
     */
    public Task findById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task Not Found!");
        }
        return taskOptional.get();
    }

    /**
     * creates new Task and saves it in Database
     *
     * @param taskDetails field values
     * @return new Task
     */
    public Task createTask(Task taskDetails) {
        Task newTask = taskRepository.save(taskDetails);
        return newTask;
    }

    /**
     * updates Task from Database with field values in taskDetails
     *
     * @param taskId      Database ID of task
     * @param taskDetails field values
     */
    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = findById(taskId);
        task.setTitle(taskDetails.getTitle());
        task.setContent(taskDetails.getContent());
        task.setStatus(taskDetails.getStatus());
        taskRepository.save(task);
        return task;
    }

    /**
     * deletes Task from Database
     *
     * @param taskId Database ID of task
     */
    public void deleteTask(Long taskId) {
        taskRepository.delete(findById(taskId));
    }

    /**
     * Sets Task.Status to CLOSED
     *
     * @param taskId Database ID of task
     */
    public void closeTask(Long taskId) {
        Task task = findById(taskId);
        task.closeTask();
        taskRepository.save(task);
    }

    /**
     * Sets Task.Status to REOPENED
     *
     * @param taskId Database ID of task
     */
    public void reopenTask(Long taskId) {
        Task task = findById(taskId);
        task.reopenTask();
        taskRepository.save(task);
    }

}
