package com.example.savetogpay.gpay;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.*;
import com.google.gson.JsonObject;

import java.util.Objects;

public class Services {

    private String EXISTS_MESSAGE = "No changes will be made when saved by link. " +
            "To update info, use update() or patch(). " +
            "For an example, see " +
            "https://developers.google.com/pay/passes/guides/get-started/implementing-the-api/engage-through-google-pay#update-state\n";

    private String NOT_EXIST_MESSAGE = "Will be inserted when user saves by link/button for first time\n";

    public Services() {

    }


    /*******************************
     *
     *
     *  See all the verticals: https://developers.google.com/pay/passes/guides/overview/basics/about-google-pay-api-for-passes
     *
     *******************************/
    public enum VerticalType {
        OFFER
        , EVENTTICKET
        , FLIGHT     // also referred to as Boarding Passes
        , GIFTCARD
        , LOYALTY
        , TRANSIT
    }

    /*******************************
    *
    *  Output to explain various status codes from a get API call
    *
    *  @param GenericJson getCallResponse - response from a get call
    *  @param String idType - identifier of type of get call.  "object" or "class"
    *  @param String id - unique identifier of Pass for given idType
    *  @param String checkClassId - optional. ClassId to check for if objectId exists, and idType.equals('object')
    *  @return void
    *
     *******************************/
    private void handleGetCallStatusCode(GenericJson getCallResponse, String idType, String id, String checkClassId) throws Exception{

        if ((int) getCallResponse.get("code") == 200) {  // Id resource exists for this issuer account
            System.out.println(String.format("%sId: (%s) already exists. %s", idType, id, EXISTS_MESSAGE));

            // for object get, do additional check
            if (idType.equals("object")) {
                // check if object's classId matches target classId
                String classIdOfObjectId = (String) getCallResponse.get("classId");
                if (!Objects.equals(classIdOfObjectId, checkClassId) && checkClassId != null) {
                    throw new Exception(String.format("the classId of inserted object is (%s). " +
                            "It does not match the target classId (%s). The saved object will not " +
                            "have the class properties you expect.", classIdOfObjectId, checkClassId));
                }
            }
        } else if ((int) getCallResponse.get("code") == 404) {  // Id resource does not exist for this issuer account
            System.out.println(String.format("%sId: (%s) does not exist. %s", idType, id, NOT_EXIST_MESSAGE));
        } else {
            throw new Exception(String.format("Issue with getting %s.", idType) + getCallResponse.toPrettyString());
        }

        return;
    }

    /*******************************
     *
     *  Output to explain various status codes from a insert API call
     *
     *  @param GenericJson getCallResponse - response from a get call
     *  @param String idType - identifier of type of get call.  "object" or "class"
     *  @param String id - unique identifier of Pass for given idType
     *  @param String checkClassId - optional. ClassId to check for if objectId exists, and idType.equals('object')
     *  @param VerticalType verticalType - optional. VerticalType to fetch ClassId of existing objectId.
     *  @return void
     *
     *******************************/
    private void handleInsertCallStatusCode(GenericJson insertCallResponse, String idType, String id, String checkClassId, VerticalType verticalType) throws Exception{
        if ((int)insertCallResponse.get("code") == 200) {
            System.out.println(String.format("%s id (%s) insertion success!\n" ,idType, id));
        }else if ((int)insertCallResponse.get("code") == 409) {  // Id resource exists for this issuer account
            System.out.println(String.format("%sId: (%s) already exists. %s", idType, id, EXISTS_MESSAGE));

            // for object insert, do additional check
            if (idType.equals("object")) {
                RestMethods restMethods = RestMethods.getInstance();
                GenericJson objectResponse = null;
                // get object definition
                switch (verticalType){
                    case OFFER:
                        objectResponse = restMethods.getOfferObject(id);
                        break;
                    case EVENTTICKET:
                        objectResponse = restMethods.getEventTicketObject(id);
                        break;
                    case FLIGHT:
                        objectResponse = restMethods.getFlightObject(id);
                        break;
                    case GIFTCARD:
                        objectResponse = restMethods.getGiftCardObject(id);
                        break;
                    case LOYALTY:
                        objectResponse = restMethods.getLoyaltyObject(id);
                    case TRANSIT:
                        objectResponse = restMethods.getTransitObject(id);
                        break;
                }
                // check if object's classId matches target classId
                String classIdOfObjectId = (String) objectResponse.get("classId");
                if (!Objects.equals(classIdOfObjectId, checkClassId) && checkClassId != null) {
                    throw new Exception(String.format("the classId of inserted object is (%s). " +
                            "It does not match the target classId (%s). The saved object will not " +
                            "have the class properties you expect.", classIdOfObjectId, checkClassId));
                }
            }
        } else {
            throw new Exception(String.format("%s insert issue.", idType) + insertCallResponse.toPrettyString());
        }

        return;
    }

    /*******************************
    *
    *  Generates a signed "skinny" JWT.
    *  2 REST calls are made:
    *    x1 pre-insert one classes
    *    x1 pre-insert one object which uses previously inserted class
    *
    *  This JWT can be used in JS web button.
    *  This is the shortest type of JWT; recommended for Android intents/redirects.
    *
    *  See https://developers.google.com/pay/passes/reference/v1/
    *
    *  @param VerticalType verticalType - define type of pass being generated
    *  @param String classId - The unique identifier for an class.
    *  @param String objectId - The unique identifier for an object.
    *  @return String signedJwt - a signed JWT
    *
     *******************************/

