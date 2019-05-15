package data.help_classes;

/**
 * Entity class of a part.
 * Holds all nessescary information of a part.
 */
public class Part {

    private final String name;
    private final int length;
    private final int amount;
    private final String description;
    private final double buyPrice;
    private final String unit;

    
    /**
     * The constructor checks if the arguments is legal.
     * If they are not, IllegalArgumentException is thrown.
     * @param name string for the name of the part.
     * @param length integer of the length of the part.
     * @param amount an integer of the amount of the part.
     * @param unit which unit the amount is calculated in.
     * @param description a string that describes the part.
     * @param buyPrice integer of the price of the part.
     */
    public Part(String name, int length, int amount, String unit, String description, double buyPrice) {
        if (name == null || name.isEmpty()
                || amount < 0 
                || buyPrice < 0
                || description == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.unit = unit;
        this.description = description;
        this.buyPrice = buyPrice;
    }

    public Part(String name, int amount, String unit, String description, double buyPrice) {
        this(name, -1, amount, unit, description, buyPrice);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public String getUnit() {
        return unit;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getTotalPrice() {
        return buyPrice * amount;
    }
}
