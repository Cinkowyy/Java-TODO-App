package todoapp.modules;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoapp.controllers.MainController;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class UserAuthentication {

    public static AuthKey authKey;

    public static boolean authenticate(String login, String password, LoginErrorMessage errorMessageController) {

        UserData data = new UserData(login, password);

        Gson gson =  new Gson();
        String jsonData = gson.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/login"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonData))
                .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200) {
                authKey = gson.fromJson(response.body(), AuthKey.class);
                return true;
            } else {
                Message errorMsg = gson.fromJson(response.body(), Message.class);
                errorMessageController.setMessage(errorMsg.message);
            }

        } catch (IOException | InterruptedException e) {
                errorMessageController.setMessage("Server connection error");
                e.printStackTrace();
        }
        return false;
    }


    @FXML
    public static void loadMainView(Stage AppStage, URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        MainController controller = new MainController(authKey);
        loader.setController(controller);

        Scene mainView = new Scene(loader.load());
        AppStage.setTitle("TODO App");
        mainView.lookup(".logo-text").requestFocus();
        AppStage.setScene(mainView);
    }
}
