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

    /***
     * This method is responsible for changing the status in the database. Function sends a request to the server
     * @param todoId this is ID of todo
     * @param newStatus this is bool new todo status
     * @return true if successful updated or false if error
     */
    public boolean updateStatus(int todoId, boolean newStatus) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/update"))
                .header("Content-Type", "application/json")
                .header("Authorization", authorizationKey.getKey())
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n\t\"id\":" + todoId +",\n\t\"status\": "+ newStatus+"\n}"))
                .build();
        HttpResponse<String> response = null;
        boolean resStatus = false;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Message resMessage = gson.fromJson(response.body(), Message.class);

            if(response.statusCode() == 200) {
                errorMessageField.removeMessage();
                resStatus = true;
                System.out.println(resMessage.message);
            } else {
                errorMessageField.setMessage(resMessage.message);
            }

        } catch (IOException | InterruptedException e) {
            errorMessageField.setMessage("Server connection error");
            e.printStackTrace();
        }

        return resStatus;
    }

    /***
     * This method is responsible for deleting all completed items in the database
     * @return true if successful updated or false if error
     */
    public boolean clearTodos() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/clear"))
                .header("Authorization", authorizationKey.getKey())
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        boolean resStatus = false;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Message resMessage = gson.fromJson(response.body(), Message.class);

            if(response.statusCode() == 200) {
                errorMessageField.removeMessage();
                resStatus = true;
                System.out.println(resMessage.message);
            } else {
                errorMessageField.setMessage(resMessage.message);
            }

        } catch (IOException | InterruptedException e) {
            errorMessageField.setMessage("Server connection error");
            e.printStackTrace();
        }
        return resStatus;
    }

    /***
     * This method is responsible for removing a single element with a given id
     * @param todoId this is ID of todo
     * @return true if successful updated or false if error
     */
    public boolean deleteTodo(int todoId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/delete"))
                .header("Content-Type", "application/json")
                .header("Authorization", authorizationKey.getKey())
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n\t\"id\":" +todoId+"}"))
                .build();
        HttpResponse<String> response = null;
        boolean resStatus = false;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Message resMessage = gson.fromJson(response.body(), Message.class);

            if(response.statusCode() == 200) {
                errorMessageField.removeMessage();
                resStatus = true;
                System.out.println(resMessage.message);
            } else {
                errorMessageField.setMessage(resMessage.message);
            }

        } catch (IOException | InterruptedException e) {
            errorMessageField.setMessage("Server connection error");
            e.printStackTrace();
        }

        return resStatus;
    }

    /***
     * This method is responsible for adding a new element to the database
     * @param newTodo Todo object to insert
     * @return Todo object if successful insert or null if error
     */
    public Todo insertTodo(Todo newTodo) {

        String jsonTodo = gson.toJson(newTodo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/add"))
                .header("Content-Type", "application/json")
                .header("Authorization", authorizationKey.getKey())
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonTodo))
                .build();
        HttpResponse<String> response = null;
        newTodo = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200) {
                newTodo = gson.fromJson(response.body(), Todo.class);
                System.out.println("Added new todo");
            } else {
                Message msg = gson.fromJson(response.body(), Message.class);
                errorMessageField.setMessage(msg.message);
            }

        } catch (IOException | InterruptedException e) {
            errorMessageField.setMessage("Server connection error");
            e.printStackTrace();
        }

        return newTodo;
    }

    /***
     * This method is responsible for retrieving the elements of a given user from the database
     * @return ArrayList of Todos if successful or empty ArrayList if error
     */
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
            errorMessageField.setMessage("Server connection error");
            e.printStackTrace();
        }

        return todosList;
    }
}
