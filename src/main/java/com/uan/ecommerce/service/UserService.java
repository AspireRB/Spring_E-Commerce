package com.uan.ecommerce.service;

import com.uan.ecommerce.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
    User save (User user);
    Optional<User> findByEmailAndPassword(String email, String password);


}
