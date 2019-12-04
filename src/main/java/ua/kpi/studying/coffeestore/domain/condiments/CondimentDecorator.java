package ua.kpi.studying.coffeestore.domain.condiments;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import javax.persistence.*;

//https://www.doctrine-project.org/projects/doctrine-orm/en/2.7/cookbook/decorator-pattern.html
@MappedSuperclass
public abstract class CondimentDecorator extends Beverage {

	@OneToOne(targetEntity=Beverage.class, cascade= CascadeType.ALL)
    @JoinColumn(name="beverage", referencedColumnName="id")
	public Beverage beverage;

	public abstract String getDescription();
	
	public Size getSize() {
		return beverage.getSize();
	}
}
