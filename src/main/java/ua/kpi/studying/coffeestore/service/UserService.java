package ua.kpi.studying.coffeestore.service;


import ua.kpi.studying.coffeestore.domain.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User findByUsername(String username);

    void registerUser(User user);

    User obtainCurrentPrincipleUser();

}