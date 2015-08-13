package com.testdoc.impl;

import com.testdoc.api.DocumentationHandler;
import com.testdoc.api.DocumentationLineFormatter;
import com.testdoc.domain.Documentation;

import java.util.List;

public class ConsoleDocumentationHandler implements DocumentationHandler {

    @Override
    public void write(final List<Documentation> documentation, final DocumentationLineFormatter documentationLineFormatter) {

        for (Documentation d : documentation) {
            System.out.println(documentationLineFormatter.format(d));
        }

        System.out.println("");
        System.out.println("--- --- ---");
        System.out.println("");

    }
}
