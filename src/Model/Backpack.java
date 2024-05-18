package Model;

public class Backpack {
    Monster[] monsters = new Monster[3];
    Item[] items = new Item[10];
    Monster[] tempMonsters = new Monster[2];
    int maxItem = 0;
    int maxMonster = 0;

    Backpack() {

    }

    public void addItem(Item item) {
        if (maxItem == items.length) {
            return;
        }
        items[maxItem++] = item;
    }

    public void removeItem(Item item) {
        if (maxItem == 0) {
            return;
        }
        int index = findItem(item.getName());
        if (index < 0) {
            return;
        }
        items[index] = null;
    }

    public int findItem(String name) {
        for (int i = 0; i < items.length && items[i] != null; i++) {
            if (items[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public void addMonster(Monster Monster) {
        if (maxMonster == monsters.length) {
            return;
        }
        monsters[maxMonster++] = Monster;
    }

    public void removeMonster(Monster monster) {
        if (maxMonster == 0) {
            return;
        }
        int index = findMonster(monster.getName());
        if (index < 0) {
            return;
        }
        monsters[index] = null;
    }

    public int findMonster(String name) {
        for (int i = 0; i < monsters.length && monsters[i] != null; i++) {
            if (((Monster) monsters[i]).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public String printBackpack() {
        String str = "";
        for (Monster monster : monsters) {
            str += monster.getName() + " " + monster.getElement() + "\n";
        }

        str += "\n";
        for (Item item : items) {
            str += item.printDetail();
        }

        str += "\n";
        for (Monster tempMonster : tempMonsters) {
            str += tempMonster.getName() + " " + tempMonster.getElement() + "\n";
        }

        return str;
    }
}