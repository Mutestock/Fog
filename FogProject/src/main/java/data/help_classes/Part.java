package data.help_classes;

/**
 *
 * @author Simon Asholt Norup
 */
public class Part {
    
    private final String name;
    private final int length;
    private final int amount;
    private final String description;
    private final double buyPrice;

    public Part(String name, int length, int amount, String description, double buyPrice) {
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.description = description;
        this.buyPrice = buyPrice;
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

    public double getBuyPrice() {
        return buyPrice;
    }
}
