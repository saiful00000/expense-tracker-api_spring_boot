package com.shaiful.expensetrackerapi.services;

import com.shaiful.expensetrackerapi.entities.User;
import com.shaiful.expensetrackerapi.exceptions.EtAuthException;

import java.util.Map;

public interface UserService {

    Map<String, Object> validateUser(String email, String password) throws EtAuthException;

    Map<String, Object> registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
