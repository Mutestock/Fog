package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Shed;

public class SVGDrawerFromAbove {

    // all the following are Carport specific and numbers are therefore measured in cm
    private static double startX, startY,
            yEaves, leftEavesToPole, leftEaves, rightEaves, widthWithoutEaves,
            maxDistanceBetweenPoles, poleLineWidth, poleWidth,
            rBoardWidth, rBoardMaxDistance, battenMaxDistance, battenWidth,
            maxWidthBeforeExtraSupport;
    private final Carport carport;

    public SVGDrawerFromAbove(Carport carport) {
        this.carport = carport;
        startX = 10;
        startY = 10;

        yEaves = 30;
        rightEaves = 15;
        leftEaves = 15;
        leftEavesToPole = carport.getLength() * 0.15;
        widthWithoutEaves = carport.getWidth() - 2 * yEaves;

        poleLineWidth = 5.5;
        poleWidth = 15;
        maxDistanceBetweenPoles = 480;

        rBoardWidth = 2;
        rBoardMaxDistance = 20;
        battenWidth = 4;
        battenMaxDistance = 30;

        maxWidthBeforeExtraSupport = 500;
    }

    public String drawCarport() {
        Shed shed = carport.getShed();

        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\"" + (cmToDrawUnits(carport.getLength()) + 5) + "mm\" height=\"" + (cmToDrawUnits(carport.getWidth()) + 5) + "mm\">");

        drawFlatRoofOutline(sb); // will be overdrawn by later methods, if raised roof
        if (shed != null) {
            drawShedOutline(sb);
        }
        drawPoleLinesAlongLength(sb);
        double rBoardDistance = drawRafterBoards(sb);

        if (carport.getRoof().getRaised()) {
            drawRaisedRoofEnds(sb);
            drawRaisedRoofBattens(sb);
        } else {
            drawCrossLines(sb, rBoardDistance);
        }
        drawCarportPoles(sb);
        
