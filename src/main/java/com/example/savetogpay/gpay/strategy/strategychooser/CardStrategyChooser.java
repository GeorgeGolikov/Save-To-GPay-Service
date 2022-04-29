package com.example.savetogpay.gpay.strategy.strategychooser;

import com.example.savetogpay.gpay.strategy.CardStrategy;

import java.lang.reflect.Constructor;

public class CardStrategyChooser extends StrategyChooser {
    private static final String CARD_STRATEGY_CONCRETE_CLASS_PATH_TEMPLATE =
            "com.example.savetogpay.gpay.strategy.concrete.%sCardStrategy";

    public CardStrategy choose(String type) throws Exception {
        try {
            String strategyTypeFirstWord = getStrategyTypeInCamelCase(type);
            String className = getStrategyTypeConcreteClassName(strategyTypeFirstWord);
            Constructor<?> concreteStrategyClassConstructor = getConcreteStrategyClassConstructor(className);
            return (CardStrategy) concreteStrategyClassConstructor.newInstance();
        } catch (Exception e) {
            throw new Exception("Card type specified incorrectly.");
        }
    }

    private String getStrategyTypeConcreteClassName(String strategyTypeFirstWord) {
        return String.format(CARD_STRATEGY_CONCRETE_CLASS_PATH_TEMPLATE, strategyTypeFirstWord);
    }
}
