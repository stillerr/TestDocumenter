package com.testdoc.example.businesslogic;

import java.util.Objects;

public class Result {

    private static final String OK = "OK";

    private final String code;


    public Result(String code) {
        this.code = code;
    }

    public static Result ok() {
        return new Result(OK);
    }


    public boolean isOk() {
        return this.code.equals(OK);
    }


    @Override
    public String toString() {
        return String.format("AuthResult{code='%s'}", code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Result that = (Result) o;

        return Objects.equals(this.code, that.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

}
