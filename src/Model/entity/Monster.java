package Model.entity;

import java.io.Serializable;

import Model.element.Element;
import Model.element.components.Air;
import Model.element.components.Angin;
import Model.element.components.Api;
import Model.element.components.Es;
import Model.element.components.Tanah;

public class Monster implements Serializable {
    private String[] names = {"Blazetalon", "Aerozephyr", "Hydroblast", "Frostbite", "Stonelash"};
    private String name;
    private int monsterStr;
    private Element element;
    private int maxHP;
    private int hp;
    private int maxLevel = 99;
    private int level;
    private int lastLevel;
    private int maxEP = 100;
    private int ep;
    private String image;

    public Monster() {
    }

    public Monster(int name, Element element, int monsterStr, String image) {
        setName(names[name]);
        setElement(element);
        setMonsterStr(monsterStr);
        setImage(image);
        setLastLevel(1);
        setMaxHP(100);
        setHp(getMaxHP());
        setLevel(1);
        setEp(0);
    }

    public String basicAttack(Monster target, String entity) {
        target.setHp(target.getHp() - getMonsterStr());
        return "\n" + entity + " memberi damage: " + getMonsterStr();
    }

    public String specialAttack(Monster target, String entity) {
        if (Math.random() > 0.9) {
            return "\nSerangan " + entity + " Miss";
        }
        int dmg = getMonsterStr() + random();
        target.setHp(target.getHp() - dmg);
        return "\n" + entity + " memberi damage: " + dmg;
    }

    private int random() {
        return (int) Math.random() * 3 + 3;
    }

    public void levelUp() {
        setMaxHP(random() + getMaxHP() + 10);
        setHp(getMaxHP());
        setMonsterStr(getMonsterStr() + (int) (random() * 1.5));
        getElement().setElementDmg(getElement().getElementDmg() + (int) (random() * 1.5));
        setLevel(getLevel() + 1);
        setEp(0);
    }

    public String elementAttack(Monster target, String entity) {
        int dmg;
        if (getElement() instanceof Api && target.getElement() instanceof Es) {
            dmg = element.damage(target);
        } else if (getElement() instanceof Es && target.getElement() instanceof Angin) {
            dmg = element.damage(target);
        } else if (getElement() instanceof Angin && target.getElement() instanceof Tanah) {
            dmg = element.damage(target);
        } else if (getElement() instanceof Tanah && target.getElement() instanceof Air) {
            dmg = element.damage(target);
        } else if (getElement() instanceof Air && target.getElement() instanceof Api) {
            dmg = element.damage(target);
        } else {
            dmg = element.getElementDmg();
        }
        target.setHp(target.getHp() - dmg);
        return "\n" + entity + " memberi damage: " + dmg;
    }

    public String getMonsterDetail() {
        String result = "<html>Nama : " + getName();
        result += "<br>Level : " + getLevel() + "/" + getMaxLevel();
        result += "<br>HP : " + getHp() + "/" + getMaxHP();
        result += "<br>Element : " + getElement().getClass().getSimpleName();
        result += "<br>EP : " + getEp() + "/" + getMaxEP();
        result += "</html>";
        return result;
    }

    @Override
    public String toString() {
        return "Monster{name=" + name + ", monsterStr=" + monsterStr + ", Element=" + element + ", maxHP=" + maxHP
                + ", hp=" + hp + ", maxLevel=" + maxLevel + ", level=" + level + ", lastLevel=" + lastLevel + ", maxEP="
                + maxEP + ", ep=" + ep + ", image=" + image + "}";
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getMonsterStr() {
        return monsterStr;
    }

    public void setMonsterStr(int monsterStr) {
        this.monsterStr = monsterStr;
    }

    public String getImage() {
        return image;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMaxEP() {
        return maxEP;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
