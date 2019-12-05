package ua.kpi.studying.coffeestore.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kpi.studying.coffeestore.component.BeverageEditor;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.domain.coffeefm.BeverageStore;
import ua.kpi.studying.coffeestore.domain.observer.CurrentConditionsDisplay;
import ua.kpi.studying.coffeestore.service.BeverageService;


@Route("")
public class BeverageList extends VerticalLayout {

    private final BeverageStore beverageStore;
    private final CurrentConditionsDisplay currentConditionsDisplay;

    private final BeverageService beverageService;
    private final BeverageEditor beverageEditor;
    private final TextField filterForFm = new TextField();
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New Custom Beverage", VaadinIcon.PLUS.create());
    private final Button addByFMButton = new Button("Create By FactoryMethod", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter);
    private final HorizontalLayout toolbarForAddNew = new HorizontalLayout(addNewButton);
    private final HorizontalLayout toolbarForFM = new HorizontalLayout(filterForFm, addByFMButton);
    private Grid<Beverage> employeeGrid = new Grid<>(Beverage.class);

    @Autowired
    public BeverageList(CurrentConditionsDisplay currentConditionsDisplay,
                        BeverageStore beverageStore, BeverageService beverageService, BeverageEditor beverageEditor) {
        this.beverageService = beverageService;
        this.beverageEditor = beverageEditor;

        this.beverageStore = beverageStore;
        this.currentConditionsDisplay = currentConditionsDisplay;

        filter.setPlaceholder("Search:");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        filterForFm.setPlaceholder("Type FM param (Espresso or DarkRoast or HouseBlend) :");
        filterForFm.setValueChangeMode(ValueChangeMode.LAZY);
//        filterForFm.addValueChangeListener(field -> addByFm(field.getValue()));
        addByFMButton.addClickListener(e -> addByFm(filterForFm.getValue()));

        add(toolbar, toolbarForAddNew, toolbarForFM, employeeGrid, beverageEditor);

        employeeGrid
                .asSingleSelect()
                .addValueChangeListener(e -> beverageEditor.editBeverage(e.getValue()));

        addNewButton.addClickListener(e -> beverageEditor.editBeverage(new Beverage()));

        beverageEditor.setChangeHandler(() -> {
            beverageEditor.setVisible(false);
            fillList(filter.getValue());
        });

        fillList("");
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            employeeGrid.setItems(this.beverageService.findOnlyMasterBeverages());
        } else {
            employeeGrid.setItems(this.beverageService.findByDescription(name));
        }
    }

    private void addByFm(String coffeeName) {
        Beverage beverage = beverageStore.orderBeverage(coffeeName);
        beverageEditor.addByFm(beverage);
    }

}
