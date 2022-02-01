package todoapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import todoapp.modules.AuthKey;
import todoapp.modules.LoginErrorMessage;
import todoapp.modules.Todo;
import todoapp.modules.TodosGetter;

import java.util.ArrayList;

public class MainController {

    private final AuthKey key;

    @FXML
    private TextField todoInput;

    @FXML
    private VBox todosContainer;

    @FXML
    private Text errorMessage;

    public MainController(AuthKey authKey) {
        this.key =authKey;
        System.out.println("MainController started with key:");
        System.out.println(key.getKey());
    }

    @FXML
    void initialize() {

        LoginErrorMessage loginMessageController = new LoginErrorMessage(errorMessage);

        ArrayList<Todo> todosList = TodosGetter.getTodos(this.key, loginMessageController);

        TodosController todosController = new TodosController(this.todosContainer);
        todosController.renderTasks(todosList);
    }

}
