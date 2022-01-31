package todoapp.controllers;

import todoapp.modules.AuthKey;

public class MainController {

    public MainController(AuthKey authKey) {
        System.out.println("MainController started with key:");
        System.out.println(authKey.getKey());
    }

}
