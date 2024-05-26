package Model.dungeon;

import Model.element.components.Air;
import Model.element.components.Angin;
import Model.element.components.Api;
import Model.element.components.Es;
import Model.element.components.Tanah;
import Model.entity.Monster;

public class Dungeon {
    private int floor;

    public Dungeon() {

    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String explore() {
        String result = "Tidak menemukan apa-apa";
        if (Math.random() > 0.8) {
            result = "Bertemu pintu";
        } else if (Math.random() > 0.65) {
            result = "Bertemu monster";
        }
        return result;
    }

    public Monster genMonster() {
        Monster enemy;
        switch ((int) (Math.random() * 5 + 1)) {
            case 1:
                enemy = new Monster(null, new Tanah(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterTanah.jpg");
                break;
            case 2:
                enemy = new Monster(null, new Angin(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterAngin.jpg");
                break;
            case 3:
                enemy = new Monster(null, new Air(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterAir.jpg");
                break;
            case 4:
                enemy = new Monster(null, new Api(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterApi.jpg");
                break;
            case 5:
                enemy = new Monster(null, new Es(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterEs.jpg");
                break;
            default:
                enemy = new Monster(null, new Tanah(), 10 + ((int) (Math.random() * 3 + 3) * floor), "src/resources/images/monsterTanah.jpg");
                break;
        }
        return enemy;
    }
}
