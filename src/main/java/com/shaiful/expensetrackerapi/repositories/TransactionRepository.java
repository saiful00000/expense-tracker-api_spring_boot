package com.shaiful.expensetrackerapi.repositories;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll(Integer userId);
    List<Transaction> findAllByCategory(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer transactionId) throws EtResourceNotFoundException;

    Integer create(Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) throws EtBadRequestException;

    void update(Integer userId, Transaction transaction) throws EtBadRequestException;

    void removeById(Integer userId, Integer transactionId) throws EtResourceNotFoundException;

}
