package com.testdoc.example.driver.auth;

import com.testdoc.example.businesslogic.Result;

public interface AuthCommon<T> {

    Result submit();

    T submitAndContinue();

}
