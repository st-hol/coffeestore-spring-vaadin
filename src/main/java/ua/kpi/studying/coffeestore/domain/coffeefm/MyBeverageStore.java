package ua.kpi.studying.coffeestore.domain.coffeefm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.domain.beverages.DarkRoast;
import ua.kpi.studying.coffeestore.domain.beverages.Espresso;
import ua.kpi.studying.coffeestore.domain.beverages.HouseBlend;
import ua.kpi.studying.coffeestore.domain.condiments.Milk;
import ua.kpi.studying.coffeestore.domain.condiments.Mocha;
import ua.kpi.studying.coffeestore.domain.condiments.Soy;
import ua.kpi.studying.coffeestore.domain.condiments.Whip;
import ua.kpi.studying.coffeestore.domain.entity.User;
import ua.kpi.studying.coffeestore.service.UserService;

import java.util.Set;

@Component
public class MyBeverageStore extends BeverageStore {

    @Autowired
    private UserService userService;

    Beverage makeBeverage(String masterBeverageName, Set<String> condiments) {
        User principle = userService.obtainCurrentPrincipleUser();
        Beverage masterBeverage;
        switch (masterBeverageName) {
            case "Espresso":
                masterBeverage = new Espresso();
                return initBeverage(principle, masterBeverage,
                        decorateMasterBeverageWithCondiments(masterBeverage, condiments));
            //new Espresso());
            case "DarkRoast":
                masterBeverage = new DarkRoast();
                return initBeverage(principle, masterBeverage,
                        decorateMasterBeverageWithCondiments(masterBeverage, condiments));
            //new Whip(new Mocha(new Mocha(masterBeverage))));
            case "HouseBlend":
                masterBeverage = new HouseBlend();
                return initBeverage(principle, masterBeverage,
                        decorateMasterBeverageWithCondiments(masterBeverage, condiments));
            //new Whip(new Mocha(new Soy(masterBeverage))));
            default:
                return null;
        }
    }

    private Beverage decorateMasterBeverageWithCondiments(Beverage masterBeverage,
                                                          Set<String> decors) {
        Beverage preparedBeverage = masterBeverage;
        if (decors.contains("Milk")) {
            preparedBeverage = new Milk(preparedBeverage);
        }
        if (decors.contains("Mocha")) {
            preparedBeverage = new Mocha(preparedBeverage);
        }
        if (decors.contains("Soy")) {
            preparedBeverage = new Soy(preparedBeverage);
        }
        if (decors.contains("Whip")) {
            preparedBeverage = new Whip(preparedBeverage);
        }
        return preparedBeverage;
    }

    private Beverage initBeverage(User principle, Beverage masterBeverage, Beverage decoratedBeverage) {
        masterBeverage.setUser(principle);
        masterBeverage.setDescription(decoratedBeverage.getDescription());
        masterBeverage.setCost(decoratedBeverage.getCost());
        return masterBeverage;
    }
}
