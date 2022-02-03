package todoapp.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import todoapp.modules.LoginErrorMessage;
import todoapp.modules.UserAuthentication;
import java.io.IOException;


public class LoginController {

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    @FXML
    private Text errorMessage;

    @FXML
    void initialize() {

        LoginErrorMessage loginMessageController = new LoginErrorMessage(errorMessage);
        loginButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (loginInput.getText().length() == 0 || passwordInput.getText().length() == 0) {
                    loginMessageController.setMessage("Fields cannot be empty");

                } else if (loginInput.getText().length() < 3 || passwordInput.getText().length() < 8) {
                    loginMessageController.setMessage("Invalid login or password");

                } else {
                    loginMessageController.removeMessage();

                    String login = loginInput.getText();
                    String password = passwordInput.getText();

                    try {
                        if (UserAuthentication.authenticate(login, password, loginMessageController)) {
                            UserAuthentication.loadMainView((Stage) loginInput.getScene().getWindow(), this.getClass().getResource("../views/mainView.fxml"));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
