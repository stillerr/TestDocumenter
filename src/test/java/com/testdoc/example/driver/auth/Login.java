package com.testdoc.example.driver.auth;

import com.testdoc.example.businesslogic.User;

public interface Login extends AuthCommon {

    Login forAlreadyRegisteredUser(User user);

    Login withEmail(String email);

    Login withPassword(String password);

}
