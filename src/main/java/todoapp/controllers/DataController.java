package todoapp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import todoapp.modules.AuthKey;
import todoapp.modules.Message;
import todoapp.modules.Todo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class DataController {

    static Gson gson = new Gson();
    public final ErrorMessageController errorMessageField;
    private final AuthKey authorizationKey;

    public DataController(AuthKey key, ErrorMessageController message) {
        this.authorizationKey = key;
        this.errorMessageField = message;
    }

    public void updateStatus(int todoId, boolean newStatus) {
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

    public void deleteTodo(int todoId) {
        System.out.println("Deleted todo id: "+ todoId);
    }

    public Todo insertTodo(Todo newTodo) {

        String jsonTodo = gson.toJson(newTodo);
        System.out.println(authorizationKey.getKey());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/add"))
                .header("Content-Type", "application/json")
                .header("Authorization", authorizationKey.getKey())
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
            errorMessageField.setMessage(msg.message);
            newTodo = null;
        }

        return newTodo;
    }

    public ArrayList<Todo> getTodos() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/todos"))
                .header("Authorization", authorizationKey.getKey())
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
                errorMessageField.setMessage(errorMsg.message);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return todosList;
    }
}
