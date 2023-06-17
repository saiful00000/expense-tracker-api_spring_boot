package com.shaiful.expensetrackerapi.services;

import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.shaiful.expensetrackerapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository repository;

    @Override
    public List<Transaction> finaAll(Integer userId) {
        return repository.findAll(userId);
    }

    @Override
    public List<Transaction> findAllByCategory(Integer userId, Integer categoryId) {
        return repository.findAllByCategory(userId, categoryId);
    }

    @Override
    public Transaction findById(Integer userId, Integer transactionId) {
        return repository.findById(userId, transactionId);
    }

    @Override
    public Transaction addTransaction(Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) throws EtBadRequestException {
        Integer transactionId = repository.create(categoryId, userId, amount, remark, transactionDate);
        return repository.findById(userId, transactionId);
    }

    @Override
    public void updateTransaction(Integer userID, Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeTransaction(Integer userId, Integer transactionId) throws EtResourceNotFoundException {

    }
}
