package Model;

public class Item {
    private String name;
    private int maxSum;
    private int sum;
    private int effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    Item() {
    }

    public String printDetail() {
        return getName() + " " + getEffect() + "\n"; // sum & maxSum ?
    }
}
