package com.shaiful.expensetrackerapi.repositories;

import com.shaiful.expensetrackerapi.entities.User;
import com.shaiful.expensetrackerapi.exceptions.EtAuthException;

public interface UserRepository {

   Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

   User findByEmailAndPassword(String email, String password) throws EtAuthException;

   Integer getCountByEmail(String email);

   User findById(Integer id);

}
