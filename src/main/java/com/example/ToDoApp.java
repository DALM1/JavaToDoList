package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ToDoApp extends Application {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-spacing: 10;");

        ListView<String> taskListView = new ListView<>();
        taskListView.setPrefHeight(200);

        TextField taskInput = new TextField();
        taskInput.setPromptText("Entrez une nouvelle tâche");

        Button addButton = new Button("Ajouter");
        addButton.setOnAction(event -> {
            String title = taskInput.getText().trim();
            if (!title.isEmpty()) {
                tasks.add(new Task(title));
                updateTaskList(taskListView);
                taskInput.clear();
            } else {
                showAlert("Erreur", "Le titre de la tâche ne peut pas être vide.");
            }
        });

        Button markAsCompletedButton = new Button("Marquer comme terminée");
        markAsCompletedButton.setOnAction(event -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                tasks.get(selectedIndex).setCompleted(true);
                updateTaskList(taskListView);
            } else {
                showAlert("Erreur", "Veuillez sélectionner une tâche à marquer comme terminée.");
            }
        });

        layout.getChildren().addAll(
            taskListView,
            new HBox(10, taskInput, addButton),
            markAsCompletedButton
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
