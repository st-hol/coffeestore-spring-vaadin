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
import ua.kpi.studying.coffeestore.service.BeverageService;



@Route("")
public class BeverageList extends VerticalLayout {
    private final BeverageService beverageService;

    private final BeverageEditor beverageEditor;

    private Grid<Beverage> employeeGrid= new Grid<>(Beverage.class);
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New Beverage", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    @Autowired
    public BeverageList(BeverageService beverageService, BeverageEditor beverageEditor) {
        this.beverageService = beverageService;
        this.beverageEditor = beverageEditor;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, employeeGrid, beverageEditor);

        employeeGrid
                .asSingleSelect()
                .addValueChangeListener(e -> beverageEditor.editBeverage(e.getValue()));

        addNewButton.addClickListener(e -> beverageEditor.editBeverage(new Beverage() {
            @Override
            public double cost() {
                return 0;
            }
        }));

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
            employeeGrid.setItems(this.beverageService.findById(Long.parseLong(name)));
        }
    }
}
