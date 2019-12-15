package ua.kpi.studying.coffeestore.service;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.dto.BeverageDto;

import java.util.List;

public interface BeverageService {
    List<Beverage> findAll();
    Beverage findById(Long id);

    Beverage save(Beverage beverage);
    void delete(Beverage beverage);

    List<BeverageDto> findOnlyMasterBeveragesDtoList();

    List<BeverageDto> findOnlyMasterBeveragesDtoListByUserId(Long id);

    List<BeverageDto> findByDescription(String description);
}
