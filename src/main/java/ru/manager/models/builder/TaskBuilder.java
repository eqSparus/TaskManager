package ru.manager.models.builder;

import ru.manager.models.StatusTask;
import ru.manager.models.Task;

import java.time.Instant;

public class TaskBuilder {

    private Long id;
    private String title;
    private String description;
    private Instant createdAt;
    private Instant completionAt;
    private Instant frozenDay;
    private StatusTask status;
    private Long userId;

    public TaskBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TaskBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TaskBuilder completionAt(Instant completionAt) {
        this.completionAt = completionAt;
        return this;
    }

    public TaskBuilder frozenDay(Instant frozenDay) {
        this.frozenDay = frozenDay;
        return this;
    }

    public TaskBuilder status(StatusTask status) {
        this.status = status;
        return this;
    }

    public TaskBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Task build() {
        return new Task(
                id, title, description, createdAt,
                completionAt, frozenDay, status, userId
        );
    }

}
