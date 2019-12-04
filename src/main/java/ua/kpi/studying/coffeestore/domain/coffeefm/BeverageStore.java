package ua.kpi.studying.coffeestore.domain.coffeefm;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import java.util.Observable;


public abstract class BeverageStore extends Observable {

    abstract Beverage makeBeverage(String item);

    public Beverage orderBeverage(String type) {
        Beverage beverage = makeBeverage(type);
        notifyThatDrinkIsBeingPrepared();
        return beverage;
    }

    //////obsrv/////
    public void notifyThatDrinkIsBeingPrepared() {
        setChanged();
        notifyObservers();
    }
}
