package ru.manager.models.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dto класс задачи для тела запроса.
 */
public class TaskDtoRequest {

    private final String title;
    private final String description;
    private final Long createAt;
    private final Long completionAt;

    @JsonCreator
    public TaskDtoRequest(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("createAt") Long createAt,
            @JsonProperty("completionAt") Long completionAt
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

    public Long getCreateAt() {
        return createAt;
    }

    public Long getCompletionAt() {
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
