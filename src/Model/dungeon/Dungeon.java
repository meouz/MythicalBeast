package Model.dungeon;

import Model.element.components.Air;
import Model.element.components.Angin;
import Model.element.components.Api;
import Model.element.components.Es;
import Model.element.components.Tanah;
import Model.entity.Monster;

public class Dungeon {
    private Monster player;

    public Dungeon(Monster player) {
        this.player = player;
    }

    public String explore() {
        String result = "\nTidak menemukan apa-apa";
        if (Math.random() > 0.5) {
            result = "\nBertemu monster";
        }
        return result;
    }

    public Monster genMonster() {
        Monster enemy;
        switch ((int) (Math.random() * 5 + 1)) {
            case 1:
                enemy = new Monster(0, new Tanah(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterTanah.jpg");
                break;
            case 2:
                enemy = new Monster(1, new Angin(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterAngin.jpg");
                break;
            case 3:
                enemy = new Monster(2, new Air(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterAir.jpg");
                break;
            case 4:
                enemy = new Monster(3, new Api(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterApi.jpg");
                break;
            case 5:
                enemy = new Monster(4, new Es(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterEs.jpg");
                break;
            default:
                enemy = new Monster(0, new Tanah(), player.getMonsterStr() + ((int) (Math.random() * 3 + 3)), "src/resources/images/monsterTanah.jpg");
                break;
        }
        return enemy;
    }
}
