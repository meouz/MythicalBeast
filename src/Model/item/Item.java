package Model.item;

public class Item {
    private String name;
    private int maxQty;
    private int qty;
    private int price;
    private String imagePath;

    public Item(String name, String imagePath, int qty, int maxQty, int price) {
        setName(name);
        setImagePath(imagePath);
        setQty(qty);
        setMaxQty(maxQty);
        setPrice(price);
    }

    public Item(String name, String imagePath, int price) {
        setName(name);
        setImagePath(imagePath);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
