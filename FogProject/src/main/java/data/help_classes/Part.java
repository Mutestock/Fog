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
        if (name == null || name.isEmpty()
                || amount < 0 || buyPrice < 0
                || description == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.description = description;
        this.buyPrice = buyPrice;
    }

    public Part(String name, int amount, String description, double buyPrice) {
        this(name, -1, amount, description, buyPrice);
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
    
    public double getTotalPrice() {
        return buyPrice * amount;
    }
}
