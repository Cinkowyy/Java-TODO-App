package todoapp.controllers;

import javafx.scene.text.Text;

public class ErrorMessageController {

    Text errorTextField;

    public ErrorMessageController(Text field) {
        this.errorTextField =  field;
    }

    /***
     * This method is showing error message
     * @param message message text
     */
    public void setMessage(String message) {
        errorTextField.setText(message);
        errorTextField.setStyle("-fx-opacity: 1;");
    }

    /***
     * Thius method hides error message
     */
    public void removeMessage() {
        errorTextField.setStyle("-fx-opacity: 0;");
    }
}
