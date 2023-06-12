package com.shaiful.expensetrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("user_id")
    private Integer userid;
    private String title;
    private String description;

    public Category(Integer categoryId, Integer userid, String title, String description, Double totalExpense) {
        this.categoryId = categoryId;
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
    }

    @JsonProperty("total_expense")
    private Double totalExpense;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
