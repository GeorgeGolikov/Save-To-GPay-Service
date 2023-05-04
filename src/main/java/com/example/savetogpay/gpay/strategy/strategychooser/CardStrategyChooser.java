package com.example.savetogpay.gpay.strategy.strategychooser;

import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.strategy.CardStrategy;

public class CardStrategyChooser extends StrategyChooser {
    public CardStrategyChooser() {
        super("com.example.savetogpay.gpay.strategy.concrete.%sCardStrategy");
    }

    public CardStrategy choose(String type) {
        try {
            return (CardStrategy) doChoose(type);
        } catch (Exception e) {
            throw new TemplateOrCardException("Card type specified incorrectly.");
        }
    }
}
