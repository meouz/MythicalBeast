package Model;

public class HomeBase {
    Monster[] monsters = new Monster[20];
    Item[] items = new Item[12];

    HomeBase() {

    }

    public void revive(Monster monster) {
        heal(monster);
    }

    public void heal(Monster monster) {
        monster.setHp(monster.getMaxHP());
    }

    public void train(Monster monster) {
        monster.setEp(monster.getEp() + (int) (Math.random() * 10)); // Set Status Monster ? kan naik level
    }

    public void evolve(Monster monster) {
        // ??
        monster.setElement(null);
    }

    public Monster changeMonster(Monster monster, int index) {
        if (index > monsters.length && index < 0) {
            return null;
        }
        Monster temp = monsters[index];
        monsters[index] = monster;
        return temp;
    }

    public String printAll() {
        return "";
    }

    public String printActiveMonsterStat() {
        return "";
    }

    public String printGuide() {
        return "";
    }

    public String addItem(Item item) {
        if (item == null) {
            return "There's no item";
        }
        if (items[items.length - 1] != null) {
            return "Storage Item is full";
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
        return "Item stored successfully";
    }

    public String deleteItem(Item item) {
        String temp = "Item tidak ada di dalam Storage";
        if (item == null || item) {
            return "Terjadi kesalahan";
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }
            if (!items[i].name.equalsIgnoreCase(item.name)) {
                continue;
            }
            items[i] = null;
            temp = "Deleted successfully";
            break;
        }
        return temp;
    }

    public String addMonster(Monster monster) {
        if (monster == null) {
            return "There's no monster";
        }
        if (monsters[monsters.length - 1] != null) {
            return "Storage Monster is full";
        }
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] == null) {
                monsters[i] = monster;
                break;
            }
        }
        return "Monster stored successfully";
    }

    public String deleteMonster(Monster monster) {
        return "";
    }
}
