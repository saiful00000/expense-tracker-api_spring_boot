package com.shaiful.expensetrackerapi.repositories;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository{
    @Override
    public List<Transaction> findAll(Integer userId) {
        return null;
    }

    @Override
    public List<Transaction> findAllByCategory(Integer userId, Integer categoryId) {
        return null;
    }

    @Override
    public Transaction findById(Integer userId, Integer transactionId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Integer create(Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) throws EtBadRequestException {
        return null;
    }

    @Override
    public void update(Integer userId, Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer userId, Integer transactionId) throws EtResourceNotFoundException {

    }
}
