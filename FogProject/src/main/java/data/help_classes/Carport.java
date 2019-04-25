/*
 */
package data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Carport {

    private final int length;
    private final int width;
    private final int height;
    private final Roof roof;
    private final Shed shed;

    public Carport(int length, int width, int height, Roof roof, Shed shed) {
        if (length <= 0 || width <= 0 || height <= 0 || roof == null || shed == null) {
            throw new IllegalArgumentException();
        }
        if (length < shed.getLength() || width < shed.getWidth()) {
            throw new IllegalArgumentException();
        }
        this.length = length;
        this.width = width;
        this.height = height;
        this.roof = roof;
        this.shed = shed;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Roof getRoof() {
        return roof;
    }

    public Shed getShed() {
        return shed;
    }

}
