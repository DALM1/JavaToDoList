package com.example;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class SeleniumTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        new ToDoApp().start(stage);
    }

    @Test
    public void testAddTask() {
        clickOn(".text-field").write("Ma première tâche");

        clickOn(".button").lookup(hasText("Ajouter")).queryButton().fire();

        verifyThat(".list-view", (ListView<String> listView) ->
            listView.getItems().contains("Ma première tâche (non terminée)"));
    }

    @Test
    public void testMarkTaskAsCompleted() {
        clickOn(".text-field").write("Une tâche à terminer");
        clickOn(".button").lookup(hasText("Ajouter")).queryButton().fire();

        clickOn(".list-view").clickOn("Une tâche à terminer (non terminée)");

        clickOn(".button").lookup(hasText("Marquer comme terminée")).queryButton().fire();

        verifyThat(".list-view", (ListView<String> listView) ->
            listView.getItems().contains("Une tâche à terminer (terminée)"));
    }
}
