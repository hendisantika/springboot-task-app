package com.hendisantika.model;

import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-task-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/09/21
 * Time: 07.46
 */
public enum Status {
    OPEN("open"),
    CLOSED("closed"),
    REOPENED("reopened");

    private final String typeOfStatus;

    Status(String typeOfStatus) {
        this.typeOfStatus = typeOfStatus;
    }

    public static Stream<Status> stream() {
        return Stream.of(Status.values());
    }

    public String getTypeOfStatus() {
        return typeOfStatus;
    }
}
