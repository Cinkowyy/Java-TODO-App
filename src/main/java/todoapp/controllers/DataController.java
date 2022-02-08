package todoapp.controllers;

import com.google.gson.Gson;
import todoapp.modules.AuthKey;
import todoapp.modules.LoginErrorMessage;
import todoapp.modules.Message;
import todoapp.modules.Todo;

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

    public static Todo insertTodo(Todo newTodo, LoginErrorMessage msgController, AuthKey key) {

        String jsonTodo = gson.toJson(newTodo);
        System.out.println(key.getKey());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/add"))
                .header("Content-Type", "application/json")
                .header("Authorization", key.getKey())
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonTodo))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if( response != null && response.statusCode() == 200) {
            newTodo = gson.fromJson(response.body(), Todo.class);
            System.out.println("Added new todo");
        } else {
            Message msg = gson.fromJson(response.body(), Message.class);
            msgController.setMessage(msg.message);
            newTodo = null;
        }

        return newTodo;
    }
}
