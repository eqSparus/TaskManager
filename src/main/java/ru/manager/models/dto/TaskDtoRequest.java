package ru.manager.models.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class TaskDtoRequest {

    private final String title;
    private final String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private final Instant createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private final Instant completionAt;

    @JsonCreator
    public TaskDtoRequest(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("createAt") Instant createAt,
            @JsonProperty("completionAt") Instant completionAt
    ) {
        this.title = title;
        this.description = description;
        this.createAt = createAt;
        this.completionAt = completionAt;
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

    @Override
    public String toString() {
        return "TaskDtoRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createAt=" + createAt +
                ", completionAt=" + completionAt +
                '}';
    }
}
