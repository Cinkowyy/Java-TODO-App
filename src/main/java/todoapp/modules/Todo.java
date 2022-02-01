package todoapp.modules;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Todo {

    public int id;
    public String content;
    public int status;

    public Todo(int taskId, String taskContent, int taskStatus) {
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

        contentContainer.getChildren().add(contentText);

        container.getChildren().add(checkBox);
        container.getChildren().add(contentContainer);
        container.getChildren().add(crossIcon);

        container.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(taskId);

            }
        });

        return container;
    }



}
