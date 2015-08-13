package com.testdoc.api;

import com.testdoc.domain.Documentation;

import java.util.List;

public interface DocumentationHandler {

    void write(final List<Documentation> documentation, final DocumentationLineFormatter documentationLineFormatter);
}
