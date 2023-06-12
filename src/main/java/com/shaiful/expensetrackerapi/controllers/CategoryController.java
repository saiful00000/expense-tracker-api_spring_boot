package com.shaiful.expensetrackerapi.controllers;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.models.ResponseModel;
import com.shaiful.expensetrackerapi.services.CategoryService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        return "User id = " + userId;
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel<Category>> addCategory(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        Category category = categoryService.addCategory(
                (Integer) request.getAttribute("user_id"),
                (String) body.get("title"),
                (String) body.get("description")
        );

        return new ResponseEntity<>(
                new ResponseModel<Category>(category, "Category added successfully."),
                HttpStatus.CREATED
        );
    }
}