    public String makeSkinnyJwt(VerticalType verticalType, String classId, String objectId) {

        String signedJwt = null;
        ResourceDefinitions resourceDefinitions = ResourceDefinitions.getInstance();
        RestMethods restMethods = RestMethods.getInstance();
        GenericJson classResourcePayload = null;
        GenericJson objectResourcePayload = null;
        GenericJson classResponse = null;
        GenericJson objectResponse = null;

        try {
            // get class, object definitions, insert class and object (check class in Merchant center GUI: https://pay.google.com/gp/m/issuer/list)
            switch (verticalType){
                case OFFER:
//                    classResourcePayload = resourceDefinitions.makeOfferClassResource(classId);
//                    objectResourcePayload = resourceDefinitions.makeOfferObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertOfferClass((OfferClass)classResourcePayload);
                    objectResponse = restMethods.insertOfferObject((OfferObject)objectResourcePayload); 
                    break;
                case EVENTTICKET:
                    classResourcePayload = resourceDefinitions.makeEventTicketClassResource(classId);
                    objectResourcePayload = resourceDefinitions.makeEventTicketObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertEventTicketClass((EventTicketClass)classResourcePayload);
                    objectResponse = restMethods.insertEventTicketObject((EventTicketObject)objectResourcePayload); 
                    break;
                case FLIGHT:
                    classResourcePayload = resourceDefinitions.makeFlightClassResource(classId);
                    objectResourcePayload = resourceDefinitions.makeFlightObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertFlightClass((FlightClass)classResourcePayload);
                    objectResponse = restMethods.insertFlightObject((FlightObject)objectResourcePayload); 
                    break;
                case GIFTCARD:
                    classResourcePayload = resourceDefinitions.makeGiftCardClassResource(classId);
                    objectResourcePayload = resourceDefinitions.makeGiftCardObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertGiftCardClass((GiftCardClass)classResourcePayload);
                    objectResponse = restMethods.insertGiftCardObject((GiftCardObject)objectResourcePayload); 
                    break;
                case LOYALTY:
//                    classResourcePayload = resourceDefinitions.makeLoyaltyClassResource(classId);
                    objectResourcePayload = resourceDefinitions.makeLoyaltyObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertLoyaltyClass((LoyaltyClass)classResourcePayload);
                    objectResponse = restMethods.insertLoyaltyObject((LoyaltyObject)objectResourcePayload); 
                    break;
                case TRANSIT:
                    classResourcePayload = resourceDefinitions.makeTransitClassResource(classId);
                    objectResourcePayload = resourceDefinitions.makeTransitObjectResource(classId, objectId);
                    System.out.println("\nMaking REST call to insert class");
                    classResponse = restMethods.insertTransitClass((TransitClass)classResourcePayload);
                    objectResponse = restMethods.insertTransitObject((TransitObject)objectResourcePayload); 
                    break;
            }

            // continue based on insert response status. Check https://developers.google.com/pay/passes/reference/v1/statuscodes
            // check class insert response. Will print out if class insert succeeds or not. Throws error if class resource is malformed.
            handleInsertCallStatusCode(classResponse, "class", classId, null, null);

            // check object insert response. Will print out if object insert succeeds or not. Throws error if object resource is malformed, or if existing objectId's classId does not match the expected classId
            handleInsertCallStatusCode(objectResponse, "object", objectId, classId, verticalType);

            // put into JSON Web Token (JWT) format for Google Pay API for Passes
            Jwt googlePassJwt = new Jwt();
            // only need to add objectId in JWT because class and object definitions were pre-inserted via REST call
            JsonObject jwtPayload = new JsonObject();
            switch (verticalType){
                case OFFER:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addOfferObject(jwtPayload);
                    break;
                case EVENTTICKET:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addEventTicketObject(jwtPayload);
                    break;
                case FLIGHT:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addFlightObject(jwtPayload);
                    break;
                case GIFTCARD:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addGiftcardObject(jwtPayload);
                    break;
                case LOYALTY:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addLoyaltyObject(jwtPayload);
                    break;
                case TRANSIT:
                    jwtPayload.addProperty("id", objectId);
                    googlePassJwt.addTransitObject(jwtPayload);
                    break;
            }
            // sign JSON to make signed JWT
            signedJwt = googlePassJwt.generateSignedJwt();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return "skinny" JWT. Try putting it into save link.
        // See https://developers.google.com/pay/passes/guides/get-started/implementing-the-api/save-to-google-pay#add-link-to-email
        return signedJwt;
    }

//    public void updateObject(VerticalType verticalType) {
//
//        String signedJwt = null;
//        ResourceDefinitions resourceDefinitions = ResourceDefinitions.getInstance();
//        resourceDefinitions.setCardObjectAttributesDto(cardObjectAttributesDto);
//        RestMethods restMethods = RestMethods.getInstance();
//        GenericJson classResourcePayload = null;
//        GenericJson classResponse = null;
//        GenericJson objectResponse = null;
//
//        try {
//            switch (verticalType) {
//                case OFFER:
//                    String objectId = cardObjectAttributesDto.getObjectId();
//                    OfferObject existingObject = (OfferObject) restMethods.getOfferObject(objectId);
//                    OfferObject objectResourcePayload = resourceDefinitions.makeOfferObjectResource(existingObject.getClassId(), objectId);
//                    objectResponse = restMethods.updateOfferObject(objectId, objectResourcePayload);
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}