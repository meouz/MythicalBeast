package Model.item;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private int maxQty = 99;
    private int qty;
    private int price;
    private String imagePath;

    public Item(String name, String imagePath, int qty, int price) {
        setName(name);
        setImagePath(imagePath);
        setQty(qty);
        setPrice(price);
    }

    public Item(String name, String imagePath, int price) {
        setName(name);
        setImagePath(imagePath);
        setPrice(price);
    }

    @Override
    public String toString() {
        return "Item{name=" + name + ", maxQty=" + maxQty + ", qty=" + qty + ", price=" + price + ", imagePath=" + imagePath +  "}";
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
