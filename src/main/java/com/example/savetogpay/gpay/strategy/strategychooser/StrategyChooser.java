package com.example.savetogpay.gpay.strategy.strategychooser;

import java.lang.reflect.Constructor;

public abstract class StrategyChooser {
    private final String CONCRETE_CLASS_PATH_TEMPLATE;

    StrategyChooser(String concreteClassPathTemplate) {
        CONCRETE_CLASS_PATH_TEMPLATE = concreteClassPathTemplate;
    }

    protected Object doChoose(String type) throws Exception {
        String strategyTypeFirstWord = getStrategyTypeInCamelCase(type);
        String className = getStrategyTypeConcreteClassName(strategyTypeFirstWord);
        Constructor<?> concreteStrategyClassConstructor = getConcreteStrategyClassConstructor(className);
        return concreteStrategyClassConstructor.newInstance();
    }

    private String getStrategyTypeInCamelCase(String type) {
        String typeLowerCase = type.toLowerCase();
        String firstLetter = typeLowerCase.substring(0, 1);
        return typeLowerCase.replaceFirst(firstLetter, firstLetter.toUpperCase());
    }

    private Constructor<?> getConcreteStrategyClassConstructor(String className) throws Exception {
        Class<?> concreteStrategyClass = Class.forName(className);
        Constructor<?> concreteStrategyClassConstructor = concreteStrategyClass.getDeclaredConstructor();
        concreteStrategyClassConstructor.setAccessible(true);
        return concreteStrategyClassConstructor;
    }

    private String getStrategyTypeConcreteClassName(String strategyTypeFirstWord) {
        return String.format(CONCRETE_CLASS_PATH_TEMPLATE, strategyTypeFirstWord);
    }
}
