package ru.manager.models.builder;

import ru.manager.models.StatusTask;
import ru.manager.models.dto.TaskDtoResponse;

public class TaskDtoResponseBuilder {

    private Long id;
    private String title;
    private String description;
    private long createAt;
    private long completionAt;
    private StatusTask status;

    public TaskDtoResponseBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TaskDtoResponseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TaskDtoResponseBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskDtoResponseBuilder createAt(long createAt) {
        this.createAt = createAt;
        return this;
    }

    public TaskDtoResponseBuilder completionAt(long completionAt) {
        this.completionAt = completionAt;
        return this;
    }

    public TaskDtoResponseBuilder status(StatusTask status) {
        this.status = status;
        return this;
    }

    public TaskDtoResponse build() {
        return new TaskDtoResponse(
                id, title, description,
                createAt, completionAt, status
        );
    }

}
