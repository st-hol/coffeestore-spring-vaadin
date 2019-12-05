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
		switch (item) {
			case "Espresso":
				return new Espresso();
			case "DarkRoast":
				return new Whip(new Mocha(new Mocha(new DarkRoast())));
			case "HouseBlend":
				return new Whip(new Mocha(new Soy(new HouseBlend())));
			default:
				return null;
		}
	}
}
