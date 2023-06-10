package com.shaiful.expensetrackerapi.controllers;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        return "User id = " + userId;
    }
}
