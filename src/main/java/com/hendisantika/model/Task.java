package com.hendisantika.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-task-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/09/21
 * Time: 07.47
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique id
    @Column(name = "task_id")   //database column name
    private Long id;

    //title may not be empty
    //otherwise will cause error after form submission
    @NotEmpty
    private String title;

    @Lob    //long object notation for texts
    @NotEmpty
    @Type(type = "org.hibernate.type.TextType") //heroku config
    private String content;

    //status may not be null
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Task() {
        this.status = Status.OPEN;
    }

    public void closeTask() {
        this.status = Status.CLOSED;
    }

    public void reopenTask() {
        this.status = Status.REOPENED;
    }
}
