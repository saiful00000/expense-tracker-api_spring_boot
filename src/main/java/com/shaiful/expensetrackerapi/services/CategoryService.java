package com.shaiful.expensetrackerapi.services;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> allCategories(Integer userId);

    Category getCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    void updateCategory(Integer userId, Category category) throws EtBadRequestException;

    void deleteCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
