package com.testdoc.example.driver.auth;

public interface Registration extends AuthCommon<Registration> {

    Registration withEmail(String email);

    Registration withPassword1(String password);

    Registration withPassword2(String password);

    Login navigateToLogin();

}
