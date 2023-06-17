package com.shaiful.expensetrackerapi.controllers;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.models.ResponseModel;
import com.shaiful.expensetrackerapi.repositories.TransactionRepository;
import com.shaiful.expensetrackerapi.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService service;

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<Transaction>>> getAll(HttpServletRequest request){
        return new ResponseEntity<>(new ResponseModel<>(service.finaAll((Integer) request.getAttribute("user_id")), "Success"), HttpStatus.OK);
    };

    @GetMapping("/{category_id}")
    public ResponseEntity<ResponseModel<List<Transaction>>> getAllByCategory(HttpServletRequest request, @PathVariable("category_id") Integer categoryId){
        List<Transaction> transactionList = service.findAllByCategory(
                (Integer) request.getAttribute("user_id"),
                categoryId
        );

        return new ResponseEntity<>(new ResponseModel<>(transactionList, "Success"), HttpStatus.OK);
    };

    @PostMapping("")
    public ResponseEntity<ResponseModel<Transaction>> addTransaction(HttpServletRequest request, @RequestBody Map<String, Object> body){
        Integer userId = (Integer) request.getAttribute("user_id");
        System.out.println("user id => " + userId);
        Transaction transaction = service.addTransaction(
                (Integer) body.get("category_id"),
                userId,
                (Double) body.get("amount"),
                (String) body.get("note"),
                (Long) body.get("transaction_date")

        );

        return new ResponseEntity<>(new ResponseModel<>(transaction, "Transaction created successfully"), HttpStatus.CREATED);
    }
}
