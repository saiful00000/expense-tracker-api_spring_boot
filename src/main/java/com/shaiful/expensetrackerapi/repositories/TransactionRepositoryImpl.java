package com.shaiful.expensetrackerapi.repositories;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.entities.Transaction;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE) VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE FROM ET_TRANSACTIONS WHERE USER_ID = ? AND TRANSACTION_ID = ?";

    private static final String SQL_FIND_ALL = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ?";

    private static final String SQL_FIND_ALL_BY_CATEGORY = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ?";

    @Override
    public List<Transaction> findAll(Integer userId) {
        return jdbcTemplate.query(SQL_FIND_ALL, transactionRowMapper, new Object[]{userId});
    }

    @Override
    public List<Transaction> findAllByCategory(Integer userId, Integer categoryId) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_CATEGORY, transactionRowMapper,  new Object[]{userId, categoryId});
    }

    @Override
    public Transaction findById(Integer userId, Integer transactionId) throws EtResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, transactionRowMapper, new Object[]{userId, transactionId});
        }catch (Exception e){
            throw new EtResourceNotFoundException("Resource not found");
        }
    }

    @Override
    public Integer create(Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) throws EtBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    (connection) -> {
                        PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, categoryId);
                        ps.setInt(2, userId);
                        ps.setDouble(3, amount);
                        ps.setString(4, remark);
                        ps.setLong(5, transactionDate);
                        return ps;
                    },
                    keyHolder
            );

            return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
        }catch(Exception e){
            throw  new EtBadRequestException("Request body is not valid");
        }
    }

    @Override
    public void update(Integer userId, Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer userId, Integer transactionId) throws EtResourceNotFoundException {

    }

    private RowMapper<Transaction> transactionRowMapper = ((res, rowNumber) -> {
        return new Transaction(
                res.getInt("TRANSACTION_ID"),
                res.getInt("CATEGORY_ID"),
                res.getInt("USER_ID"),
                res.getDouble("AMOUNT"),
                res.getString("NOTE"),
                res.getLong("TRANSACTION_DATE")
        );
    });
}
