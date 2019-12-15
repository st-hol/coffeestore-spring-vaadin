package ua.kpi.studying.coffeestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.studying.coffeestore.domain.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
