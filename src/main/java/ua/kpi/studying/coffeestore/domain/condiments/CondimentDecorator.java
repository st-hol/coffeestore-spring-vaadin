package ua.kpi.studying.coffeestore.domain.condiments;

import ua.kpi.studying.coffeestore.domain.beverages.Beverage;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

//https://www.doctrine-project.org/projects/doctrine-orm/en/2.7/cookbook/decorator-pattern.html
@MappedSuperclass
public abstract class CondimentDecorator extends Beverage {

	@OneToOne(targetEntity=Beverage.class, cascade= CascadeType.ALL)
    @JoinColumn(name="beverage", referencedColumnName="id")
	public Beverage beverage;

    @Override
    public abstract String getDescription();

    @Override
    public abstract double getCost();


}
