package com.example.savetogpay.service.impl;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.strategy.CardStrategy;
import com.example.savetogpay.gpay.strategy.concrete.CardStrategyChooser;
import com.example.savetogpay.service.CardService;
import com.google.api.client.json.GenericJson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardStrategy cardStrategy;

    @Override
    public GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        checkClassId(cardObjectAttributesDto.getClassId());
        cardStrategy = CardStrategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.create(cardObjectAttributesDto);
    }

    @Override
    public List<GetCardDto> getCards(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String classId = cardObjectAttributesDto.getClassId();
        checkClassId(classId);
        cardStrategy = CardStrategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.getAll(classId);
    }

    @Override
    public GenericJson getCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String objectId = cardObjectAttributesDto.getObjectId();
        checkObjectId(objectId);
        cardStrategy = CardStrategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.get(objectId);
    }

    @Override
    public GenericJson updateCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        checkObjectId(cardObjectAttributesDto.getObjectId());
        cardStrategy = CardStrategyChooser.choose(cardObjectAttributesDto.getCardType());
        return cardStrategy.update(cardObjectAttributesDto);
    }

    private void checkClassId(String classId) throws Exception {
        throwIfClassIdIsNull(classId);
    }

    private void checkObjectId(String objectId) throws Exception {
        throwIfObjectIdIsNull(objectId);
    }

    private void throwIfClassIdIsNull(String classId) throws Exception {
        if (classId == null || classId.equals("")) {
            throw new Exception("Class id not specified.");
        }
    }

    private void throwIfObjectIdIsNull(String objectId) throws Exception {
        if (objectId == null || objectId.equals("")) {
            throw new Exception("Object id not specified.");
        }
    }
}
