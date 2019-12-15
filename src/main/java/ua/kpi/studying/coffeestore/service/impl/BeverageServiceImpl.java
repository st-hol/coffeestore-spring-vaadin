package ua.kpi.studying.coffeestore.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.dto.BeverageDto;
import ua.kpi.studying.coffeestore.mappers.BeverageMapper;
import ua.kpi.studying.coffeestore.repository.BeverageRepository;
import ua.kpi.studying.coffeestore.service.BeverageService;
import ua.kpi.studying.coffeestore.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

@Service
public class BeverageServiceImpl implements BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BeverageMapper beverageMapper;

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
        beverage.setUser(userService.obtainCurrentPrincipleUser());
        return beverageRepository.save(beverage);
    }

    @Override
    public void delete(Beverage beverage) {
        beverageRepository.delete(beverage);
    }

    //    @Override
//    public List<Beverage> findOnlyMasterBeverages() {
//        return Lists.newArrayList(beverageRepository.findOnlyMasterBeverages())
//                .forEach(beverageMapper::beverageToBeverageDto);
//    }
    @Override
    public List<BeverageDto> findOnlyMasterBeveragesDtoList() {
        return beverageRepository.findOnlyMasterBeverages()
                .stream().map(beverageMapper::beverageToBeverageDto)
                .collect(Collectors.toList());
    }


//    @Override
//    public List<Beverage> findOnlyMasterBeveragesByUserId(Long id) {
//        return beverageRepository.findOnlyMasterBeveragesByUserId(id);
//    }

    @Override
    public List<BeverageDto> findOnlyMasterBeveragesDtoListByUserId(Long id) {
        return beverageRepository.findOnlyMasterBeveragesByUserId(id)
                .stream().map(beverageMapper::beverageToBeverageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeverageDto> findByDescription(String description) {
        return this.findOnlyMasterBeveragesDtoListByUserId(
                userService.obtainCurrentPrincipleUser().getId())
                .stream()
                .filter(b -> (b.getCost() + " " + b.getDescription()).contains(description))
                .collect(Collectors.toList());
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


