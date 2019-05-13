package logic.SVG;

import data.help_classes.Carport;
import data.help_classes.Roof;
import data.help_classes.Shed;

/**
 *
 * @author Gamer
 */
public class SVGDrawerFromSide {
    
    private static double startX, startY, leftEaves, rightEaves, maxDistanceBetweenPoles, poleWidth;

    
    public SVGDrawerFromSide() {
        startX = 10;
        startY = 10;
       
        rightEaves = 5;

        poleWidth = 7.5;
        maxDistanceBetweenPoles = 480;
    }
    
    
    public String drawCarportFlatRoofSide(Carport carport) {
        double roofHeight = 0;
        
        leftEaves = carport.getLength() * 0.15;
        if (carport.getRoof().getRaised()) {
            Roof roof = carport.getRoof();
            double angleOfRoof = 90-roof.getSlope();
            double bigA = carport.getWidth()/2;
            roofHeight = Math.sin(Math.toRadians(roof.getSlope()))*(bigA/Math.sin(Math.toRadians((angleOfRoof))));
        }
        
        
        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\""+(cmToDrawUnits(carport.getLength())+5)+"mm\" height=\""+(cmToDrawUnits(carport.getHeight())+100+roofHeight)+"mm\">");
       
        
        if (carport.getRoof().getRaised()) {
            drawCarportRaisedRoof(sb, carport, roofHeight);
            drawCarportPoles(sb, carport, roofHeight);
            if (carport.getShed() != null) {
                drawShedCoverings(sb, carport, roofHeight);
            }
            drawCarportLengthLine(sb, carport, roofHeight);
        } else {
            drawCarportPoles(sb, carport);
            drawCarportFlatRoof(sb, carport);
            drawCarportLengthLine(sb, carport);
            if (carport.getShed() != null) {
                drawShedCoverings(sb, carport);
            }
        }
        sb.append("</svg>");
        return sb.toString();
    }
    
    
    private void drawCarportFlatRoof(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX, startY+13, 9, carport.getLength()));
        sb.append(rectangle(startX, startY, 9, carport.getLength()));
    }
    
    private void drawCarportRaisedRoof(StringBuilder sb, Carport carport, double roofHeight) {
        sb.append(rectangle(startX, startY+poleWidth, roofHeight+10, poleWidth));
        sb.append(rectangle(carport.getLength(), startY+poleWidth, roofHeight+10, poleWidth));
        sb.append(rectangle(startX, startY+roofHeight+poleWidth, 9, carport.getLength()));
        sb.append(rectangle(startX, startY, 9, carport.getLength()));
        
        double shedPole = carport.getLength()-rightEaves-carport.getLength();
        double amount = Math.floor((carport.getLength()-rightEaves-carport.getLength()-poleWidth)/(poleWidth+0)+1);
        double distance = (carport.getLength()-rightEaves-carport.getLength() - amount*poleWidth) / (amount-1);
        
        for (double i = startX+rightEaves; i < carport.getLength(); i = i + poleWidth + distance) {
            sb.append(rectangle(i+shedPole+2, startY+poleWidth, roofHeight, poleWidth));
        }
        
        sb.append(rectangle(startX+5, startY+roofHeight+poleWidth+poleWidth, 9, carport.getLength()-5));
        
    }
    
    private void drawCarportPoles(StringBuilder sb, Carport carport) {
        sb.append(rectangle(startX+leftEaves, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        sb.append(rectangle(carport.getLength()-rightEaves, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        
        if (carport.getShed() != null) {
            Shed shed = carport.getShed();
            double shedPole = carport.getLength()-rightEaves-shed.getLength();
            sb.append(rectangle(shedPole, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        }
    }
    
    private void drawCarportPoles(StringBuilder sb, Carport carport, double height) {
        sb.append(rectangle(startX+leftEaves, startY+22+height+2, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        sb.append(rectangle(carport.getLength()-rightEaves, startY+22+height+2, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        
        if (carport.getShed() != null) {
            Shed shed = carport.getShed();
            double shedPole = carport.getLength()-rightEaves-shed.getLength();
            sb.append(rectangle(shedPole, startY+22+height+2, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
            if (shed.getLength() > maxDistanceBetweenPoles){
                double half = shed.getLength()/2;
                sb.append(rectangle((((carport.getLength()+startX)-rightEaves)-half)-poleWidth, startY+24+height, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
            }
        }
    }
    
    private void drawCarportLengthLine(StringBuilder sb, Carport carport) {
        sb.append(line(startX, startY+carport.getHeight()+5,carport.getLength()+10,startY+carport.getHeight()+5,2));
        sb.append("<text x="+ ((carport.getLength()/2)-10) +" y="+ (carport.getHeight()+25) +" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + carport.getLength() 
                + " cm" + "</text>");
    }
    
    private void drawCarportLengthLine(StringBuilder sb, Carport carport, double height) {
        sb.append(line(startX, startY+carport.getHeight()+15+height,carport.getLength()+10,startY+carport.getHeight()+15+height,2));
        sb.append("<text x="+ ((carport.getLength()/2)-10) +" y="+ (carport.getHeight()+height+25)+" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + carport.getLength() 
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
    
    private void drawShedCoverings(StringBuilder sb, Carport carport, double height) {
        Shed shed = carport.getShed();
        double shedPole = carport.getLength()-rightEaves-shed.getLength();
        double amount = Math.floor((carport.getLength()-rightEaves-shed.getLength()-poleWidth)/(poleWidth+0)+1);
        double distance = (carport.getLength()-rightEaves-shed.getLength() - amount*poleWidth) / (amount-1);
        
        for (double i = 0; i < (carport.getLength()-rightEaves)-(carport.getLength()-rightEaves-shed.getLength()); i = i + poleWidth + distance) {
            sb.append(rectangle(i+shedPole+2, startY+27+height+2, carport.getHeight()-28, poleWidth));
        }
    }
    
    private String rectangle(double x, double y, double width, double length) {
        return rectangle(x, y, width, length, 1);
    }
    
    private String rectangle(double x, double y, double width, double length, double thickness) {
        return rectangle(x, y, width, length, thickness, "none");
    }
    
    private String rectangle(double x, double y, double width, double length, double thickness, String color) {
        x = cmToDrawUnits(x);
        y = cmToDrawUnits(y);
        width = cmToDrawUnits(width);
        length = cmToDrawUnits(length);
        String text = "<rect x=\"" + x + "mm\" y=\"" + y + "mm\" height=\"" + width + "mm\" width=\"" + length + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill:" + color + "\"/>";
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
        return cm * 0.25; // measure: 1 cm in real life = 0,25 mm on paper
    }
    
}
