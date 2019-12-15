package ua.kpi.studying.coffeestore.domain.beverages;


import lombok.Getter;
import lombok.Setter;
import ua.kpi.studying.coffeestore.domain.entity.User;

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


@SqlResultSetMapping(
        name = "MasterBeveragesByUserMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Beverage.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "cost", type = Double.class)

                        }
                )
        }
)
@NamedNativeQuery(name = "Beverage.findOnlyMasterBeveragesByUserId",
        query = "select b.id, b.description, b.cost from beverage b " +
                "join users u on u.id = b.id_user " +
                "where b.beverage is null " +
                "and u.id = :id",
        resultSetMapping = "MasterBeveragesByUserMapping")


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discr")
public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    String description = "Unknown Beverage";
    double cost = 0;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public Beverage() {
        //required
    }


    public Beverage(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Beverage(int id, String description, Double cost) {
        this.id = id;
        this.description = description;
        this.cost = cost;
    }

    @PrePersist
    void preInsert() {
        this.description = getDescription();
        this.cost = (double) Math.round(getCost() * 100) / 100;
    }


}
