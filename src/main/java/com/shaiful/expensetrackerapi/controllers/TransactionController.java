package com.shaiful.expensetrackerapi.controllers;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.models.ResponseModel;
import com.shaiful.expensetrackerapi.repositories.TransactionRepository;
import com.shaiful.expensetrackerapi.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService service;

    @GetMapping("/{transaction_id}")
    public ResponseEntity<Transaction> getById(HttpServletRequest request, @PathVariable("transaction_id") Integer transactionId) {
        Transaction transaction = service.findById((Integer) request.getAttribute("user_id"), transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<Transaction>>> getAll(HttpServletRequest request){
        return new ResponseEntity<>(new ResponseModel<>(service.finaAll((Integer) request.getAttribute("user_id")), "Success"), HttpStatus.OK);
    };

    @GetMapping("/by-category/{category_id}")
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

    @PutMapping("")
    public ResponseEntity<Map<String, String>> updateTransaction(HttpServletRequest request, @RequestBody Transaction transaction){
        service.updateTransaction((Integer) request.getAttribute("user_id"), transaction);
        return new ResponseEntity<>(Map.of("message", "Transaction updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{transaction_id}")
    public ResponseEntity<Map<String, Object>> removeTransaction(HttpServletRequest request, @PathVariable("transaction_id") Integer transactionId){
        service.removeTransaction((Integer) request.getAttribute("user_id"), transactionId);
        return new ResponseEntity<>(Map.of("message", "Transaction deleted successfully"), HttpStatus.OK);
    }
}
