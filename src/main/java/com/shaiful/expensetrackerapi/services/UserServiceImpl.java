package com.shaiful.expensetrackerapi.services;

import com.shaiful.expensetrackerapi.Constants;
import com.shaiful.expensetrackerapi.entities.User;
import com.shaiful.expensetrackerapi.exceptions.EtAuthException;
import com.shaiful.expensetrackerapi.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<String, Object> validateUser(String email, String password) throws EtAuthException {
        if(email != null) {
            email = email.toLowerCase();
        }
        User user = userRepository.findByEmailAndPassword(email, password);
        String token = generateJWTToken(user);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", token);
        responseMap.put("user", user);

        return  responseMap;
    }

    @Override
    public Map<String, Object> registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

        if (email == null) {
            throw new EtAuthException("No email has given");
        }

        if (!emailPattern.matcher(email.toLowerCase()).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        Integer count = userRepository.getCountByEmail(email);
        if (count > 0) {
            throw new EtAuthException("Email already in use");
        }

        Integer userId = userRepository.create(firstName, lastName, email, password);
        User user = userRepository.findById(userId);
        String token = generateJWTToken(user);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", token);
        responseMap.put("user", user);

        return responseMap;
    }

    private String generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("user_id", user.getUserId())
                .claim("first_name", user.getFirstName())
                .claim("last_name", user.getLastName())
                .claim("email", user.getEmail())
                .compact();
    }
}
