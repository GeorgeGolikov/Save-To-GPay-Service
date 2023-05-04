package com.example.savetogpay.gpay.strategy.strategychooser;

import com.example.savetogpay.exception.TemplateOrCardException;

import java.lang.reflect.Constructor;

public abstract class StrategyChooser {
    private final String concreteClassPathTemplate;

    StrategyChooser(String concreteClassPathTemplate) {
        this.concreteClassPathTemplate = concreteClassPathTemplate;
    }

    protected Object doChoose(String type) {
        try {
            String strategyTypeFirstWord = getStrategyTypeInCamelCase(type);
            String className = getStrategyTypeConcreteClassName(strategyTypeFirstWord);
            Constructor<?> concreteStrategyClassConstructor = getConcreteStrategyClassConstructor(className);
            return concreteStrategyClassConstructor.newInstance();
        } catch (Exception e) {
            throw new TemplateOrCardException("Failed to instantiate Strategy Chooser concrete impl by given type."
                    + e.getMessage()
            );
        }
    }

    private String getStrategyTypeInCamelCase(String type) {
        String typeLowerCase = type.toLowerCase();
        String firstLetter = typeLowerCase.substring(0, 1);
        return typeLowerCase.replaceFirst(firstLetter, firstLetter.toUpperCase());
    }

    private Constructor<?> getConcreteStrategyClassConstructor(String className) {
        try {
            Class<?> concreteStrategyClass = Class.forName(className);
            Constructor<?> concreteStrategyClassConstructor = concreteStrategyClass.getDeclaredConstructor();
            concreteStrategyClassConstructor.setAccessible(true);
            return concreteStrategyClassConstructor;
        } catch (Exception e) {
            throw new TemplateOrCardException("Strategy class concrete constructor not found by className."
                    + e.getMessage()
            );
        }
    }

    private String getStrategyTypeConcreteClassName(String strategyTypeFirstWord) {
        return String.format(concreteClassPathTemplate, strategyTypeFirstWord);
    }
}
