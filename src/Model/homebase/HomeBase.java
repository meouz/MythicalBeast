package Model.homebase;

import Model.Main;
import Model.element.components.*;
import Model.entity.Monster;
import Model.entity.Player;

public class HomeBase {
    private String[] monsterImages = Main.monsterImages;

    public HomeBase() {
    }

    public void revive(Monster monster) {
        monster.setHp(monster.getMaxHP());
    }

    public void train(Player player, Monster monster) {
        if (player.getEp() >= monster.getMaxEP()) {
            monster.levelUp();
            player.setEp(player.getEp() - monster.getMaxEP());
        }
    }

    public void evolve(Monster monster, int index) {
        if (monster == null || monster.getLevel() <= monster.getLastLevel()) {
            return;
        }
        if (monster.getElement() instanceof Api) {
            switch (index) {
                case 1:
                    monster.setElement(new Angin());
                    monster.setImage(monsterImages[1]);
                    break;
                case 2:
                    monster.setElement(new Tanah());
                    monster.setImage(monsterImages[4]);
                    break;
            }
        } else if (monster.getElement() instanceof Es) {
            switch (index) {
                case 1:
                    monster.setElement(new Tanah());
                    monster.setImage(monsterImages[4]);
                    break;
                case 2:
                    monster.setElement(new Air());
                    monster.setImage(monsterImages[2]);
                    break;
            }
        } else if (monster.getElement() instanceof Angin) {
            switch (index) {
                case 1:
                    monster.setElement(new Air());
                    monster.setImage(monsterImages[2]);
                    break;
                case 2:
                    monster.setElement(new Api());
                    monster.setImage(monsterImages[0]);
                    break;
            }
        } else if (monster.getElement() instanceof Tanah) {
            switch (index) {
                case 1:
                    monster.setElement(new Api());
                    monster.setImage(monsterImages[0]);
                    break;
                case 2:
                    monster.setElement(new Es());
                    monster.setImage(monsterImages[3]);
                    break;
            }
        } else if (monster.getElement() instanceof Air) {
            switch (index) {
                case 1:
                    monster.setElement(new Es());
                    monster.setImage(monsterImages[3]);
                    break;
                case 2:
                    monster.setElement(new Angin());
                    monster.setImage(monsterImages[1]);
                    break;
            }
        }
        monster.setLastLevel(monster.getLevel());
    }

    public String getElementEvolve(Monster monster) {
        if (monster.getElement() instanceof Api) {
            return "Angin-Tanah";
        } else if (monster.getElement() instanceof Es) {
            return "Tanah-Air";
        } else if (monster.getElement() instanceof Angin) {
            return "Air-Api";
        } else if (monster.getElement() instanceof Tanah) {
            return "Api-Es";
        } else if (monster.getElement() instanceof Air) {
            return "Es-Angin";
        }
        return null;
    }
}
