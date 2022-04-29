package com.example.savetogpay.gpay.strategy.strategychooser;

import java.lang.reflect.Constructor;

public abstract class StrategyChooser {
    protected String getStrategyTypeInCamelCase(String type) {
        String typeLowerCase = type.toLowerCase();
        String firstLetter = typeLowerCase.substring(0, 1);
        return typeLowerCase.replaceFirst(firstLetter, firstLetter.toUpperCase());
    }

    protected Constructor<?> getConcreteStrategyClassConstructor(String className) throws Exception {
        Class<?> concreteStrategyClass = Class.forName(className);
        Constructor<?> concreteStrategyClassConstructor = concreteStrategyClass.getDeclaredConstructor();
        concreteStrategyClassConstructor.setAccessible(true);
        return concreteStrategyClassConstructor;
    }
}
