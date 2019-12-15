package ua.kpi.studying.coffeestore.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
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
import ua.kpi.studying.coffeestore.dto.BeverageDto;
import ua.kpi.studying.coffeestore.mappers.BeverageMapper;
import ua.kpi.studying.coffeestore.service.BeverageService;
import ua.kpi.studying.coffeestore.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Route("list")
public class BeverageList extends VerticalLayout {

    private final BeverageStore beverageStore;
    private final UserService userService;
    private final CurrentConditionsDisplay currentConditionsDisplay;
    private final BeverageMapper beverageMapper;

    private final BeverageService beverageService;
    private final BeverageEditor beverageEditor;
    private final TextField filterForFm = new TextField();
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New Custom Beverage", VaadinIcon.PLUS.create());
    private final Button addByFMButton = new Button("Create By FactoryMethod", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter);
    private final HorizontalLayout toolbarForAddNew = new HorizontalLayout(addNewButton);
    private final Set<String> condiments = new HashSet<>();
    private Grid<BeverageDto> beverageGrid = new Grid<>(BeverageDto.class);
    private Checkbox addMilkCheckBox = new Checkbox("Add Milk?");
    private Checkbox addMochaCheckBox = new Checkbox("Add Mocha?");
    private Checkbox addSoyCheckBox = new Checkbox("Add Soy?");
    private Checkbox addWhipCheckBox = new Checkbox("Add Whip?");
    private final HorizontalLayout toolbarForFM = new HorizontalLayout(filterForFm, addByFMButton,
            addMilkCheckBox, addMochaCheckBox, addSoyCheckBox, addWhipCheckBox);

    @Autowired
    public BeverageList(CurrentConditionsDisplay currentConditionsDisplay,
                        BeverageStore beverageStore,
                        BeverageService beverageService, UserService userService,
                        BeverageMapper beverageMapper,
                        BeverageEditor beverageEditor) {
        this.beverageService = beverageService;
        this.userService = userService;
        this.beverageMapper = beverageMapper;
        this.beverageEditor = beverageEditor;

        this.beverageStore = beverageStore;
        this.currentConditionsDisplay = currentConditionsDisplay;

        filter.setPlaceholder("Search:");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        filterForFm.setPlaceholder("Type FM param (Espresso or DarkRoast or HouseBlend) :");
        filterForFm.setValueChangeMode(ValueChangeMode.LAZY);
        addByFMButton.addClickListener(e -> addByFm(filterForFm.getValue()));

        //conds
        addMilkCheckBox.addValueChangeListener(event -> triggerCheckboxForCondiment(event.getValue(),
                "Milk"));
        addMochaCheckBox.addValueChangeListener(event -> triggerCheckboxForCondiment(event.getValue(),
                "Mocha"));
        addSoyCheckBox.addValueChangeListener(event -> triggerCheckboxForCondiment(event.getValue(),
                "Soy"));
        addWhipCheckBox.addValueChangeListener(event -> triggerCheckboxForCondiment(event.getValue(),
                "Whip"));
        //conds

        add(toolbar, toolbarForAddNew, toolbarForFM, beverageGrid, beverageEditor);

        beverageGrid
                .asSingleSelect()
                .addValueChangeListener(e -> beverageEditor.editBeverage(
                        beverageMapper.beverageDtoToBeverage(e.getValue())));

        addNewButton.addClickListener(e -> beverageEditor.editBeverage(new Beverage()));

        beverageEditor.setChangeHandler(() -> {
            beverageEditor.setVisible(false);
            fillList(filter.getValue());
        });

        fillList("");
    }

    private void triggerCheckboxForCondiment(boolean isChangedToTrue, String condimentName) {
        if (isChangedToTrue) { //true
            condiments.add(condimentName);
        } else {
            condiments.remove(condimentName);
        }
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            beverageGrid.setItems(this.beverageService.findOnlyMasterBeveragesDtoListByUserId(
                    userService.obtainCurrentPrincipleUser().getId()));
        } else {
            beverageGrid.setItems(this.beverageService.findByDescription(name));
        }
    }

    private void addByFm(String coffeeName) {
        Beverage beverage = beverageStore.orderBeverage(coffeeName, condiments);
        beverageEditor.addByFm(beverage);
    }

}
