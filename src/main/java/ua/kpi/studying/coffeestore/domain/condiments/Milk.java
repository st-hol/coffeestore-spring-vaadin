package ua.kpi.studying.coffeestore.domain.condiments;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Milk extends CondimentDecorator {
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	public double getCost() {
		return beverage.getCost() + .10;
	}

}
