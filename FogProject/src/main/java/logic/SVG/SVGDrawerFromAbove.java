package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Shed;

public class SVGDrawerFromAbove {

    // all the following are Carport specific and numbers are therefore measured in cm
    private static double startX, startY, yEaves, leftEaves, rightEaves, maxDistanceBetweenPoles, widthWithoutEaves, poleWidth, rBoardWidth, rBoardMaxDistance, maxWidthBeforeExtraSupport;

    public SVGDrawerFromAbove() {
        startX = 10;
        startY = 10;

        yEaves = 30;
        rightEaves = 5;

        poleWidth = 7.5;
        maxDistanceBetweenPoles = 480;
        maxWidthBeforeExtraSupport = 500;
        rBoardWidth = 2;
        rBoardMaxDistance = 20;
    }

    public String drawCarportFlatRoof(Carport carport) {
        Shed shed = carport.getShed();

        leftEaves = carport.getLength() * 0.15;
        widthWithoutEaves = carport.getWidth() - 2 * yEaves;

        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\""+(cmToDrawUnits(carport.getLength())+5)+"mm\" height=\""+(cmToDrawUnits(carport.getWidth())+5)+"mm\">");
        
        drawCarportOutline(sb, carport);
        drawPoleLinesAlongLength(sb, carport);
        double rBoardDistance = drawRafterBoards(sb, carport);
        drawCarportPoles(sb, carport);
        if (shed != null) {
            drawShedOutline(sb, carport);
        }
        drawCrossLines(sb, carport, rBoardDistance);

        sb.append("</svg>");
        return sb.toString();
    }

    private void drawCarportOutline(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX, startY, carport.getWidth(), carport.getLength()));
    }

    private void drawPoleLinesAlongLength(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();
        double poleLineWidth = 5.5;

        sb.append(rectangle(startX, startY + yEaves, poleLineWidth, carport.getLength()));
        sb.append(rectangle(startX, startY + carport.getWidth() - yEaves - poleLineWidth, poleLineWidth, carport.getLength()));

        if (shed != null && shed.getWidth() < widthWithoutEaves - 5) { //-5 because it's probably close enough to entire width
            sb.append(rectangle(startX, startY + yEaves + shed.getWidth() - poleLineWidth, poleLineWidth, carport.getLength()));
        }
    }

    private double drawRafterBoards(StringBuilder sb, Carport carport) {

        int rBoardAmount = (int) Math.ceil(carport.getLength() / (rBoardMaxDistance + rBoardWidth));
        double rBoardAvailableSpace = carport.getLength() - rBoardWidth * rBoardAmount;
        double rBoardAvgDistance = rBoardAvailableSpace / (rBoardAmount - 1);

        double rBoardX = startX;
        for (int i = 0; i < rBoardAmount; i++) {
            sb.append(rectangle(rBoardX, startY, carport.getWidth(), rBoardWidth));
            rBoardX += rBoardWidth;
            rBoardX += rBoardAvgDistance;
        }
        return rBoardAvgDistance;
    }

    private void drawShedOutline(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();

        double shedX = startX + carport.getLength() - shed.getLength() - rightEaves;
        double shedY = startY + yEaves;
        sb.append(rectangle(shedX, shedY, shed.getWidth(), shed.getLength(), 3));
    }

    private void drawCarportPoles(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();
        
        double availableSpace = carport.getLength() - leftEaves - rightEaves;
        if (shed != null) {
            availableSpace -= shed.getLength();
        }
//        int polesOnOneSide = (int) Math.ceil(availableSpace / (maxDistanceBetweenPoles + poleWidth));
        int polesOnOneSide = 0;
        if (availableSpace - leftEaves - rightEaves > maxDistanceBetweenPoles) {
            polesOnOneSide += 3;
        } else {
            polesOnOneSide += 2;
        }
        double leftOverSpace = availableSpace - poleWidth * polesOnOneSide;
        double poleAvgDistance = leftOverSpace / (polesOnOneSide - 1);

        double poleX = startX + leftEaves;
        int poleCount = polesOnOneSide;
        if (shed != null){
            poleCount--; 
        }
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, startY + yEaves, poleWidth, poleWidth, 2));
            sb.append(rectangle(poleX, startY + carport.getWidth() - yEaves - poleWidth, poleWidth, poleWidth, 2));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }
        
        if (shed != null) {
            drawShedPoles(sb, carport);
        }
        if (shed != null && shed.getWidth() < widthWithoutEaves - 5) { //-5 because it's probably close enough to entire width
            drawExtraPoles(sb, carport, poleCount, poleAvgDistance);
        }
    }

    private void drawShedPoles(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();

        double poleX = startX + carport.getLength() - rightEaves;
        double poleY = startY + yEaves;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 2));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 2));
        poleY = startY + carport.getWidth() - yEaves - poleWidth;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 2));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 2));
        
        if (shed != null && shed.getLength() > maxDistanceBetweenPoles){
            poleX = startX+carport.getLength()-rightEaves-shed.getLength()/2-poleWidth/2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 2));
            poleY = startY + yEaves;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 2));
        }
    }

    private void drawExtraPoles(StringBuilder sb, Carport carport, int poleCount, double poleAvgDistance) {
        Shed shed = carport.getShed();

        double poleX = startX + carport.getLength() - rightEaves;
        double poleY = startY + yEaves + shed.getWidth() - poleWidth;
        
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 2));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 2));
        
        if (shed != null && shed.getLength() > maxDistanceBetweenPoles){
            poleX -= shed.getLength()/2-poleWidth/2;
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 2));
        }
        
        poleX = startX + leftEaves;
        for (int i = 0; i < poleCount; i++) {
            sb.append(rectangle(poleX, poleY, poleWidth, poleWidth, 2));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }
    }

    private void drawCrossLines(StringBuilder sb, Carport carport, double rBoardDistance) {
        Shed shed = carport.getShed();

        double x1 = startX + rBoardDistance + rBoardWidth;
        double y1 = startY + yEaves + poleWidth;
        double y2 = startY + carport.getWidth() - yEaves - poleWidth;
        double x2 = startX + carport.getLength() - rightEaves;
        if (shed == null) {
            sb.append(line(x1, y1, x2, y2, 1.5));
            sb.append(line(x1, y2, x2, y1, 1.5));
        } else {
            x2 -= shed.getLength();
            sb.append(line(x1, y1, x2, y2, 1.5));
            sb.append(line(x1, y2, x2, y1, 1.5));
        }
    }

    // ==============================================
    // ==============================================
    // ==============================================
    private String rectangle(double x, double y, double width, double length) {
        return rectangle(x, y, width, length, 1);
    }

    private String rectangle(double x, double y, double width, double length, double thickness) {
        x = cmToDrawUnits(x);
        y = cmToDrawUnits(y);
        width = cmToDrawUnits(width);
        length = cmToDrawUnits(length);
        String text = "<rect x=\"" + x + "mm\" y=\"" + y + "mm\" height=\"" + width + "mm\" width=\"" + length + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill: none\"/>";
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
