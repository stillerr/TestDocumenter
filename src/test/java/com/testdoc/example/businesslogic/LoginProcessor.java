package com.testdoc.example.businesslogic;

public class LoginProcessor {

    public Result validate(User user, String email, String password) {

        if(user.email.equals(email) && user.password.equals(password)) {
            return Result.ok();
        }

        return new Result("Unknown user!");

    }

}
