package ua.kpi.studying.coffeestore.domain.beverages;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name="MasterBeveragesMapping",
        classes={
                @ConstructorResult(
                        targetClass=Beverage.class,
                        columns={
                                @ColumnResult(name="id", type = Integer.class),
                                @ColumnResult(name="description", type = String.class)
//                                @ColumnResult(name="size", type = Beverage.Size.class)
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
@NoArgsConstructor
////@Entity
//@Table(name = "beverages")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dev_type")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discr")
public abstract class Beverage {
    public enum Size {TALL, GRANDE, VENTI}

    public Beverage(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    Size size = Size.TALL;
    String description = "Unknown Beverage";

    public abstract double cost();
}
