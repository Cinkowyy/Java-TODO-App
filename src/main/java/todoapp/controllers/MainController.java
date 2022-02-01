package todoapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import todoapp.modules.AuthKey;
import todoapp.modules.Todo;

public class MainController {

    @FXML
    private VBox todosContainer;

    public MainController(AuthKey authKey) {
        System.out.println("MainController started with key:");
        System.out.println(authKey.getKey());
    }

    @FXML
    void initialize() {
        Todo todo = new Todo(2, "Make this shit work", 1);
        todosContainer.getChildren().add(todo.renderTask());
    }

}
