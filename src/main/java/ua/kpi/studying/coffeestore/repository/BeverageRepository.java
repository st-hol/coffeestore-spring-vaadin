package ua.kpi.studying.coffeestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import java.util.List;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Integer> {
//
//    @Query(nativeQuery = true,
//            value = "select id from beverage")
    List<Beverage> findOnlyMasterBeverages();
}
