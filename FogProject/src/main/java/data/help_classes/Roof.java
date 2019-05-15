/*
 */
package data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Roof {

    private final int id;
    private final String type;
    private final int slope;

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

    public int getRoofHeight(Carport carport) {
        int result;
        double preCasted = carport.getWidth() / 2;
        double toDegrees = Math.tan(Math.toRadians((double) carport.getRoof().getSlope()));
        preCasted = preCasted * toDegrees;
        result = (int) Math.ceil(preCasted);
        return result;
    }
}
