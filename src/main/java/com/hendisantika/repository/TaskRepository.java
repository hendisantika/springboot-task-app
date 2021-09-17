package com.hendisantika.repository;

import com.hendisantika.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-task-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/09/21
 * Time: 07.49
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
