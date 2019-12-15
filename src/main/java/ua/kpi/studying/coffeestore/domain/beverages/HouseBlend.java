package ua.kpi.studying.coffeestore.domain.beverages;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOUSEBLEND")
public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
        cost = 0.89;
	}
 
}

