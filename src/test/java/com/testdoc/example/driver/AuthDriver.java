package com.testdoc.example.driver;

import com.testdoc.meta.DocStep;
import com.testdoc.example.businesslogic.User;
import com.testdoc.example.driver.auth.*;

public class AuthDriver {

    private final User user = User.unknown();


    @DocStep("User clicks on the new registration button")
    public Registration registration() {
        return redirectToRegistration();
    }

    @DocStep("Navigate to registration screen")
    private Registration redirectToRegistration() {
        return new RegistrationDriver(user);
    }

    @DocStep("User clicks on the login button")
    public Login login() {
        return new LoginDriver(user);
    }

}
