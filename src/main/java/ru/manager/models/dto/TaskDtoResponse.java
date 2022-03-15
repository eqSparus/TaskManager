package ru.manager.models.dto;

import ru.manager.models.StatusTask;

import java.time.Instant;

public class TaskDtoResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final Instant createAt;
    private final Instant completionAt;
    private final StatusTask status;


    public TaskDtoResponse(Long id, String title, String description,
                           Instant createAt, Instant completionAt, StatusTask status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createAt = createAt;
        this.completionAt = completionAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getCompletionAt() {
        return completionAt;
    }

    public StatusTask getStatus() {
        return status;
    }
}
