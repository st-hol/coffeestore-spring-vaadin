package ua.kpi.studying.coffeestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeverageDto {
    private long id;
    private String description;
    private double cost;
}
