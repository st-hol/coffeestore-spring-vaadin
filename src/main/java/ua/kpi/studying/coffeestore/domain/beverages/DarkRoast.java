package ua.kpi.studying.coffeestore.domain.beverages;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DARKROAST")
public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "Dark Roast Coffee";
		cost = .99;
	}

}

