package app;

public class OrderHistory {
    private String itemName;
    private double pricePerItem;
    private int slot;

    // Constructor
    public OrderHistory(String itemName, double pricePerItem, int slot) {
        this.itemName = itemName;
        this.pricePerItem = pricePerItem;
        this.slot = slot;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public int getSlot() {
        return slot;
    }
}
