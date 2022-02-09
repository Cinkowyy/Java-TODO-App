package todoapp.controllers;

import javafx.scene.text.Text;

public class ErrorMessageController {

    Text errorTextField;

    public ErrorMessageController(Text field) {
        this.errorTextField =  field;
    }

    public void setMessage(String message) {
        errorTextField.setText(message);
        errorTextField.setStyle("-fx-opacity: 1;");
    }

    public void removeMessage() {
        errorTextField.setStyle("-fx-opacity: 0;");
    }
}
