package ua.kpi.studying.coffeestore.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.service.BeverageService;


@SpringComponent
@UIScope
public class BeverageEditor extends VerticalLayout implements KeyNotifier {

    private final BeverageService beverageService;

    private Beverage beverage;

//    private TextField id = new TextField("", "id");
//    private TextField size = new TextField("", "size");
    private TextField description = new TextField("", "description");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

    private Binder<Beverage> binder = new Binder<>(Beverage.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public BeverageEditor(BeverageService beverageService) {
        this.beverageService = beverageService;

        add(description, buttons);
//        add(size, id, description, buttons);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editBeverage(beverage));
        setVisible(false);
    }

    private void save() {
        beverageService.save(beverage);
        changeHandler.onChange();
    }

    private void delete() {
        beverageService.delete(beverage);
        changeHandler.onChange();
    }

    public void editBeverage(Beverage beverage) {
        if (beverage == null) {
            setVisible(false);
            return;
        }

        if (beverage.getId() != 0) { // todo
            this.beverage = beverageService.findById((long)beverage.getId());
        } else {
            this.beverage = beverage;
        }

        binder.setBean(this.beverage);

        setVisible(true);

//        size.focus();
    }

    public void addByFm(Beverage beverage) {
        if (beverage != null) {
            beverageService.save(beverage);
        }
        changeHandler.onChange();
    }
}
