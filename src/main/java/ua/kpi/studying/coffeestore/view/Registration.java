package ua.kpi.studying.coffeestore.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kpi.studying.coffeestore.domain.entity.User;
import ua.kpi.studying.coffeestore.service.UserService;


@Route("registration")
public class Registration extends VerticalLayout {

    private UserService userService;
    private Binder<User> binder = new Binder<>();
    private PasswordField passwordField, rePasswordField;
    private Label usernameInDatabase = new Label();
    private Label emailInDatabase = new Label();

    @Autowired
    public Registration(UserService userRepo) {
        this.userService = userRepo;
        Label label = new Label("There was an error");
        label.setVisible(false);
        TextField textFieldUsername = usernameField();

        this.passwordField = passwordField();
        this.rePasswordField = rePasswordField();
        Button buttonSubmit = new Button("Submit");

        buttonSubmit.addClickListener(event ->
        {
            usernameInDatabase.setVisible(false);
            emailInDatabase.setVisible(false);
            if (isValid(textFieldUsername.getValue(), passwordField.getValue(), rePasswordField.getValue())) {
                try {
                    saveUser(textFieldUsername.getValue(), passwordField.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        add(textFieldUsername, this.passwordField, this.rePasswordField, buttonSubmit, usernameInDatabase);

    }

    private TextField usernameField() {
        TextField textField = new TextField("Username");
        textField.setPlaceholder("Username");
        textField.setRequired(true);
        binder.forField(textField)
                .withValidator(
                        new RegexpValidator("Username should be at least 3 characters long", "^(?=\\S+$).{3,32}"))
                .bind(User::getPassword, User::setPassword);
        return textField;

    }


    private PasswordField passwordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.setPlaceholder("Enter password");
        passwordField.setRequired(true);

        binder.forField(passwordField)
                .withValidator(new RegexpValidator("Password should contain lowercase and uppercase letters and digits and be at least 8 characters long", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,32}$"))
                .bind(User::getPassword, User::setPassword);

        return passwordField;

    }

    private PasswordField rePasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.setPlaceholder("Enter password");
        passwordField.setRequired(true);
        binder.forField(passwordField)
                .withValidator(this::rePasswordValidator, "Passwords should match")
                .bind(User::getPassword, User::setPassword);
        add(passwordField, passwordField);
        return passwordField;
    }


    private Boolean passwordValidator(String password) {
        System.out.println(password);
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,32}$")) {
            return true;
        }
        return false;
    }

    private Boolean rePasswordValidator(String password) {
        System.out.println(password + " " + passwordField.getValue());
        if (password.equals(passwordField.getValue())) {
            return true;
        }
        return false;
    }

    private Boolean usernameValidator(String username) {
        if (username.matches("^(?=\\S+$).{3,32}")) {
            return true;
        }
        return false;
    }

    private Boolean emailValidator(String email) {
        if (email.matches("^([a-zA-Z0-9_\\.\\-+])+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9-]{2,}$")) {
            return true;
        }
        return false;
    }


    private void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.registerUser(user);
    }

    private boolean usernameInDataBaseValidator(String username) {
        User user = userService.findByUsername(username);

        if (user != null) {
            usernameInDatabase.setText("There is already user with that username");
            usernameInDatabase.setVisible(true);
            return false;
        }
        return true;
    }

    public Boolean isValid(String username, String password, String rePassword) {
        boolean usernameindb = usernameInDataBaseValidator(username);
        if (passwordValidator(password) && rePasswordValidator(rePassword) && usernameValidator(username) && usernameindb) {
            return true;
        }

        return false;
    }


}