package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ToDoApp extends Application {

    private final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-spacing: 10;");

        ListView<String> taskListView = new ListView<>();
        taskListView.setId("task-list");
        taskListView.setPrefHeight(200);

        TextField taskInput = new TextField();
        taskInput.setId("task-input");
        taskInput.setPromptText("Entrez une nouvelle tâche");

        Button addButton = new Button("Ajouter");
        addButton.setId("add-button");
        addButton.setOnAction(event -> {
            String title = taskInput.getText().trim();
            if (!title.isEmpty()) {
                tasks.add(new Task(title));
                updateTaskList(taskListView);
                taskInput.clear();
            }
        });

        Button completeButton = new Button("Marquer comme terminée");
        completeButton.setId("complete-button");
        completeButton.setOnAction(event -> {
            String selectedItem = taskListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int index = taskListView.getSelectionModel().getSelectedIndex();
                tasks.get(index).setCompleted(true);
                updateTaskList(taskListView);
            }
        });

        layout.getChildren().addAll(
            taskListView,
            new HBox(10, taskInput, addButton),
            completeButton
        );

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTaskList(ListView<String> taskListView) {
        taskListView.getItems().clear();
        for (Task task : tasks) {
            taskListView.getItems().add(task.toString());
        }
    }
}
