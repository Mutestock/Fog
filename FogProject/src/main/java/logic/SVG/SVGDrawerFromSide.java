package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Roof;
import data.help_classes.Shed;

/**
 *
 * @author Gamer
 */
public class SVGDrawerFromSide {
    
    private static double startX, startY, yEaves, leftEaves, rightEaves, maxDistanceBetweenPoles, widthWithoutEaves, poleWidth, rBoardWidth, rBoardMaxDistance, maxWidthBeforeExtraSupport;

    
    public SVGDrawerFromSide() {
        startX = 10;
        startY = 10;
       
        rightEaves = 5;

        poleWidth = 7.5;
        maxDistanceBetweenPoles = 480;
        maxWidthBeforeExtraSupport = 500;
        rBoardWidth = 2;
        rBoardMaxDistance = 20;
    }
    
    
    public String drawCarportFlatRoofSide(Carport carport) {
        Shed shed = carport.getShed();

        leftEaves = carport.getLength() * 0.15;
        widthWithoutEaves = carport.getWidth() - 2 * yEaves;

        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width="+carport.getWidth()+"height=\"2mm\">");

       
        drawCarportPoles(sb, carport);
        drawCarportRoof(sb, carport);
        drawCarportLengthLine(sb, carport);
        if (carport.getShed() != null) {
            drawShedCoverings(sb, carport);
        }
        
        sb.append("</svg>");
        return sb.toString();
    }
    
    
    private void drawCarportOutline(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX, startY, carport.getHeight(), carport.getLength()));
    }
    
    private void drawCarportRoof(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX, startY+13, 9, carport.getLength()));
        /*if (carport.getRoof().getRaised()) {
            Roof roof = carport.getRoof();
            double angleOfRoof = 90-roof.getSlope();
            double bigA = carport.getWidth()/2;
            double roofHeight = Math.sin(Math.toRadians(roof.getSlope()))*(bigA/Math.sin(Math.toRadians((angleOfRoof))));
            System.out.println(roofHeight);
            sb.append(rectangle(startX, startY,roofHeight, carport.getLength()));
        }
        else {*/
            sb.append(rectangle(startX, startY, 9, carport.getLength()));
        //}
    }
    
    private void drawCarportPoles(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX+leftEaves, startY+22, carport.getHeight()-22, poleWidth));
        sb.append(rectangle(carport.getLength()-rightEaves, startY+22, carport.getHeight()-22, poleWidth));
        
        if (carport.getShed() != null) {
            Shed shed = carport.getShed();
            double shedPole = carport.getLength()-rightEaves-shed.getLength();
            double firstPole = startX+leftEaves;
            sb.append(rectangle(shedPole, startY+22, carport.getHeight()-22, poleWidth));
            if (shedPole-firstPole > maxDistanceBetweenPoles){
                sb.append(rectangle(((startX+leftEaves+shedPole)/2), startY+22, carport.getHeight()-22, poleWidth));
            }
        }
        else{
            double lastPole = carport.getLength()-rightEaves;
            double firstPole = startX+leftEaves;
            if (lastPole-firstPole > maxDistanceBetweenPoles){
                sb.append(rectangle(((startX+leftEaves+lastPole)/2), startY+22, carport.getHeight()-22, poleWidth));
            }
        }
    }
    
    private void drawCarportLengthLine(StringBuilder sb, Carport carport) {
        sb.append(line(startX, startY+carport.getHeight()+5,carport.getLength()+10,startY+carport.getHeight()+5,2));
        sb.append("<text x="+ ((carport.getLength()/2)-10) +" y="+ 230 +" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + carport.getLength() 
                + " cm" + "</text>");
    }
    
    private void drawShedCoverings(StringBuilder sb, Carport carport) {
        Shed shed = carport.getShed();
        double shedPole = carport.getLength()-rightEaves-shed.getLength();
        double amount = Math.floor((carport.getLength()-rightEaves-shed.getLength()-poleWidth)/(poleWidth+0)+1);
        double distance = (carport.getLength()-rightEaves-shed.getLength() - amount*poleWidth) / (amount-1);
        
        for (double i = 0; i < (carport.getLength()-rightEaves)-(carport.getLength()-rightEaves-shed.getLength()); i = i + poleWidth + distance) {
            sb.append(rectangle(i+shedPole+2, startY+27, carport.getHeight()-28, poleWidth));
        }
    }
    
    
    
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
