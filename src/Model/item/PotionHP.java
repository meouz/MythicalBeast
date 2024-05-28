package Model.item;

import Model.entity.Monster;

public class PotionHP extends Item {
    private int effect;

    public PotionHP(String name, String imagePath, int qty, int price, int effect) {
        super(name, imagePath, qty, price);
        setEffect(effect);
    }

    public PotionHP(String name, String imagePath, int price, int effect) {
        super(name, imagePath, price);
        setEffect(effect);
    }

    public void effect(Monster monster) {
        double heal = (monster.getMaxHP() * (getEffect() / 100.0));
        if (monster.getHp() + heal > monster.getMaxHP()) {
            monster.setHp(monster.getMaxHP());
        } else {
            monster.setHp((int) (monster.getHp() + heal));
        }
    }

    public String printDetail() {
        String result;
        result = "<html>Nama : " + getName();
        result += "<br>Jumlah : " + getQty() + "/" + getMaxQty();
        result += "<br>Effect : Heal " + getEffect() + "% dari Max HP</html>";
        return result;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }
}
