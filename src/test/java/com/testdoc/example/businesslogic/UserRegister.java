package com.testdoc.example.businesslogic;

public class UserRegister {

    public Result validate(String email, String password1, String password2) {

        if(email.trim().length() == 0) {
            return new Result("EMPTY EMAIL ERROR");
        }

        if(password1.trim().length() == 0) {
            return new Result("EMPTY PASSWORD ERROR");
        }

        if(!email.contains("@")) {
            return new Result("EMAIL ERROR");
        }

        if(!password1.equals(password2)) {
            return new Result("PASS ERROR");
        }

        return Result.ok();

    }

}
