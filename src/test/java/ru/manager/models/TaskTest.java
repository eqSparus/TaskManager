package ru.manager.models;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void builderTest() {

        var time = Instant.now();

        var task = Task.builder()
                .id(1L)
                .title("Title")
                .description("Description")
                .status(StatusTask.ACTIVE)
                .userId(2L)
                .frozenDay(time)
                .createdAt(Instant.MIN)
                .completionAt(Instant.MAX)
                .build();

        assertEquals(1L, task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(StatusTask.ACTIVE, task.getStatus());
        assertEquals(2L, task.getUserId());
        assertEquals(time, task.getFrozenDay());
        assertEquals(Instant.MIN, task.getCreatedAt());
        assertEquals(Instant.MAX, task.getCompletionAt());

    }

}
