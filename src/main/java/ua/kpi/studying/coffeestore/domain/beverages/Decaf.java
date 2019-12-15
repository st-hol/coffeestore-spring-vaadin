package ua.kpi.studying.coffeestore.domain.beverages;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DECAF")
public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf Coffee";
        cost = 1.05;
	}
 
}

