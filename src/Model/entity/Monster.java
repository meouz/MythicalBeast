package Model.entity;

import Model.element.Element;
import Model.element.components.Air;
import Model.element.components.Angin;
import Model.element.components.Api;
import Model.element.components.Es;
import Model.element.components.Tanah;

public class Monster {
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

    Monster[] monster = new Monster[5];

    public Monster(){}

    public Monster(String name, Element element, int monsterStr, String image) {
        setName(name);
        setElement(element);
        setMonsterStr(monsterStr);
        setImage(image);
        setLastLevel(1);
        setMaxHP(100);
        setHp(getMaxHP());
        setLevel(1);
        setEp(0);
    }

    public String basicAttack(Monster target) {
        target.setHp(target.getHp() - getMonsterStr());
        return "Anda memberi damage: " + getMonsterStr(); // Selalu kena
    }

    public String specialAttack(Monster target) {
        if (Math.random() > 0.9) {
            return "Serangan Anda Miss";
        }
        int dmg = getMonsterStr() + random();
        target.setHp(target.getHp() - dmg);
        return "Anda memberi damage: " + dmg; // Sangat jarang untuk miss
    }

    private int random(){
        return (int) Math.random() * 3 + 3;
    }

    public void levelUp() {
        setMaxHP(random() + getMaxHP() + 10);
        setHp(getMaxHP());
        setMonsterStr(random() + getMonsterStr() + 10);
        getElement().setElementDmg(random() + getElement().getElementDmg() + 10);
        setLevel(getLevel() + 1);
        setEp(0);
    }

    public String elementAttack(Monster target) {
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
        System.out.println(target.getHp());
        return "Anda memberi damage: " + dmg;
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
        this.hp = hp;
    }

    public void heal(int amount) {
        if (getHp() + amount > getMaxHP()) {
            this.hp = getMaxHP();
            return;
        }
        this.hp = getHp() + amount;
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