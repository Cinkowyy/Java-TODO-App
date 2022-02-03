package todoapp.modules;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import todoapp.controllers.TodosController;

public class Todo {

    public int id;
    public String content;
    public boolean status;

    public Todo(int taskId, String taskContent, boolean taskStatus) {
        this.id = taskId;
        this.content = taskContent;
        this.status = taskStatus;
    }

    public HBox renderTask() {

        int taskId = this.id;

        HBox container = new HBox();
        container.getStyleClass().add("todo-element");

        CheckBox checkBox = new CheckBox();
        checkBox.getStyleClass().add("todo-checkbox");
        checkBox.setSelected(status);


        FlowPane contentContainer = new FlowPane();
        contentContainer.getStyleClass().add("todo-text-wrapper");
        contentContainer.setPrefWrapLength(300);

        Text contentText = new Text();
        contentText.getStyleClass().add("todo-text");
        contentText.setText(this.content);
        contentText.setWrappingWidth(280);

        ImageView crossIcon = new ImageView();
        Image cross = new Image(String.valueOf(this.getClass().getResource("../images/icon-cross.png")));
        crossIcon.setImage(cross);
        crossIcon.getStyleClass().add("cross-icon");

        textStrikethrough(status,contentText);

        //change status event
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                textStrikethrough(newValue, contentText);
                changeStatus(newValue);
            }
        });

        contentContainer.getChildren().add(contentText);

        container.getChildren().add(checkBox);
        container.getChildren().add(contentContainer);
        container.getChildren().add(crossIcon);

        //mouse click event
        container.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(taskId);
                System.out.println(status);
            }
        });

        return container;
    }

    public void textStrikethrough(boolean checked, Text text) {

        if(checked)
            text.getStyleClass().add("completed");
        else
            text.getStyleClass().remove("completed");

    }

    public void changeStatus(boolean newStatus) {
        this.status = newStatus;
    }


}
