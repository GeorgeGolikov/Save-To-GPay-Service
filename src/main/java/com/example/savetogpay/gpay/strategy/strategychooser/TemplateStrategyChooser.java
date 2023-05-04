package com.example.savetogpay.gpay.strategy.strategychooser;

import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.strategy.TemplateStrategy;

public class TemplateStrategyChooser extends StrategyChooser {
    public TemplateStrategyChooser() {
        super("com.example.savetogpay.gpay.strategy.concrete.%sTemplateStrategy");
    }

    public TemplateStrategy choose(String type) {
        try {
            return (TemplateStrategy) doChoose(type);
        } catch (Exception e) {
            throw new TemplateOrCardException("Card type specified incorrectly.");
        }
    }
}
