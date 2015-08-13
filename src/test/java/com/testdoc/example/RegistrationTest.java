package com.testdoc.example;


import com.testdoc.meta.DocCase;
import com.testdoc.TestDocAspect;
import com.testdoc.example.businesslogic.Result;
import com.testdoc.example.businesslogic.User;
import com.testdoc.example.driver.AuthDriver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class RegistrationTest {

    @Before
    public void setUp() throws Exception {
        new TestDocAspect();
    }


    @Test
    @DocCase("New user registration with valid parameters")
    public void canRegisterWithEmailAndPassword() {

        Result result = new AuthDriver().registration()
                .withEmail("test@test.com")
                .withPassword1("verySecurePass")
                .withPassword2("verySecurePass")
                .submit();

        assertEquals(Result.ok(), result);

    }

    @Test
    @DocCase("New user registration fails because the email address is required")
    public void cantRegisterWithoutEmail() {

        Result result = new AuthDriver().registration()
                .withEmail("  ")
                .withPassword1("verySecurePass")
                .withPassword2("verySecurePass")
                .submit();

        assertNotEquals(Result.ok(), result);

    }

    @Test
    @DocCase("New user registration fails because the password is required")
    public void cantRegisterWithoutPassword() {

        Result result = new AuthDriver().registration()
                .withEmail("test@test.com")
                .withPassword1("")
                .withPassword2("")
                .submit();

        assertNotEquals(Result.ok(), result);

    }

    @Test
    @DocCase("New user registration fails because the given passwords are different")
    public void cantRegisterWithDifferentPasswords() {

        Result result = new AuthDriver().registration()
                .withEmail("test@test.com")
                .withPassword1("a")
                .withPassword2("b")
                .submit();

        assertNotEquals(Result.ok(), result);

    }

    @Test
    @DocCase("The login fails because the user is unknown")
    public void cantLoginTheUnknownUser() {

        Result result = new AuthDriver().login()
                .forAlreadyRegisteredUser(new User("admin@test.com", "verySecurePass"))
                .withEmail("admin@test.com")
                .withPassword("wrongPass")
                .submit();

        assertNotEquals(Result.ok(), result);

    }

    @Test
    @DocCase("Can login the saved user")
    public void canLogin() {

        Result result = new AuthDriver().login()
                .forAlreadyRegisteredUser(new User("admin@test.com", "verySecurePass"))
                .withEmail("admin@test.com")
                .withPassword("verySecurePass")
                .submit();

        assertEquals(Result.ok(), result);

    }

    @Test
    @DocCase("Can register and login")
    public void canRegisterAndLogin() {

        final String email = "test@test.com";
        final String password = "pass";

        Result result = new AuthDriver().registration()
                .withEmail(email)
                .withPassword1(password)
                .withPassword2(password)
                .submitAndContinue()
                .navigateToLogin()
                .withEmail(email)
                .withPassword(password)
                .submit();

        assertEquals(Result.ok(), result);

    }


}
