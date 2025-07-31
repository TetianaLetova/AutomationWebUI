package com.andersen.priority;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

public class ReverseOrderInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        methods.sort((m1, m2) -> m2.getMethod().getMethodName().compareTo(m1.getMethod().getMethodName()));
        return methods;
    }
}