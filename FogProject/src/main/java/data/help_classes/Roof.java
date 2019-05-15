package data.help_classes;

/**
 * Entity class of a roof. 
 * Holds all values needed to simulate a real roof.
 */
public class Roof {

    private final int id;
    private final String type;
    private final int slope;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param id the unique integer value of the roof.
     * @param type string that describes the type of roof used.
     * @param slope integer value of the slope of the roof.
     */
    public Roof(int id, String type, int slope) {
        if (type == null || type.isEmpty() || slope < -1) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.type = type;
        this.slope = slope;
    }

    public String getType() {
        return type;
    }

    public int getSlope() {
        return slope;
    }

    public boolean getRaised() {
        return slope > 0;
    }

    public int getId() {
        return id;
    }

    
    /**
     * Calculate the precise height of the roof only (without the rest of the carport)
     * @param carport carport object.
     * @return the height of the roof of the carport as an integer.
     */
    public int getRoofHeight(Carport carport) {
        int result;
        double preCasted = carport.getWidth() / 2;
        double toDegrees = Math.tan(Math.toRadians((double) carport.getRoof().getSlope()));
        preCasted = preCasted * toDegrees;
        result = (int) Math.ceil(preCasted);
        return result;
    }
}
