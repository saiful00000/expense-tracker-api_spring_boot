package com.shaiful.expensetrackerapi.services;

import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {
    List<Transaction> finaAll(Integer userId);
    List<Transaction> findAllByCategory(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer transactionId);

    Transaction addTransaction(Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) throws EtBadRequestException;

    void updateTransaction(Integer userID, Transaction transaction) throws EtBadRequestException;

    void removeTransaction(Integer userId, Integer transactionId) throws EtResourceNotFoundException;
}
