package todoapp.modules;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public abstract class TodosGetter {

    public static ArrayList<Todo> getTodos(AuthKey key, LoginErrorMessage errorMessageController) {
        Gson gson =  new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/todos"))
                .header("Authorization", key.getKey())
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        ArrayList<Todo> todosList = new ArrayList<>();

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200) {
                todosList= gson.fromJson(response.body(), new TypeToken<ArrayList<Todo>>(){}.getType());
                return  todosList;
            } else {
                Message errorMsg = gson.fromJson(response.body(), Message.class);
                errorMessageController.setMessage(errorMsg.message);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return todosList;
    }
}
