package todoapp.controllers;

import com.google.gson.Gson;
import todoapp.modules.Message;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class DataController {

    static Gson gson = new Gson();

    public static void updateStatus(int todoId, boolean newStatus) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/update"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n\t\"id\":" + todoId +",\n\t\"status\": "+ newStatus+"\n}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Message resMessage = gson.fromJson(response.body(), Message.class);
            System.out.println(resMessage.message);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTodo(int todoId) {
        System.out.println("Deleted todo id: "+ todoId);
    }

    //ma zwracać id todosa w bazie
    public static int insertTodo(String todoContent) {
        System.out.println("Added new todo");
        return 3;
    }
}
