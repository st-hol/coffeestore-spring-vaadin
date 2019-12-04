package ua.kpi.studying.coffeestore.domain.condiments;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Soy extends CondimentDecorator {
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	public double cost() {
		double cost = beverage.cost();
		if (beverage.getSize() == Size.TALL) {
			cost += .10;
		} else if (beverage.getSize() == Size.GRANDE) {
			cost += .15;
		} else if (beverage.getSize() == Size.VENTI) {
			cost += .20;
		}
		return cost;
	}
}
