package data.help_classes;

/**
 * Entity class of a shed. 
 * Holds all values needed to simulate a real shed.
 */
public class Shed {

    private final int id;
    private final int length;
    private final int width;
    private final String wall_coverings;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param id the unique integer value of the shed.
     * @param length integer value of the length of the shed.
     * @param width integer value of the width of the shed.
     * @param wall_coverings string that describe the type of wall coverings used for the shed.
     */
    public Shed(int id, int length, int width, String wall_coverings) {
        if (length <= 0 || width <= 0 || wall_coverings == null || wall_coverings.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.length = length;
        this.width = width;
        this.wall_coverings = wall_coverings;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getWallCoverings() {
        return wall_coverings;
    }

    public int getId() {
        return id;
    }
}
