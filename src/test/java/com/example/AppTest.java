package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testTaskCompletion() {
        Task task = new Task("Test Task");
        assertFalse(task.isCompleted(), "La tâche ne devrait pas être terminée au départ");
        task.setCompleted(true);
        assertTrue(task.isCompleted(), "La tâche devrait être marquée comme terminée");
    }
}
