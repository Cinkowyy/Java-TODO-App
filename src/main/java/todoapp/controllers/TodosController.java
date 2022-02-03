package todoapp.controllers;

import javafx.scene.layout.VBox;
import todoapp.modules.Todo;

import java.util.ArrayList;

public class TodosController {

    VBox todosContainer;
    ArrayList<Todo> todosList;

    public TodosController(VBox container, ArrayList<Todo> list) {
        this.todosContainer = container;
        this.todosList = list;
    }

    public void renderTasks(String filter) {

        if(todosList.size()>0) {
            switch (filter) {
                case "Completed" -> todosList.forEach((todo) -> {
                    if (todo.status)
                        todosContainer.getChildren().add(todo.renderTask());
                });

                case "Active" -> todosList.forEach((todo) -> {
                    if (!todo.status)
                        todosContainer.getChildren().add(todo.renderTask());
                });

                default -> todosList.forEach( (todo) -> todosContainer.getChildren().add(todo.renderTask()));
            }
        }
    }

}
