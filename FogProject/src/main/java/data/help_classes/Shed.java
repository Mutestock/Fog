/*
 */
package data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Shed {

    private final int length;
    private final int width;
    private final String wall_coverings;

    public Shed(int length, int width, String wall_coverings) {
        if (length <= 0 || width <= 0 || wall_coverings == null || wall_coverings.isEmpty()) {
            throw new IllegalArgumentException();
        }
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

    public String getWall_coverings() {
        return wall_coverings;
    }

}
