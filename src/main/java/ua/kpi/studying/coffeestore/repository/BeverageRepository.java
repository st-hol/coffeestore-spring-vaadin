package ua.kpi.studying.coffeestore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import java.util.List;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Integer> {

    List<Beverage> findOnlyMasterBeverages();

    @Query("from Beverage b " +
            "where " +
            "   concat(b.description, ' ', b.id) like concat('%', :name, '%')")
    List<Beverage> findByDescription(@Param("name") String name);
}
