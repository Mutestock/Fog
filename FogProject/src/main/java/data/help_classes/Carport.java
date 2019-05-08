/*
 */
package data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Carport {

    private final int id;
    private final int length;
    private final int width;
    private final int height = 210;
    private final Roof roof;
    private final Shed shed;

    public Carport(int id, int length, int width, Roof roof, Shed shed) {
        if (length <= 0 || width <= 0 || roof == null) {
            throw new IllegalArgumentException();
        }
        if (shed != null && (length < shed.getLength() || (width-60) < shed.getWidth())) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.length = length;
        this.width = width;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Carport{" + "id=" + id + ", length=" + length + ", width=" + width + ", height=" + height + ", roof=" + roof + ", shed=" + shed + '}';
    }

}
