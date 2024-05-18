package Model;

public class Dungeon {
    int floor;
    int countMonster;

    Dungeon() {

    }

    public String printInfo() {
        String result = "";
        return result;
    }

    public Monster genMonster() {
        Monster monster = new Monster();
        return monster;
    }

    public Monster genBoss() {
        Monster monster = new Monster();
        return monster;
    }

    public Item genChess() {
        Item item = new Item();
        return item;
    }
}
