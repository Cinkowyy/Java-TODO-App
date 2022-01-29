package todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import todoapp.controllers.LoginController;

public class Main extends Application {

    public static void main(String... args) {
        launch(args);
    }

    public void start(Stage AppStage) throws Exception {

        Font.loadFont(getClass().getResourceAsStream("fonts/JosefinSans-Bold.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("fonts/JosefinSans-Medium.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("fonts/JosefinSans-Regular.ttf"), 16);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("views/loginView.fxml"));

        LoginController controller = new LoginController();
        loader.setController(controller);

        Scene loginView = new Scene(loader.load());
        loginView.lookup(".logo-text").requestFocus();
        AppStage.setScene(loginView);
        AppStage.setTitle("TODO App login");
        AppStage.setResizable(false);


        AppStage.show();
    }
}
