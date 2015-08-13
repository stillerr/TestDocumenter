package com.testdoc.impl;

import com.testdoc.api.DocumentationLineFormatter;
import com.testdoc.domain.Documentation;

import java.util.Map;

public class DefaultDocumentationLineFormatter implements DocumentationLineFormatter {

    @Override
    public String format(final Documentation documentation) {

        String offset = documentation.isHead() ? "" : "  * ";
        String value = documentation.getValue();
        Map<String, Object> arguments = documentation.getArguments();
        Object result = documentation.getResult();

        if (arguments != null) {
            for (String key : arguments.keySet()) {
                value = value.replace("${" + key + "}", "'" + arguments.get(key).toString() + "'");
            }
        }

        if (result != null) {
            value = value.replace("${return}", "'" + result.toString() + "'");
        }



        return offset + value;
    }

}
