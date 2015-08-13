package com.testdoc.impl;

import com.testdoc.domain.Documentation;
import com.testdoc.impl.DefaultDocumentationLineFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DefaultDocumentationLineFormatterTest {

    private final DefaultDocumentationLineFormatter formatter = new DefaultDocumentationLineFormatter();

    @Test
    public void formatCaseParams() {
        String line = formatter.format(Documentation.forCase("Hello"));
        Assert.assertEquals("Hello", line);
    }

    @Test
    public void formatStepWithoutParams() {
        String line = formatter.format(Documentation.forStep("Hello", null, null));
        Assert.assertEquals("  * Hello", line);
    }

    @Test
    public void formatStepWithParams() {

        Map<String, Object> map = new HashMap<>();
        map.put("p1", "A");
        map.put("p2", "B");

        String line = formatter.format(Documentation.forStep("Hello ${p1}, ${p2}", map, null));
        Assert.assertEquals("  * Hello 'A', 'B'", line);
    }

    @Test
    public void formatStepWithResult() {
        String line = formatter.format(Documentation.forStep("Hello ${return}", null, "Result"));
        Assert.assertEquals("  * Hello 'Result'", line);
    }

    @Test
    public void formatStepWithParamsAndResult() {

        Map<String, Object> map = new HashMap<>();
        map.put("p1", "A");
        map.put("p2", "B");

        String line = formatter.format(Documentation.forStep("Hello ${p1}, ${p2}, ${return}", map, "Result"));
        Assert.assertEquals("  * Hello 'A', 'B', 'Result'", line);
    }

}