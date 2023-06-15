package com.shaiful.expensetrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    @JsonProperty("transaction_id")
    private Integer transactionId;
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("user_id")
    private Integer userId;

    private Double amount;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("transaction_date")
    private Long transactionDate;

    public Transaction(Integer transactionId, Integer categoryId, Integer userId, Double amount, String remark, Long transactionDate) {
        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
        this.remark = remark;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
