package todoapp.modules;

public class AuthKey {
    private final String authKey;

    public AuthKey(String key) {
        authKey = key;
    }

    public String getKey() {
        return authKey;
    }
}
