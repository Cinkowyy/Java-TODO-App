package todoapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import todoapp.modules.AuthKey;
import todoapp.modules.LoginErrorMessage;
import todoapp.modules.Todo;
import todoapp.modules.TodosGetter;

import java.util.ArrayList;

public class MainController {

    private final AuthKey key;
    public String filter;

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
    private Text errorMessageField;

    public MainController(AuthKey authKey) {
        this.key =authKey;
        this.filter = "All";
        System.out.println("MainController started with key:");
        System.out.println(key.getKey());
    }

    @FXML
    void initialize() {

        LoginErrorMessage todosMessageController = new LoginErrorMessage(errorMessageField);

        ArrayList<Todo> todosList = TodosGetter.getTodos(this.key, todosMessageController);

        TodosController todosController = new TodosController(this.todosContainer, todosList);

        todoInput.setOnKeyPressed(keyEvent -> {
           if(keyEvent.getCode() == KeyCode.ENTER) {
               if(todoInput.getText().length()>0 && todoInput.getText().length()<64) {
                   todosMessageController.removeMessage();
                   Todo todo = new Todo(3, todoInput.getText(), false);
                   todoInput.setText("");
                   todosList.add(todo);
                   todosController.renderTasks(filter);
               } else {
                   todosMessageController.setMessage("Empty field or too many characters(max 64)");
               }
           }
        });

        allFilter.setOnMouseClicked(mouseEvent -> {
            filter = "All";
            todosController.renderTasks(filter);
            clearActiveClass();
            allFilter.getStyleClass().add("active");
        });

        activeFilter.setOnMouseClicked(mouseEvent -> {
            filter = "Active";
            todosController.renderTasks(filter);
            clearActiveClass();
            activeFilter.getStyleClass().add("active");
        });

        completedFilter.setOnMouseClicked(mouseEvent -> {
            filter = "Completed";
            todosController.renderTasks(filter);
            clearActiveClass();
            completedFilter.getStyleClass().add("active");
        });

        todosController.renderTasks(filter);

    }

    void clearActiveClass() {
        allFilter.getStyleClass().remove("active");
        activeFilter.getStyleClass().remove("active");
        completedFilter.getStyleClass().remove("active");
    }

}
