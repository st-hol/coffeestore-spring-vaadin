package ua.kpi.studying.coffeestore.domain.beverages;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name="MasterBeveragesMapping",
        classes={
                @ConstructorResult(
                        targetClass=Beverage.class,
                        columns={
                                @ColumnResult(name="id"),
                                @ColumnResult(name="description"),
                                @ColumnResult(name="size")
                        }
                )
        }
)
@NamedNativeQuery(name="Beverage.findOnlyMasterBeverages",
        query="select * from beverage b where b.beverage is null",
        resultSetMapping="MasterBeveragesMapping")

//@Embeddable
@Getter
@Setter
////@Entity
//@Table(name = "beverages")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dev_type")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discr")
public abstract class Beverage {
    public enum Size {TALL, GRANDE, VENTI}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    Size size = Size.TALL;
    String description = "Unknown Beverage";

    public abstract double cost();
}
