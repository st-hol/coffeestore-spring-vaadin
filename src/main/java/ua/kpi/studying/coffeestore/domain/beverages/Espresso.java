package ua.kpi.studying.coffeestore.domain.beverages;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ESPRESSO")
public class Espresso extends Beverage {
  
	public Espresso() {
		description = "Espresso";
        cost = 1.99;
	}
  
}

