package ua.kpi.studying.coffeestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.studying.coffeestore.domain.entity.Role;
import ua.kpi.studying.coffeestore.domain.entity.User;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAllByUsers(User user);
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
}
