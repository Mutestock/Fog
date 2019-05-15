package logic;

import logic.offer.EmailHandler;
import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.util.LinkedList;
import logic.offer.OfferCalc;
import javax.mail.MessagingException;
import logic.SVG.SVGDrawerFromAbove;
import logic.SVG.SVGDrawerFromSide;
import logic.partslist.*;

public class PresentationToLogicImpl implements PresentationToLogic {

    private static LogicToData LOGIC_TO_DATA;

    public PresentationToLogicImpl() throws DataAccessException {
        LOGIC_TO_DATA = new LogicToDataImpl();
    }

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
        LinkedList<Part> fittingsAndScrews = FittingsAndScrewsCalc.calculateParts(carport, woodPackage);
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
            EmailHandler.sendMail(offer);
        } catch (MessagingException ex) {
            System.out.println("Shit happened in send email");
            ex.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) throws DataAccessException {
        return LOGIC_TO_DATA.getUser(username);
    }

    @Override
    public String getSVGDrawing(Carport carport, String angle) {
        switch (angle) {
            case "above":
                SVGDrawerFromAbove SVGdrawerAbove = new SVGDrawerFromAbove(carport);
                return SVGdrawerAbove.drawCarport();
            case "side":
                SVGDrawerFromSide SVGdrawerSide = new SVGDrawerFromSide(carport);
                return SVGdrawerSide.drawCarport();
            default:
                throw new AssertionError();
        }
    }
}
