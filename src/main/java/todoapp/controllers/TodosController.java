package todoapp.controllers;

import javafx.scene.layout.VBox;
import todoapp.modules.Todo;

import java.util.ArrayList;

public class TodosController {

    VBox todosContainer;

    public TodosController(VBox container) {
        this.todosContainer = container;
    }

    public void renderTasks(ArrayList<Todo> todosList) {

        if(todosList.size()>0) {
            todosList.forEach( (todo) -> {
                todosContainer.getChildren().add(todo.renderTask());
            });
        }
    }

}
