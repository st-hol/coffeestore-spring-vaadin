package ua.kpi.studying.coffeestore.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.repository.BeverageRepository;
import ua.kpi.studying.coffeestore.service.BeverageService;

import static java.lang.Math.toIntExact;
import java.util.List;

@Service
public class BeverageServiceImpl implements BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;

    @Override
    public List<Beverage> findAll() {
        return Lists.newArrayList(beverageRepository.findAll());
    }

    @Override
    public Beverage findById(Long id) {
        return beverageRepository.findById(toIntExact(id)).orElse(null);
    }

    @Override
    public Beverage save(Beverage beverage) {
        return beverageRepository.save(beverage);
    }

    @Override
    public void delete(Beverage beverage) {
        beverageRepository.delete(beverage);
    }

    @Override
    public List<Beverage> findOnlyMasterBeverages() {
        return Lists.newArrayList(beverageRepository.findOnlyMasterBeverages());
    }

    @Override
    public  List<Beverage>  findByDescription(String description) {
        return beverageRepository.findByDescription(description);
    }
}
