package ua.kpi.studying.coffeestore.domain.condiments;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Whip extends CondimentDecorator {
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}

	public double getCost() {
		return beverage.getCost() + .10;
	}
}
