package data.help_classes;

/**
 *
 * @author Simon Asholt Norup
 */
public class Part {
    
    private final String name;
    private final int unknown;
    private final int amount;
    private final String description;

    public Part(String name, int unknown, int amount, String description) {
        this.name = name;
        this.unknown = unknown;
        this.amount = amount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getUnknown() {
        return unknown;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
    
}
