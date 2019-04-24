/*
 */
package Data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Roof {

    private final String type;
    private final int slope;

    public Roof(String type, int slope) {
        if (type == null || type.isEmpty() || slope < -1) {
            throw new IllegalArgumentException();
        }
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

}
