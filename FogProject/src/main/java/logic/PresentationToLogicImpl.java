package logic;

import logic.offer.EmailHandler;
import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.util.LinkedList;
import logic.offer.OfferCalc;
import javax.mail.MessagingException;
import logic.partslist.*;

public class PresentationToLogicImpl implements PresentationToLogic {

    private static final LogicToData LOGIC_TO_DATA = new LogicToDataImpl();

    @Override
    public void sendRequest(Request request) throws DataAccessException {
        LOGIC_TO_DATA.saveRequest(request);
    }

    @Override
    public LinkedList<Request> getRequests(String filter) throws DataAccessException {
        return LOGIC_TO_DATA.getRequests(filter);
    }

    @Override
    public Request getRequest(int id) throws DataAccessException {
        return LOGIC_TO_DATA.getRequest(id);
    }

    @Override
    public PartsList getPartsList(Carport carport) {
        LinkedList<Part> woodPackage = WoodCalc.calculateParts(carport);
        LinkedList<Part> roofPackage = RoofCalc.calculateParts(carport);
        LinkedList<Part> fittingsAndScrews = FittingsAndScrewsCalc.calculateParts(carport);
        return new PartsList(woodPackage, roofPackage, fittingsAndScrews);
    }

    @Override
    public Offer getOffer(PartsList parts, Request request) throws DataAccessException {
        if (request.hasReceivedOffer()) {
            return LOGIC_TO_DATA.getOffer(request.getId());
        } else {
            return OfferCalc.generateOffer(parts, request);
        }
    }

    @Override
    public void sendOffer(Offer offer) throws DataAccessException {
        LOGIC_TO_DATA.saveOffer(offer);
        try {
            EmailHandler.mailSend(offer);
        } catch (MessagingException ex) {
            System.out.println("Shit happened in send email");
            ex.printStackTrace();
        }
    }
}
