package com.hendisantika.service;

import com.hendisantika.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