        drawMeasurements(sb);
        sb.append("</svg>");
        return sb.toString();
    }

    private void drawRaisedRoofBattens(StringBuilder sb) {
        double distanceToCenter = 10;
        int battenAmount = (int) Math.ceil((carport.getWidth() / 2 - distanceToCenter) / (battenMaxDistance + battenWidth));
        double battenAvailableSpace = carport.getWidth() / 2 - distanceToCenter - battenWidth * battenAmount;
        double battenAvgDistance = battenAvailableSpace / (battenAmount - 1);

        double battenY = startY;
        sb.append(rectangle(startX + poleLineWidth, battenY+carport.getWidth()/2-poleLineWidth/2, poleLineWidth, carport.getLength() - poleLineWidth * 2));
        
        for (int i = 0; i < battenAmount; i++) {
            sb.append(rectangle(startX + poleLineWidth, battenY, battenWidth, carport.getLength() - poleLineWidth * 2));
            sb.append(rectangle(startX + poleLineWidth, battenY+carport.getWidth()/2+distanceToCenter, battenWidth, carport.getLength() - poleLineWidth * 2));
            battenY += battenWidth;
            battenY += battenAvgDistance;
        }
    }

    private void drawRaisedRoofEnds(StringBuilder sb) {
        double pieceLength = carport.getWidth() / 2;
        sb.append(rectangle(startX, startY, pieceLength, poleLineWidth));
        sb.append(rectangle(startX + carport.getLength() - poleLineWidth, startY, pieceLength, poleLineWidth));
        sb.append(rectangle(startX, startY + pieceLength, pieceLength, poleLineWidth));
        sb.append(rectangle(startX + carport.getLength() - poleLineWidth, startY + pieceLength, pieceLength, poleLineWidth));
    }

    private void drawFlatRoofOutline(StringBuilder sb) {
        sb.append(rectangle(startX, startY, carport.getWidth(), carport.getLength()));
    }

    private void drawPoleLinesAlongLength(StringBuilder sb) {
        Shed shed = carport.getShed();

        double poleLineX = startX;
        double poleLineLength = carport.getLength();
        if (carport.getRoof().getRaised()) {
            poleLineX += leftEaves;
            poleLineLength -= rightEaves;
            poleLineLength -= leftEaves;
        }

        sb.append(rectangle(poleLineX, startY + yEaves, poleLineWidth, poleLineLength, 1.75));
        sb.append(rectangle(poleLineX, startY + carport.getWidth() - yEaves - poleLineWidth, poleLineWidth, poleLineLength, 1.75));

        if (shed != null && shed.getWidth() < widthWithoutEaves - 5) { //-5 because it's probably close enough to entire width
            sb.append(rectangle(poleLineX, startY + yEaves + shed.getWidth() - poleLineWidth, poleLineWidth, poleLineLength, 1.75));
        }
    }

    private double drawRafterBoards(StringBuilder sb) {

        int rBoardAmount = (int) Math.ceil(carport.getLength() / (rBoardMaxDistance + rBoardWidth));
        double rBoardAvailableSpace = carport.getLength() - rBoardWidth * rBoardAmount;
        double rBoardAvgDistance = rBoardAvailableSpace / (rBoardAmount - 1);

        double rBoardX = startX;
        if (carport.getRoof().getRaised()) { // if there's a roof, the roof ends replaces the first and the last rafter board
            rBoardX += rBoardAvgDistance + rBoardWidth;
            rBoardAmount -= 2;
        }
        for (int i = 0; i < rBoardAmount; i++) {
            sb.append(rectangle(rBoardX, startY, carport.getWidth(), rBoardWidth));
            rBoardX += rBoardWidth;
            rBoardX += rBoardAvgDistance;
        }
        return rBoardAvgDistance;
    }

    private void drawShedOutline(StringBuilder sb) {
        Shed shed = carport.getShed();

        double shedX = startX + carport.getLength() - shed.getLength() - rightEaves;
        double shedY = startY + yEaves;
        sb.append(rectangle(shedX, shedY, shed.getWidth(), shed.getLength(), 5, "rgb(250,250,250)"));
    }

    private void drawCarportPoles(StringBuilder sb) {
        Shed shed = carport.getShed();

        double availableSpace = carport.getLength() - leftEavesToPole - rightEaves;
        if (shed != null) {
            availableSpace -= shed.getLength();
        }
//        int polesOnOneSide = (int) Math.ceil(availableSpace / (maxDistanceBetweenPoles + poleWidth));
        int polesOnOneSide = 0;
        if (availableSpace - leftEavesToPole - rightEaves > maxDistanceBetweenPoles) {
            polesOnOneSide += 3;
        } else {
            polesOnOneSide += 2;
        }
        
        double leftOverSpace = availableSpace - poleWidth * polesOnOneSide;
        double poleAvgDistance = leftOverSpace / (polesOnOneSide - 1);

        double poleX = startX + leftEavesToPole;
        int poleCount = polesOnOneSide;
        if (shed != null) {
            poleCount--;
        }
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, startY + yEaves, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            sb.append(rectangle(poleX, startY + carport.getWidth() - yEaves - poleWidth, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }

        if (shed != null) {
            drawShedPoles(sb);
        }
        if (shed != null && shed.getWidth() < widthWithoutEaves - 5) { //-5 because it's probably close enough to entire width
            drawExtraPoles(sb, poleCount, poleAvgDistance);
        }
    }

    private void drawShedPoles(StringBuilder sb) {
        Shed shed = carport.getShed();

        double poleX = startX + carport.getLength() - rightEaves;
        double poleY = startY + yEaves;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        poleY = startY + carport.getWidth() - yEaves - poleWidth;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));

        if (shed.getLength() > maxDistanceBetweenPoles) {
            poleX = startX + carport.getLength() - rightEaves - shed.getLength() / 2 - poleWidth / 2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            poleY = startY + yEaves;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        }
    }

    private void drawExtraPoles(StringBuilder sb, int poleCount, double poleAvgDistance) {
        Shed shed = carport.getShed();

        double poleX = startX + carport.getLength() - rightEaves;
        double poleY = startY + yEaves + shed.getWidth() - poleWidth;

        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));

        if (shed.getLength() > maxDistanceBetweenPoles) {
            poleX -= shed.getLength() / 2 - poleWidth / 2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3));
        }

        poleX = startX + leftEavesToPole;
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }
    }

    private void drawCrossLines(StringBuilder sb, double rBoardDistance) {
        Shed shed = carport.getShed();

        double x1 = startX + rBoardDistance + rBoardWidth;
        double y1 = startY + yEaves + poleLineWidth;
        double y2 = startY + carport.getWidth() - yEaves - poleLineWidth;
        double x2 = startX + carport.getLength() - rightEaves - poleLineWidth;
        if (shed != null) {
            x2 = startX + carport.getLength() - rightEaves - shed.getLength();
        }
        sb.append(line(x1, y1, x2, y2, 1.5));
        sb.append(line(x1, y2, x2, y1, 1.5));
    }
    
    private void drawMeasurements(StringBuilder sb){
        
    }

    // ==============================================
    // ==============================================
    // ==============================================
    private String rectangle(double x, double y, double width, double length) {
        return rectangle(x, y, width, length, 1);
    }

    private String rectangle(double x, double y, double width, double length, double thickness) {
        return rectangle(x, y, width, length, thickness, "rgb(255,255,255)");
    }
    
    private String rectangle(double x, double y, double width, double length, double thickness, String color) {
        x = cmToDrawUnits(x);
        y = cmToDrawUnits(y);
        width = cmToDrawUnits(width);
        length = cmToDrawUnits(length);
        String text = "<rect x=\"" + x + "mm\" y=\"" + y + "mm\" height=\"" + width + "mm\" width=\"" + length + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill:"+color+"\"/>";
        return text;
    }

    private String line(double x1, double y1, double x2, double y2, double thickness) {
        x1 = cmToDrawUnits(x1);
        y1 = cmToDrawUnits(y1);
        x2 = cmToDrawUnits(x2);
        y2 = cmToDrawUnits(y2);
        String text = "<line stroke-dasharray=\"5, 5\" x1=\"" + x1 + "mm\" y1=\"" + y1 + "mm\" x2=\"" + x2 + "mm\" y2=\"" + y2 + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill: none\"/>";
        return text;
    }

    private double cmToDrawUnits(double cm) {
        return cm * 0.25; // measure: 1 cm in real life = 0,2 mm on paper
    }
}
