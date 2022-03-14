package ru.manager.models;

import java.time.Instant;
import java.util.Objects;

/**
 * Модель задач
 */
public class Task {

    private Long id;

    private String title;

    private String description;

    private Instant createdAt;

    private Instant completionAt;

    private Instant frozenDay;

    private StatusTask status;

    private Long userId;

    public Task(Long id, String title, String description,
                Instant createdAt, Instant completionAt,
                Instant frozenDay, StatusTask status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.completionAt = completionAt;
        this.frozenDay = frozenDay;
        this.status = status;
        this.userId = userId;
    }

    public Task(String title, String description, Instant createdAt,
                Instant completionAt, Instant frozenDay,
                StatusTask status, Long userId) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.completionAt = completionAt;
        this.frozenDay = frozenDay;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getCompletionAt() {
        return completionAt;
    }

    public void setCompletionAt(Instant completionAt) {
        this.completionAt = completionAt;
    }

    public Instant getFrozenDay() {
        return frozenDay;
    }

    public void setFrozenDay(Instant frozenDay) {
        this.frozenDay = frozenDay;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getCreatedAt(), task.getCreatedAt()) && Objects.equals(getCompletionAt(), task.getCompletionAt()) && Objects.equals(getFrozenDay(), task.getFrozenDay()) && getStatus() == task.getStatus() && Objects.equals(getUserId(), task.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCreatedAt(), getCompletionAt(), getFrozenDay(), getStatus(), getUserId());
    }
}
