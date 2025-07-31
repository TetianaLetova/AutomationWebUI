package com.andersen.priority;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

import java.util.Comparator;

public class ReverseNameOrderer implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
//        context.getMethodDescriptors().sort(
//                Comparator.comparing(MethodOrderer.MethodDescriptor::getMethodName).reversed()
//        );
    }
}
