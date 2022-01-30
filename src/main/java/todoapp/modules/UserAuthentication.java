package todoapp.modules;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class UserAuthentication {

    public static void authenticate(String login, String password) {

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
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public static void loadMainView(Stage AppStage, URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Scene mainView = new Scene(loader.load());
        AppStage.setTitle("TODO App");
        mainView.lookup(".logo-text").requestFocus();
        AppStage.setScene(mainView);
    }
}
