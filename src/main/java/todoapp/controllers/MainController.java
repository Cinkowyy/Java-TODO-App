package todoapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import todoapp.modules.AuthKey;
import todoapp.modules.LoginErrorMessage;
import todoapp.modules.Todo;

public class MainController {

    @FXML
    private TextField todoInput;

    @FXML
    private VBox todosContainer;

    @FXML
    private Text errorMessage;

    public MainController(AuthKey authKey) {
        System.out.println("MainController started with key:");
        System.out.println(authKey.getKey());
    }

    @FXML
    void initialize() {

        LoginErrorMessage loginMessageController = new LoginErrorMessage(errorMessage);

        Todo todo = new Todo(2, "Make this shit work", 1);
        todosContainer.getChildren().add(todo.renderTask());

    }

}
