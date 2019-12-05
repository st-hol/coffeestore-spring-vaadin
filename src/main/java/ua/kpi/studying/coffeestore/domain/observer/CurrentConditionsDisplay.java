package ua.kpi.studying.coffeestore.domain.observer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.kpi.studying.coffeestore.domain.coffeefm.BeverageStore;

import java.util.Observable;
import java.util.Observer;

@Component
public class CurrentConditionsDisplay implements Observer {

	@Autowired
	@Qualifier("myBeverageStore")
	Observable observable;

	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof BeverageStore) {
//			BeverageStore beverageStore = (BeverageStore)obs;
			System.out.println("OBSERVED: drink was prepared");
			display();
		}
	}

	public void display() {
	}
}
