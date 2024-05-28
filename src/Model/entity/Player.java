package Model.entity;

import java.io.Serializable;
import java.util.Arrays;

import Model.item.Item;
import Model.item.PotionHP;

public class Player implements Serializable {
    private int ep;
    private Monster[] monsters = new Monster[3];
    private Item[] items = {
            new PotionHP("Small Heal Potion", "src/resources/images/HealSmall.jpg", 2, 10, 10),
            new PotionHP("Medium Heal Potion", "src/resources/images/HealMedium.jpg", 0, 40, 50),
            new PotionHP("Large Heal Potion", "src/resources/images/HealLarge.jpg", 0, 100, 100),
    };

    public Player() {
        setEp(0);
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    // Overloading
    public void setMonsters(Monster monster) {
        if (monster == null) {
            return;
        }
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] == null) {
                monsters[i] = monster;
                break;
            }
        }
    }

    // Overloading
    public void setMonsters(Monster[] monsters) {
        if (monsters == null) {
            return;
        }
        this.monsters = monsters;
    }

    public Item[] getItems() {
        return items;
    }

    // Overloading
    public void setItems(Item item) {
        if (item == null) {
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                this.items[i] = item;
                break;
            }
        }
    }

    // Overloading
    public void setItems(Item[] items) {
        if (items == null) {
            return;
        }
        this.items = items;
    }

    public int getMonsterSize() {
        int temp = 0;
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] != null) {
                temp++;
            }
        }
        return temp;
    }

    public void changeMonster(int index) {
        if (monsters[index] == null || index < 0 || index > getMonsters().length) {
            return;
        }
        Monster tempMonster = monsters[0];
        monsters[0] = monsters[index];
        monsters[index] = tempMonster;
    }

    public void usePotion(Monster monster, int index) {
        if (getItems()[index].getQty() <= 0 || monster.getHp() == monster.getMaxHP()) {
            return;
        }
        getItems()[index].setQty(getItems()[index].getQty() - 1);
        ((PotionHP) getItems()[index]).effect(monster);
    }

    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    @Override
    public String toString() {
        return "Player{ep=" + ep + Arrays.toString(monsters) + ", items=" + Arrays.toString(items) + "}";
    }
}
