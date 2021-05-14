package com.in28minutes.springboot.web.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
// @component says that this service is bean type and can be used for injecting in controllers
//step10
@Component
public class LoginService {
    public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
    }
}
