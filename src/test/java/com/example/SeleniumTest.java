import com.example.ToDoApp;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

public class SeleniumTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        new ToDoApp().start(stage);
    }

    @Test
    public void testAddTask() {
        try {
            Thread.sleep(5000);

            verifyThat("#task-input", Node::isVisible);
            verifyThat("#add-button", Node::isVisible);

            Thread.sleep(500);
            clickOn("#task-input").write("Nouvelle tâche");
            Thread.sleep(500);
            clickOn("#add-button");

            Thread.sleep(500);
            ListView<String> listView = lookup("#task-list").queryAs(ListView.class);
            assert listView.getItems().contains("Nouvelle tâche (non terminée)");
        } catch (Exception e) {
            System.err.println("Error in testAddTask: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testMarkTaskAsCompleted() {
        try {
            Thread.sleep(500);

            clickOn("#task-input").write("Tâche à compléter");
            Thread.sleep(500);
            clickOn("#add-button");

            ListView<String> listView = lookup("#task-list").queryAs(ListView.class);
            clickOn("#task-list").clickOn("Tâche à compléter (non terminée)");

            clickOn("#complete-button");

            Thread.sleep(500);
            assert listView.getItems().contains("Tâche à compléter (terminée)");
        } catch (Exception e) {
            System.err.println("Error in testMarkTaskAsCompleted: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
