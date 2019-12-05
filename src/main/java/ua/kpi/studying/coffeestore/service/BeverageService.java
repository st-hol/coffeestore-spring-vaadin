package ua.kpi.studying.coffeestore.service;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import java.util.List;

public interface BeverageService {
    List<Beverage> findAll();
    Beverage findById(Long id);
    Beverage save(Beverage complaint);
    void delete(Beverage beverage);

    List<Beverage> findOnlyMasterBeverages();
    List<Beverage>  findByDescription(String description);
}
