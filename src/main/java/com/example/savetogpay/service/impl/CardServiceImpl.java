package com.example.savetogpay.service.impl;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.strategy.CardStrategy;
import com.example.savetogpay.gpay.strategy.strategychooser.CardStrategyChooser;
import com.example.savetogpay.service.CardService;
import com.google.api.client.json.GenericJson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardStrategyChooser strategyChooser = new CardStrategyChooser();

    @Override
    public GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        throwIfIdNull(cardObjectAttributesDto.getClassId(), "Class");
        CardStrategy cardStrategy = strategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.create(cardObjectAttributesDto);
    }

    @Override
    public List<GetCardDto> getCards(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String classId = cardObjectAttributesDto.getClassId();
        throwIfIdNull(classId, "Class");
        CardStrategy cardStrategy = strategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.getAll(classId);
    }

    @Override
    public GenericJson getCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String objectId = cardObjectAttributesDto.getObjectId();
        throwIfIdNull(objectId, "Object");
        CardStrategy cardStrategy = strategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.get(objectId);
    }

    @Override
    public GenericJson updateCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        throwIfIdNull(cardObjectAttributesDto.getObjectId(), "Object");
        CardStrategy cardStrategy = strategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.update(cardObjectAttributesDto);
    }

    private void throwIfIdNull(String id, String idType) throws Exception {
        if (id == null || id.isBlank()) {
            String errorMessage = String.format("%s id not specified.", idType);
            throw new Exception(errorMessage);
        }
    }
}
