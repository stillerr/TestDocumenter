package com.testdoc.example.driver.auth;

import com.testdoc.meta.DocStep;
import com.testdoc.example.businesslogic.LoginProcessor;
import com.testdoc.example.businesslogic.Result;
import com.testdoc.example.businesslogic.User;

public class LoginDriver implements Login {

    private User user = User.unknown();

    private String email;
    private String password;


    public LoginDriver(User user) {
        this.user = user;
    }


    @Override
    @DocStep("There is a saved user: ${user}")
    public Login forAlreadyRegisteredUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    @DocStep("User fills the email field with ${email}")
    public Login withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    @DocStep("User fills the email field with ${password}")
    public Login withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    @DocStep("User submits the form and the result is: ${return}")
    public Result submit() {

        Result result = new LoginProcessor().validate(user, email, password);

        if(result.isOk()) {
            user.email = email;
            user.password = password;
        }

        return result;
    }

    @Override
    public Login submitAndContinue() {
        submit();
        return this;
    }
}
