package data.help_classes;

/**
 * Entity class of a carport. 
 * Holds all values needed to simulate a real carport.
 */
public class Carport {
    
    private static final double widthEaves = 60;
    private static final double backEaves = 5;
    private static final int height = 210;

    private final int id;
    private final int length;
    private final int width;
    private final Roof roof;
    private final Shed shed;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param id the unique int value of the carport.
     * @param length int value of the length.
     * @param width int value of the width.
     * @param roof object of the roof (seperated from the carport).
     * @param shed object of the shed (seperated from the carport).
     * Seperated* in a way that in the database there is used a many to many relation.
     */
    public Carport(int id, int length, int width, Roof roof, Shed shed) {
        if (length <= 0 || width <= 0 || roof == null) {
            throw new IllegalArgumentException();
        }
        final double frontEaves = length*0.15;
        if (shed != null && (length-backEaves-frontEaves < shed.getLength() || width-widthEaves < shed.getWidth())) {
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
