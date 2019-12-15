package ua.kpi.studying.coffeestore.service;


import ua.kpi.studying.coffeestore.domain.entity.Role;

import java.util.Set;

public interface SecurityService {
    String findLoggedInUsername();

    Set<Role> getLoggedUserRoles();

    void autoLoginAfterReg(String username, String password);
}
