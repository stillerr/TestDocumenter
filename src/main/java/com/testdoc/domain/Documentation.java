package com.testdoc.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Documentation {

    private final boolean head;

    private final String value;

    private final Map<String, Object> arguments;

    private Object result;


    private Documentation(final boolean head, final String value, final Map<String, Object> arguments, final Object result) {
        this.head = head;
        this.value = value;
        this.arguments = arguments;
        this.result = result;
    }

    public static Documentation forCase(final String value) {
        Map<String, Object> args = new HashMap<>();
        return new Documentation(true, value, args, null);
    }

    public static Documentation forStep(final String value, final Map<String, Object> arguments) {
        return new Documentation(false, value, arguments, null);
    }

    public static Documentation forStep(final String value, final Map<String, Object> arguments, final Object result) {
        return new Documentation(false, value, arguments, result);
    }


    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isHead() {
        return head;
    }

    public String getValue() {
        return value;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public Object getResult() {
        return result;
    }


    @Override
    public String toString() {
        return "Documentation{" +
                "value='" + value + '\'' +
                ", arguments=" + arguments +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Documentation that = (Documentation) o;

        return Objects.equals(this.value, that.value)
                && Objects.equals(this.arguments, that.arguments)
                && Objects.equals(this.result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, arguments, result);
    }
}
