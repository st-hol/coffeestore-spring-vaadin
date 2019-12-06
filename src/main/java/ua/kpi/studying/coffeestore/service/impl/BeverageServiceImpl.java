package ua.kpi.studying.coffeestore.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.repository.BeverageRepository;
import ua.kpi.studying.coffeestore.service.BeverageService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

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
        return Lists.newArrayList(beverageRepository.findOnlyMasterBeverages())
                .stream()
//                .map(b-> b.getId() + " " + b.getDescription())
                .filter(b->(b.getId() + " " + b.getDescription()).contains(description))
                .collect(Collectors.toList());

        //        return beverageRepository.findByDescriptionOnlyMasters(description);
        //        return beverageRepository.findByDescription(description);
    }
}


//    @Override
//    public List<Beverage> findOnlyMasterBeverages() {
//        List<Beverage> beverages = Lists.newArrayList(beverageRepository.findOnlyMasterBeverages());
//        for (Beverage beverage : beverages){
//            beverage.setDescription(recursiveFindDescription(beverage));
//        }
//        return beverages;
//    }

//    private String recursiveFindDescription(Beverage beverage){
//        String returnVal = "";
//        if ((((CondimentDecorator) findById((long) beverage.getId())).beverage) != null){
//            recursiveFindDescription(findById((long) beverage.getId() - 1));
//            returnVal = beverage.getDescription();
//        }
//        return returnVal;
//    }


