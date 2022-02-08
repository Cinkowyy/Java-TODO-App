package todoapp.controllers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import todoapp.modules.Todo;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TodosController {

    VBox todosContainer;
    ArrayList<Todo> todosList;

    public TodosController(VBox container, ArrayList<Todo> list) {
        this.todosContainer = container;
        this.todosList = list;
    }

    public void renderTasks(String filter) {

        todosContainer.getChildren().clear();

        if(todosList.size()>0) {
            switch (filter) {

            case "Completed" -> todosList.forEach((todo) -> {
                if (todo.status)
                    todosContainer.getChildren().add(addListener(todo, filter));
            });

            case "Active" -> todosList.forEach((todo) -> {
                if (!todo.status)
                    todosContainer.getChildren().add(addListener(todo, filter));
            });

            default -> todosList.forEach((todo) -> todosContainer.getChildren().add(addListener(todo, filter)));
            }
        }
    }

    public HBox addListener(Todo todo, String filter) {

        HBox todoElement = todo.renderTask();
        todoElement.lookup(".cross-icon").setOnMouseClicked(MouseEvent -> {
            todosList.remove(todo);
            renderTasks(filter);
        });

        return  todoElement;
    }

}
