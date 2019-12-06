package ua.kpi.studying.coffeestore.domain.coffeefm;


import org.springframework.stereotype.Component;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.domain.beverages.DarkRoast;
import ua.kpi.studying.coffeestore.domain.beverages.Espresso;
import ua.kpi.studying.coffeestore.domain.beverages.HouseBlend;
import ua.kpi.studying.coffeestore.domain.condiments.Mocha;
import ua.kpi.studying.coffeestore.domain.condiments.Soy;
import ua.kpi.studying.coffeestore.domain.condiments.Whip;

@Component
public class MyBeverageStore extends BeverageStore {

	Beverage makeBeverage(String item) {
		Beverage beverage;
		Beverage finalBeverage;
		switch (item) {
			case "Espresso":
				finalBeverage = new Espresso();
				beverage = new Espresso();
				finalBeverage.setDescription(beverage.getDescription());
				return beverage;
			case "DarkRoast":
				finalBeverage = new DarkRoast();
				beverage = new Whip(new Mocha(new Mocha(finalBeverage)));
				finalBeverage.setDescription(beverage.getDescription());
				return beverage;
			case "HouseBlend":
				finalBeverage = new HouseBlend();
				beverage = new Whip(new Mocha(new Soy(finalBeverage)));
				finalBeverage.setDescription(beverage.getDescription());
				return beverage;
			default:
				return null;
		}
	}
}
