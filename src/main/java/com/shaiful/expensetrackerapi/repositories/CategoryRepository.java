package com.shaiful.expensetrackerapi.repositories;

import com.shaiful.expensetrackerapi.entities.Category;
import com.shaiful.expensetrackerapi.exceptions.EtBadRequestException;
import com.shaiful.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    void update (Integer userId, Category category) throws EtBadRequestException;

    void remove(Integer userId, Integer categoryId);

}
