package ua.kpi.studying.coffeestore.domain.coffeefm;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import java.util.Observable;
import java.util.Set;


public abstract class BeverageStore extends Observable {

    abstract Beverage makeBeverage(String item, Set<String> condiments);

    public Beverage orderBeverage(String type, Set<String> condiments) {
        Beverage beverage = makeBeverage(type, condiments);
        notifyThatDrinkIsBeingPrepared();
        return beverage;
    }

    //////obsrv/////
    public void notifyThatDrinkIsBeingPrepared() {
        setChanged();
        notifyObservers();
    }
}
