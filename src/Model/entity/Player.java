package Model.entity;

import Model.item.Item;

public class Player {
    private int ep;
    private Monster[] monsters = new Monster[3];
    private Item[] items = new Item[3];

    public Player() {
        this.ep = 0;
    }

    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster monster) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] == null) {
                monsters[i] = monster;
                break;
            }
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                this.items[i] = item;
                break;
            }
        }
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
        if (getMonsterSize() <= 1) {
            return;
        }
        Monster tempMonster = monsters[0];
        monsters[0] = monsters[index];
        monsters[index] = tempMonster;
    }
}
