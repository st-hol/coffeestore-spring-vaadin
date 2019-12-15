package ua.kpi.studying.coffeestore.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.dto.BeverageDto;

@Mapper(componentModel = "spring")
public interface BeverageMapper {

    @Mappings({
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "cost", source = "entity.cost")
    })
    BeverageDto beverageToBeverageDto(Beverage entity);

    @Mappings({
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "cost", source = "dto.cost"),
            @Mapping(target = "user", ignore = true),
    })
    Beverage beverageDtoToBeverage(BeverageDto dto);
}