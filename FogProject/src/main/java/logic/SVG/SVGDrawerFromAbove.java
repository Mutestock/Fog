package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Shed;

public class SVGDrawerFromAbove {

    // all the following are Carport specific and numbers are therefore measured in cm
    private static double startX, startY, eaves, widthWithoutEaves, poleWidth, rBoardWidth, maxWidthBeforeExtraSupport;
    
    public SVGDrawerFromAbove(){
        startX = 10;
        startY = 10;
        
        eaves = 30;
        poleWidth = 7.5;
        maxWidthBeforeExtraSupport = 300;
        rBoardWidth = 2;
    }

    public String drawCarportFlatRoof(Carport carport) {
        Shed shed = carport.getShed();
        
        widthWithoutEaves = carport.getWidth() - 2 * eaves;

        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\"500mm\" height=\"500mm\">");

        drawCarportOutline(sb, carport);
        drawPoleLinesAlongLength(sb, carport);
        double rBoardDistance = drawRafterBoards(sb, carport);
        drawCarportPoles(sb, carport);
        if (shed != null) {
            drawShedOutline(sb, carport, rBoardDistance);
            drawShedPoles(sb, carport, rBoardDistance);
        }
        drawCrossLines(sb, carport, rBoardDistance);

        sb.append("</svg>");
        return sb.toString();
    }
    
    private void drawCarportOutline(StringBuilder sb, Carport carport){
        sb.append(rectangle(startX, startY, carport.getWidth(), carport.getLength()));
    }
    
    private void drawPoleLinesAlongLength(StringBuilder sb, Carport carport){
        sb.append(rectangle(startX, startY + eaves, 5.5, carport.getLength()));
        sb.append(rectangle(startX, startY + carport.getWidth() - eaves - 5.5, 5.5, carport.getLength()));
    }
    
    private double drawRafterBoards(StringBuilder sb, Carport carport){

        double rBoardMaxDistance = 20;
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

    private void drawShedOutline(StringBuilder sb, Carport carport, double xEaves) {
        Shed shed = carport.getShed();
        
        double shedX = startX + carport.getLength() - shed.getLength() - xEaves;
        double shedY = startY; // WHERE SHOULD WE PLACE THE SHED??? WHERE SHOULD WE PLACE THE SHED??? WHERE SHOULD WE PLACE THE SHED???
        if (shed.getWidth() > widthWithoutEaves) {
            double spaceLeftInWidth = carport.getWidth() - shed.getWidth();
            shedY += spaceLeftInWidth / 2;
        } else {
            shedY += eaves;
        }
        sb.append(rectangle(shedX, shedY, shed.getWidth(), shed.getLength(), 3));
    }

    private void drawCarportPoles(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();

        double maxDistance = 150;
        double carportLength = carport.getLength();
        if (shed != null) {
            carportLength -= shed.getLength();
        }
        double availableSpace = carportLength - carportLength * 0.24 - carportLength * 0.185;
        int polesOnOneSide = (int) Math.ceil(availableSpace / (maxDistance+poleWidth));
        double leftOverSpace = availableSpace - poleWidth * polesOnOneSide;
        double poleAvgDistance = leftOverSpace / (polesOnOneSide - 1);

        double poleX = startX + carportLength * 0.185;
        for (int i = 0; i < polesOnOneSide; i++) {
            sb.append(rectangle(poleX, startY + eaves, poleWidth, poleWidth, 2));
            sb.append(rectangle(poleX, startY + carport.getWidth() - eaves - poleWidth, poleWidth, poleWidth, 2));
            poleX += poleWidth;
            poleX += poleAvgDistance;
        }
        
        if (carport.getWidth() > maxWidthBeforeExtraSupport) {
            sb.append(rectangle(startX, carport.getWidth()/2, poleWidth, poleWidth, 2));
        }
    }
    
    private void drawShedPoles(StringBuilder sb, Carport carport, double xEaves) {
        Shed shed = carport.getShed();
        
        double poleX = startX+carport.getLength() - xEaves;
        double poleY = startY+eaves;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 2));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 2));
        poleY = startY+carport.getWidth()-eaves-poleWidth;
        sb.append(rectangle(poleX - shed.getLength(), poleY, poleWidth, poleWidth, 2));
        sb.append(rectangle(poleX - poleWidth, poleY, poleWidth, poleWidth, 2));
        
        if (carport.getWidth() > maxWidthBeforeExtraSupport){
            sb.append(rectangle(poleX - shed.getLength(), carport.getWidth()/2, poleWidth, poleWidth, 2));
            sb.append(rectangle(poleX - poleWidth, carport.getWidth()/2, poleWidth, poleWidth, 2));
        }
    }
    
    private void drawCrossLines(StringBuilder sb, Carport carport, double xEaves) {
        Shed shed = carport.getShed();
        
        double x1 = startX+xEaves+rBoardWidth;
        double y1 = startY+eaves+poleWidth;
        double y2 = startY+carport.getWidth()-eaves-poleWidth;
        double x2 = startX+carport.getLength()-xEaves;
        if (shed == null){
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
        return cm * 0.3; // measure: 1 cm in real life = 0,2 mm on paper
    }
}
