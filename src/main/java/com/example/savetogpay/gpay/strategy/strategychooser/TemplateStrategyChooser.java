package com.example.savetogpay.gpay.strategy.strategychooser;

import com.example.savetogpay.gpay.strategy.TemplateStrategy;

import java.lang.reflect.Constructor;

public class TemplateStrategyChooser extends StrategyChooser {
    private static final String TEMPLATE_STRATEGY_CONCRETE_CLASS_PATH_TEMPLATE =
            "com.example.savetogpay.gpay.strategy.concrete.%sTemplateStrategy";

    public TemplateStrategy choose(String type) throws Exception {
        try {
            String strategyTypeFirstWord = getStrategyTypeInCamelCase(type);
            String className = getStrategyTypeConcreteClassName(strategyTypeFirstWord);
            Constructor<?> concreteStrategyClassConstructor = getConcreteStrategyClassConstructor(className);
            return (TemplateStrategy) concreteStrategyClassConstructor.newInstance();
        } catch (Exception e) {
            throw new Exception("Card type specified incorrectly.");
        }
    }

    private String getStrategyTypeConcreteClassName(String strategyTypeFirstWord) {
        return String.format(TEMPLATE_STRATEGY_CONCRETE_CLASS_PATH_TEMPLATE, strategyTypeFirstWord);
    }
}
