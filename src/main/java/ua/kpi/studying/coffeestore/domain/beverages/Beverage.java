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
                                @ColumnResult(name="id", type = Integer.class),
                                @ColumnResult(name="description", type = String.class)
                        }
                )
        }
)
@NamedNativeQuery(name="Beverage.findOnlyMasterBeverages",
        query="select b.id, b.description from beverage b where b.beverage is null",
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
public class Beverage {

    public Beverage(){

    }

    public Beverage(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    String description = "Unknown Beverage";

    public double cost() {
        return 0;
    }

    @PrePersist
    void preInsert() {
        this.description = getDescription();
    }

}
