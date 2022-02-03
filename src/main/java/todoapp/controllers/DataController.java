package todoapp.controllers;

public abstract class DataController {

    public static void updateStatus(int todoId, boolean newStatus) {
        System.out.println("Task id: "+ todoId+" new status is: "+ newStatus);
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
