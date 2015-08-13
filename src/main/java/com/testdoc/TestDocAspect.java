package com.testdoc;

import com.testdoc.api.DocumentationHandler;
import com.testdoc.api.DocumentationLineFormatter;
import com.testdoc.domain.Documentation;
import com.testdoc.impl.ConsoleDocumentationHandler;
import com.testdoc.impl.DefaultDocumentationLineFormatter;
import com.testdoc.meta.DocCase;
import com.testdoc.meta.DocStep;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
public class TestDocAspect {

    private final DocumentationLineFormatter documentationLineFormatter;

    private final DocumentationHandler documentationHandler;


    private List<Documentation> documentation;


    public TestDocAspect() {
        documentationLineFormatter = new DefaultDocumentationLineFormatter();
        documentationHandler = new ConsoleDocumentationHandler();
    }

    public TestDocAspect(final DocumentationLineFormatter documentationLineFormatter, final DocumentationHandler documentationHandler) {
        this.documentationLineFormatter = documentationLineFormatter;
        this.documentationHandler = documentationHandler;
    }


    @Around("execution(* *(..)) && @annotation(docCase)")
    public Object aroundDocCase(ProceedingJoinPoint joinPoint, DocCase docCase) throws Throwable {

        documentation = new ArrayList<>();
        documentation.add(Documentation.forCase(docCase.value()));

        Object proceed = joinPoint.proceed();

        documentationHandler.write(documentation, documentationLineFormatter);

        return proceed;
    }

    @Around("execution(* *(..)) && @annotation(docStep)")
    public Object aroundDocStep(ProceedingJoinPoint joinPoint, DocStep docStep) throws Throwable {

        final String value = docStep.value();
        final Map<String, Object> methodArguments = getMethodArguments(joinPoint);

        Documentation doc = Documentation.forStep(value, methodArguments);
        documentation.add(doc);

        Object result = joinPoint.proceed();

        doc.setResult(result);

        return result;
    }

    private Map<String, Object> getMethodArguments(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getStaticPart().getSignature();
        final Map<String, Object> arguments = new HashMap<>();

        if (!(signature instanceof MethodSignature)) {
            return arguments;
        }

        final MethodSignature ms = (MethodSignature) signature;

        String[] parameterNames = ms.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            arguments.put(parameterNames[i], parameterValues[i]);
        }

        return arguments;

    }


}
