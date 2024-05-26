package Model.item;

import Model.entity.Monster;

public abstract class Item {
    private String name;
    private int maxSum;
    private int sum;
    private int effect;

    public Item() {

    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

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

    public void effect(Monster monster) {
        monster.setHp(monster.getHp() + (monster.getMaxHP() * getEffect() / 100));
    }

    public String printDetail() {
        String result;
        result = "Nama : " + getName();
        result += "\nJumlah : " + getSum() + "/" + getMaxSum();
        result += "\nEffect : Heal " + getEffect() + "% dari Max HP";
        return result;
    }
}
