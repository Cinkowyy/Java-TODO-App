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
    private Text numberOfItems;

    @FXML
    private Text allFilter;

    @FXML
    private Text activeFilter;

    @FXML
    private Text completedFilter;

    @FXML
    private Text clearOption;

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

        TodosController todosController = new TodosController(this.todosContainer, todosList);
        todosController.renderTasks("All");

        allFilter.setOnMouseClicked(mouseEvent -> {
            todosController.renderTasks("All");
            clearActiveClass();
            allFilter.getStyleClass().add("active");
        });

        activeFilter.setOnMouseClicked(mouseEvent -> {
            todosController.renderTasks("Active");
            clearActiveClass();
            activeFilter.getStyleClass().add("active");
        });

        completedFilter.setOnMouseClicked(mouseEvent -> {
            todosController.renderTasks("Completed");
            clearActiveClass();
            completedFilter.getStyleClass().add("active");
        });

    }

    void clearActiveClass() {
        allFilter.getStyleClass().remove("active");
        activeFilter.getStyleClass().remove("active");
        completedFilter.getStyleClass().remove("active");
    }

}
