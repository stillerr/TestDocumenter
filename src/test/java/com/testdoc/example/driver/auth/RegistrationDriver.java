package com.testdoc.example.driver.auth;

import com.testdoc.meta.DocStep;
import com.testdoc.example.businesslogic.Result;
import com.testdoc.example.businesslogic.User;
import com.testdoc.example.businesslogic.UserRegister;

public class RegistrationDriver implements Registration {

    private User user = User.unknown();

    private String email;
    private String password1;
    private String password2;

    
    public RegistrationDriver(User user) {
        this.user = user;
    }


    @Override
    @DocStep("User fills the email field with ${email}")
    public Registration withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    @DocStep("User fills the first password field with ${password}")
    public Registration withPassword1(String password) {
        this.password1 = password;
        return this;
    }

    @Override
    @DocStep("User fills the second password field with ${password}")
    public Registration withPassword2(String password) {
        this.password2 = password;
        return this;
    }

    @Override
    @DocStep("User submits the form and the result is: ${return}")
    public Result submit() {
        Result result = new UserRegister().validate(email, password1, password2);

        if(result.isOk()) {
            user.email = email;
            user.password = password1;
        }

        return result;
    }

    @Override
    public Registration submitAndContinue() {
        submit();
        return this;
    }

    @Override
    @DocStep("Navigate to login screen")
    public Login navigateToLogin() {
        return new LoginDriver(user);
    }

}
