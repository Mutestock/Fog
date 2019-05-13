package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Shed;

public class SVGDrawerFromAbove {

    // all the following are Carport specific and numbers are therefore measured in cm
    private static double startX, startY,
            yEaves, leftEavesToPole, leftEaves, rightEaves, widthWithoutEaves,
            maxDistanceBetweenPoles, poleLineWidth, poleWidth,
            rBoardWidth, rBoardMaxDistance, 
            battenMaxDistance, battenWidth;
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
    }

    public String drawCarport() {
        Shed shed = carport.getShed();

        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\"" + (cmToDrawUnits(carport.getLength()) + 50) + "mm\" height=\"" + (cmToDrawUnits(carport.getWidth()) + 15) + "mm\">");

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

        double battenY = 0;
        sb.append(rectangle(poleLineWidth, battenY + carport.getWidth() / 2 - poleLineWidth / 2, poleLineWidth, carport.getLength() - poleLineWidth * 2));

        for (int i = 0; i < battenAmount; i++) {
            sb.append(rectangle(poleLineWidth, battenY, battenWidth, carport.getLength() - poleLineWidth * 2));
            sb.append(rectangle(poleLineWidth, battenY + carport.getWidth() / 2 + distanceToCenter, battenWidth, carport.getLength() - poleLineWidth * 2));
            battenY += battenWidth;
            battenY += battenAvgDistance;
        }
    }

    private void drawRaisedRoofEnds(StringBuilder sb) {
        double pieceLength = carport.getWidth() / 2;
        sb.append(rectangle(0, 0, pieceLength, poleLineWidth));
        sb.append(rectangle(carport.getLength() - poleLineWidth, 0, pieceLength, poleLineWidth));
        sb.append(rectangle(0, pieceLength, pieceLength, poleLineWidth));
        sb.append(rectangle(carport.getLength() - poleLineWidth, pieceLength, pieceLength, poleLineWidth));
    }

    private void drawFlatRoofOutline(StringBuilder sb) {
        sb.append(rectangle(0, 0, carport.getWidth(), carport.getLength()));
    }

    private void drawPoleLinesAlongLength(StringBuilder sb) {
        Shed shed = carport.getShed();

        double poleLineX = 0;
        double poleLineLength = carport.getLength();
        if (carport.getRoof().getRaised()) {
            poleLineX += leftEaves;
            poleLineLength -= rightEaves;
            poleLineLength -= leftEaves;
        }

        sb.append(rectangle(poleLineX, yEaves, poleLineWidth, poleLineLength, 1.75));
        sb.append(rectangle(poleLineX, carport.getWidth() - yEaves - poleLineWidth, poleLineWidth, poleLineLength, 1.75));

        if (shed != null && shed.getWidth() < widthWithoutEaves - 5) { //-5 because it's probably close enough to entire width
            sb.append(rectangle(poleLineX, yEaves + shed.getWidth() - poleLineWidth, poleLineWidth, poleLineLength, 1.75));
        }
    }

    private double drawRafterBoards(StringBuilder sb) {

        int rBoardAmount = (int) Math.ceil(carport.getLength() / (rBoardMaxDistance + rBoardWidth));
        double rBoardAvailableSpace = carport.getLength() - rBoardWidth * rBoardAmount;
        double rBoardAvgDistance = rBoardAvailableSpace / (rBoardAmount - 1);

        double rBoardX = 0;
        if (carport.getRoof().getRaised()) { // if there's a roof, the roof ends replaces the first and the last rafter board
            rBoardX += rBoardAvgDistance + rBoardWidth;
            rBoardAmount -= 2;
        }
        for (int i = 0; i < rBoardAmount; i++) {
            sb.append(rectangle(rBoardX, 0, carport.getWidth(), rBoardWidth));
            rBoardX += rBoardWidth;
            rBoardX += rBoardAvgDistance;
        }
        return rBoardAvgDistance;
    }

    private void drawShedOutline(StringBuilder sb) {
        Shed shed = carport.getShed();

        double shedX = carport.getLength() - shed.getLength() - rightEaves;
        double shedY = yEaves;
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

        double poleX = leftEavesToPole;
        int poleCount = polesOnOneSide;
        if (shed != null) {
            poleCount--;
        }
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, yEaves, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            sb.append(rectangle(poleX, carport.getWidth() - yEaves - poleWidth, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
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

        double poleX = carport.getLength() - rightEaves;
        double poleY = yEaves;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        poleY = carport.getWidth() - yEaves - poleWidth;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));

        if (shed.getLength() > maxDistanceBetweenPoles) {
            poleX = carport.getLength() - rightEaves - shed.getLength() / 2 - poleWidth / 2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            poleY = yEaves;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        }
    }

    private void drawExtraPoles(StringBuilder sb, int poleCount, double poleAvgDistance) {
        Shed shed = carport.getShed();

        double poleX = carport.getLength() - rightEaves;
        double poleY = yEaves + shed.getWidth() - poleWidth;

        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));

        if (shed.getLength() > maxDistanceBetweenPoles) {
            poleX -= shed.getLength() / 2;
            poleX -= poleWidth / 2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
        }

        poleX = leftEavesToPole;
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 3, "rgb(200,200,200)"));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }
    }

    private void drawCrossLines(StringBuilder sb, double rBoardDistance) {
        Shed shed = carport.getShed();

        double x1 = rBoardDistance + rBoardWidth;
        double y1 = yEaves + poleLineWidth;
        double y2 = carport.getWidth() - yEaves - poleLineWidth;
        double x2 = carport.getLength() - rightEaves - poleLineWidth;
        if (shed != null) {
            x2 = carport.getLength() - rightEaves - shed.getLength();
        }
        sb.append(line(x1, y1, x2, y2, 1.5));
        sb.append(line(x1, y2, x2, y1, 1.5));
    }

    private void drawMeasurements(StringBuilder sb) {
        Shed shed = carport.getShed();
        if (shed != null) {
            // shed length
            sb.append(line(carport.getLength() - shed.getLength() - rightEaves, carport.getWidth() + 10.0, carport.getLength() - rightEaves, carport.getWidth() + 10.0, 2));
            sb.append(text(carport.getLength() - shed.getLength() / 2 - rightEaves - 25, carport.getWidth() + 30, "" + shed.getLength()));
            // shed width
            sb.append(line(carport.getLength() + 10, yEaves, carport.getLength() + 10, yEaves + shed.getWidth(), 2));
            sb.append(text(carport.getLength() + 20, yEaves + shed.getWidth() / 2, "" + shed.getWidth()));
            // carport width
            sb.append(line(carport.getLength() + 90, 0, carport.getLength() + 90, carport.getWidth(), 2));
            sb.append(text(carport.getLength() + 100, carport.getWidth() / 2, "" + carport.getWidth()));
        } else {
            // carport width
            sb.append(line(carport.getLength() + 10, 0, carport.getLength() + 10, carport.getWidth(), 2));
            sb.append(text(carport.getLength() + 20, carport.getWidth() / 2, "" + carport.getWidth()));
        }
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
        x = cmToDrawUnits(x+startX);
        y = cmToDrawUnits(y+startY);
        width = cmToDrawUnits(width);
        length = cmToDrawUnits(length);
        String text = "<rect x=\"" + x + "mm\" y=\"" + y + "mm\" height=\"" + width + "mm\" width=\"" + length + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill:" + color + "\"/>";
        return text;
    }

    private String line(double x1, double y1, double x2, double y2, double thickness) {
        x1 = cmToDrawUnits(x1+startX);
        y1 = cmToDrawUnits(y1+startY);
        x2 = cmToDrawUnits(x2+startX);
        y2 = cmToDrawUnits(y2+startY);
        String text = "<line stroke-dasharray=\"5, 5\" x1=\"" + x1 + "mm\" y1=\"" + y1 + "mm\" x2=\"" + x2 + "mm\" y2=\"" + y2 + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill: none\"/>";
        return text;
    }

    private String text(double x, double y, String measurement) {
        x = cmToDrawUnits(x+startX);
        y = cmToDrawUnits(y+startY);
        return "<text x=\"" + x + "mm\" y=\"" + y + "mm\" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + measurement + " cm" + "</text>";
    }

    private double cmToDrawUnits(double cm) {
        return cm * 0.25; // measure: 1 cm in real life = 0,25 mm on paper
    }
}
