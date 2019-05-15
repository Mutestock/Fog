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
    private final Carport carport;

    
    /**
     * 
     * @param carport predefined carport object with length and width.
     */
    public SVGDrawerFromSide(Carport carport) {
        this.carport = carport;
        startX = 10;
        startY = 10;
       
        rightEaves = 5;
        leftEaves = carport.getLength() * 0.15;

        poleWidth = 7.5;
        maxDistanceBetweenPoles = 480;
    }
    
    
    
    /**
     * 
     * @return HTML svg code in form of a string based on the carport specifications.
     */
    public String drawCarport() {
        double roofHeight = 0;
        
        if (carport.getRoof().getRaised()) {
            Roof roof = carport.getRoof();
            double angleOfRoof = 90-roof.getSlope();
            double bigA = carport.getWidth()/2;
            roofHeight = Math.sin(Math.toRadians(roof.getSlope()))*(bigA/Math.sin(Math.toRadians((angleOfRoof))));
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("<svg x=\"10mm\" y=\"10mm\" width=\""+(cmToDrawUnits(carport.getLength())+5)+"mm\" height=\""+(cmToDrawUnits(carport.getHeight())+100+roofHeight)+"mm\">");
        
        if (carport.getRoof().getRaised()) {
            drawCarportRaisedRoof(sb, roofHeight);
            drawCarportPoles(sb, roofHeight);
            if (carport.getShed() != null) {
                drawShedCoverings(sb, roofHeight);
            }
            drawCarportLengthLine(sb, roofHeight);
        } else {
            drawCarportPoles(sb);
            drawCarportFlatRoof(sb);
            drawCarportLengthLine(sb);
            if (carport.getShed() != null) {
                drawShedCoverings(sb);
            }
        }
        sb.append("</svg>");
        return sb.toString();
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * Appends the HTML code used for a flat roof from the side to the main string.
     */
    private void drawCarportFlatRoof(StringBuilder sb) {
        sb.append(rectangle(startX, startY+13, 9, carport.getLength()));
        sb.append(rectangle(startX, startY, 9, carport.getLength()));
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * @param roofHeight carport roof height calculated through sinus relations.
     * Appends the HTML code used for a raised roof from the side to the main string.
     */
    private void drawCarportRaisedRoof(StringBuilder sb, double roofHeight) {
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
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * Appends the HTML code used for the poles from the side to the main string.
     */
    private void drawCarportPoles(StringBuilder sb) {
        sb.append(rectangle(startX+leftEaves, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        sb.append(rectangle(carport.getLength()-rightEaves, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        
        if (carport.getShed() != null) {
            Shed shed = carport.getShed();
            double shedPole = carport.getLength()-rightEaves-shed.getLength();
            sb.append(rectangle(shedPole, startY+22, carport.getHeight()-22, poleWidth,1.5, "rgb(200,200,200)"));
        }
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * @param height height of the roof calculated through sinus relations.
     * Appends the HTML code used for the poles from the side to the main string with a height specification.
     */
    private void drawCarportPoles(StringBuilder sb, double height) {
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
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * Appends the HTML code used for length line below the carport drawing to the main string.
     */
    private void drawCarportLengthLine(StringBuilder sb) {
        sb.append(line(startX, startY+carport.getHeight()+5,carport.getLength()+10,startY+carport.getHeight()+5,2));
        sb.append("<text x="+ ((carport.getLength()/2)-10) +" y="+ (carport.getHeight()+25) +" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + carport.getLength() 
                + " cm" + "</text>");
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * @param height total height of the entire carport.
     * Appends the HTML code used for length line below the carport drawing to the main string with a height specification.
     */
    private void drawCarportLengthLine(StringBuilder sb, double height) {
        sb.append(line(startX, startY+carport.getHeight()+15+height,carport.getLength()+10,startY+carport.getHeight()+15+height,2));
        sb.append("<text x="+ ((carport.getLength()/2)-10) +" y="+ (carport.getHeight()+height+25)+" font-family=\"Verdana\" font-size=\"15\" fill=\"black\">" + carport.getLength() 
                + " cm" + "</text>");
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * Appends the HTML code used for the shed coverings to the main string.
     */
    private void drawShedCoverings(StringBuilder sb) {
        Shed shed = carport.getShed();
        double shedPole = carport.getLength()-rightEaves-shed.getLength();
        double amount = Math.floor((carport.getLength()-rightEaves-shed.getLength()-poleWidth)/(poleWidth+0)+1);
        double distance = (carport.getLength()-rightEaves-shed.getLength() - amount*poleWidth) / (amount-1);
        
        for (double i = 0; i < (carport.getLength()-rightEaves)-(carport.getLength()-rightEaves-shed.getLength()); i = i + poleWidth + distance) {
            sb.append(rectangle(i+shedPole+2, startY+27, carport.getHeight()-28, poleWidth));
        }
    }
    
    
    /**
     * 
     * @param sb main string for the final HTML SVG code.
     * @param height height of the carport.
     * Appends the HTML code used for the shed coverings to the main string with a height specification.
     */
    private void drawShedCoverings(StringBuilder sb, double height) {
        Shed shed = carport.getShed();
        double shedPole = carport.getLength()-rightEaves-shed.getLength();
        double amount = Math.floor((carport.getLength()-rightEaves-shed.getLength()-poleWidth)/(poleWidth+0)+1);
        double distance = (carport.getLength()-rightEaves-shed.getLength() - amount*poleWidth) / (amount-1);
        
        for (double i = 0; i < (carport.getLength()-rightEaves)-(carport.getLength()-rightEaves-shed.getLength()); i = i + poleWidth + distance) {
            sb.append(rectangle(i+shedPole+2, startY+27+height+2, carport.getHeight()-28, poleWidth));
        }
    }
    
    
    /**
     * 
     * @param x x coordinate for the rectangle.
     * @param y y coordinate for the rectangle.
     * @param width width of the rectangle.
     * @param length length of the rectangle.
     * @return a rectangle SVG code with the specifications.
     */
    private String rectangle(double x, double y, double width, double length) {
        return rectangle(x, y, width, length, 1);
    }
    
    
    /**
     * 
     * @param x x coordinate for the rectangle.
     * @param y y coordinate for the rectangle.
     * @param width width of the rectangle.
     * @param length length of the rectangle.
     * @param thickness thickness of the outline of the rectangle.
     * @return a rectangle SVG code with the specifications.
     */
    private String rectangle(double x, double y, double width, double length, double thickness) {
        return rectangle(x, y, width, length, thickness, "none");
    }
    
    
    /**
     * 
     * @param x x coordinate for the rectangle.
     * @param y y coordinate for the rectangle.
     * @param width width of the rectangle.
     * @param length length of the rectangle.
     * @param thickness thickness of the outline of the rectangle.
     * @param color color of the outline of the rectangle.
     * @return a rectangle SVG code with the specifications.
     */
    private String rectangle(double x, double y, double width, double length, double thickness, String color) {
        x = cmToDrawUnits(x);
        y = cmToDrawUnits(y);
        width = cmToDrawUnits(width);
        length = cmToDrawUnits(length);
        String text = "<rect x=\"" + x + "mm\" y=\"" + y + "mm\" height=\"" + width + "mm\" width=\"" + length + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill:" + color + "\"/>";
        return text;
    }

    
    /**
     * 
     * @param x1 x coordinate for the start of the line.
     * @param y1 y coordinate for the start of the line.
     * @param x2 x coordinate for the end of the line.
     * @param y2 y coordinate for the end of the line.
     * @param thickness thickness of the outline of the line.
     * @return a line SVG code with the specifications.
     */
    private String line(double x1, double y1, double x2, double y2, double thickness) {
        x1 = cmToDrawUnits(x1);
        y1 = cmToDrawUnits(y1);
        x2 = cmToDrawUnits(x2);
        y2 = cmToDrawUnits(y2);
        String text = "<line stroke-dasharray=\"5, 5\" x1=\"" + x1 + "mm\" y1=\"" + y1 + "mm\" x2=\"" + x2 + "mm\" y2=\"" + y2 + "mm\" style=\"stroke:#000000; stroke-width: " + thickness + "; fill: none\"/>";
        return text;
    }

    
    /**
     * 
     * @param cm basic unit length used throughout the program.
     * @return converts the units to 0,25 mm on paper.
     */
    private double cmToDrawUnits(double cm) {
        return cm * 0.25; // measure: 1 cm in real life = 0,25 mm on paper
    }
    
}
