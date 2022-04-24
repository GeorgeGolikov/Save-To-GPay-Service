package com.example.savetogpay.service.impl;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.strategy.CardStrategy;
import com.example.savetogpay.gpay.strategy.concrete.LoyaltyCardStrategy;
import com.example.savetogpay.gpay.strategy.concrete.OfferCardStrategy;
import com.example.savetogpay.service.CardService;
import com.google.api.client.json.GenericJson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardStrategy cardStrategy;

    @Override
    public GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String classId = cardObjectAttributesDto.getClassId();
        throwIfClassIdIsNull(classId);

        String type = cardObjectAttributesDto.getCardType();
        selectStrategy(type);

        return cardStrategy.create(cardObjectAttributesDto);
    }

    @Override
    public List<GetCardDto> getCards(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String classId = cardObjectAttributesDto.getClassId();
        throwIfClassIdIsNull(classId);

        String type = cardObjectAttributesDto.getCardType();
        selectStrategy(type);

        return cardStrategy.getAll(classId);
    }

    @Override
    public GenericJson getCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String objectId = cardObjectAttributesDto.getObjectId();
        throwIfObjectIdIsNull(objectId);

        String type = cardObjectAttributesDto.getCardType();
        selectStrategy(type);

        return cardStrategy.get(objectId);
    }

    @Override
    public GenericJson updateCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception {
        String objectId = cardObjectAttributesDto.getObjectId();
        throwIfObjectIdIsNull(objectId);

        String type = cardObjectAttributesDto.getCardType();
        selectStrategy(type);

        return cardStrategy.update(cardObjectAttributesDto);
    }

    private void selectStrategy(String type) throws Exception {
        if (isOfferType(type)) {
            cardStrategy = new OfferCardStrategy();
        } else if (isFlightType(type)) {

        } else if (isLoyaltyType(type)) {
            cardStrategy = new LoyaltyCardStrategy();
        } else {
            throw new Exception("Card type specified incorrectly.");
        }
    }

    private boolean isOfferType(String type) {
        return "offer".equals(type);
    }
    private boolean isFlightType(String type) {
        return "flight".equals(type);
    }
    private boolean isLoyaltyType(String type) {
        return "loyalty".equals(type);
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
