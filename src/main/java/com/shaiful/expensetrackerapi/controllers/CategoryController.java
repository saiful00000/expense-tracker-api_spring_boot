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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<Category>>> getAllCategories(HttpServletRequest request) {
        List<Category> categoryList = categoryService.allCategories((Integer) request.getAttribute("user_id"));
        return new ResponseEntity<>(new ResponseModel<List<Category>>(categoryList, "Success!"), HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<ResponseModel<Category>> getCategoryById(HttpServletRequest request, @PathVariable("category_id") Integer categoryId){
        Integer userId = (Integer) request.getAttribute("user_id");
        Category category = categoryService.getCategoryById(userId, categoryId);
        return new ResponseEntity<>(new ResponseModel<>(category, "Success!"), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel<Category>> addCategory(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        Category category = categoryService.addCategory(
                (Integer) request.getAttribute("user_id"),
                (String) body.get("title"),
                (String) body.get("description")
        );

        return new ResponseEntity<>(
                new ResponseModel<>(category, "Category added successfully."),
                HttpStatus.CREATED
        );
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> updateCategory(HttpServletRequest request, @RequestBody Category category){
        categoryService.updateCategory((Integer) request.getAttribute("user_id"), category);
        return new ResponseEntity<>(Map.of("success", true, "message", "Category updated successfully."), HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Map<String, Object>> removeCategory(HttpServletRequest request, @PathVariable("category_id") Integer categoryId){
        categoryService.deleteCategoryWithAllTransactions((Integer) request.getAttribute("user_id"), categoryId);
        return new ResponseEntity<>(Map.of("message", "Category removed successfully."), HttpStatus.OK);
    }

}
