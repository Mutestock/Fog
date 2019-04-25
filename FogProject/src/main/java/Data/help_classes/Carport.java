/*
 */
package Data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Carport {

    private final int length;
    private final int width;
    private final double height = 3.05;
    private final Roof roof;
    private final Shed shed;

    public Carport(int length, int width, Roof roof, Shed shed) {
        if (length <= 0 || width <= 0 || roof == null || shed == null) {
            throw new IllegalArgumentException();
        }
        if (length < shed.getLength() || width < shed.getWidth()) {
            throw new IllegalArgumentException();
        }
        this.length = length;
        this.width = width;;
        this.roof = roof;
        this.shed = shed;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Roof getRoof() {
        return roof;
    }

    public Shed getShed() {
        return shed;
    }

}
