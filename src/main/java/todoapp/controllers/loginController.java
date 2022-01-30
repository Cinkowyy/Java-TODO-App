package todoapp.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    public LoginController() {
        System.out.println("Jestem kontrolerem");
    }

    @FXML
    void initialize() {
        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                if(loginInput.getText().length() == 0 || passwordInput.getText().length() == 0) {
                    errorMessage.setText("Fields cannot be empty");
                    errorMessage.setStyle("-fx-opacity: 1;");

                } else if(loginInput.getText().length() < 3  || passwordInput.getText().length() < 8){
                    errorMessage.setText("Invalid login or password");
                    errorMessage.setStyle("-fx-opacity: 1;");

                } else {
                    errorMessage.setStyle("-fx-opacity: 0;");

                    String login = loginInput.getText();
                    String password = passwordInput.getText();

                    try {
                        if(UserAuthentication.authenticate(login, password)) {
                            UserAuthentication.loadMainView((Stage) loginInput.getScene().getWindow(), this.getClass().getResource("../views/mainView.fxml"));
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
