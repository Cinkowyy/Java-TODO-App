import javafx.scene.text.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import todoapp.controllers.DataController;
import todoapp.controllers.ErrorMessageController;
import todoapp.modules.AuthKey;
import todoapp.modules.Todo;
import todoapp.modules.UserAuthentication;

public class AppTests {

    @Test
    public void authenticateTest_invalidData_returnsFalse() {
        Text messegeText = new Text();
        ErrorMessageController msgController = new ErrorMessageController(messegeText);

        String login = "invalidLogin";
        String password = "invalidPassword";
        boolean result = UserAuthentication.authenticate(login,password,msgController);

        Assertions.assertEquals(false, result);

    }

    @Test
    public void updateStatus_invalidAuthKey_returnsFalse() {
        Text messegeText = new Text();
        ErrorMessageController msgController = new ErrorMessageController(messegeText);
        AuthKey key = new AuthKey("invalidAuthKey");
        DataController dataController = new DataController(key,msgController);

        boolean result = dataController.updateStatus(2, true);

        Assertions.assertEquals(false, result);

    }

    @Test
    public void clearTodos_invalidAuthKey_returnsFalse() {
        Text messegeText = new Text();
        ErrorMessageController msgController = new ErrorMessageController(messegeText);
        AuthKey key = new AuthKey("invalidAuthKey");
        DataController dataController = new DataController(key,msgController);

        boolean result = dataController.clearTodos();

        Assertions.assertEquals(false, result);

    }

    @Test
    public void deleteTodo_invalidAuthKey_returnsFalse() {
        Text messegeText = new Text();
        ErrorMessageController msgController = new ErrorMessageController(messegeText);
        AuthKey key = new AuthKey("invalidAuthKey");
        DataController dataController = new DataController(key,msgController);

        boolean result = dataController.deleteTodo(1);

        Assertions.assertEquals(false, result);

    }

    @Test
    public void insertTodo_invalidAuthKey_returnsNull() {
        Text messegeText = new Text();
        ErrorMessageController msgController = new ErrorMessageController(messegeText);
        AuthKey key = new AuthKey("invalidAuthKey");
        DataController dataController = new DataController(key,msgController);
        Todo todo = new Todo(1, "todoContent", false);

        Todo result = dataController.insertTodo(todo);

        Assertions.assertEquals(null, result);

    }

}
