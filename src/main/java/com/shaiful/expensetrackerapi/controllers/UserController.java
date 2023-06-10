package com.shaiful.expensetrackerapi.controllers;

import com.shaiful.expensetrackerapi.entities.User;
import com.shaiful.expensetrackerapi.models.ResponseModel;
import com.shaiful.expensetrackerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from spring boot live";
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<Map<String, Object>>> register(@RequestBody Map<String, Object> body) {
        final String firstName = (String) body.get("first_name");
        final String lastName = (String) body.get("last_name");
        final String email = (String) body.get("email");
        final String password = (String) body.get("password");

        Map<String, Object> registerData = userService.registerUser(firstName, lastName, email, password);
        ResponseModel<Map<String, Object>> responseModel = new ResponseModel<Map<String, Object>>(registerData, "Success!");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseModel<Map<String, Object>>> login(@RequestBody Map<String, String> body){
        Map<String, Object> loginData = userService.validateUser(body.get("email"), body.get("password"));
        ResponseModel<Map<String, Object>> responseModel = new ResponseModel<Map<String, Object>>(loginData, "Success!");
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
