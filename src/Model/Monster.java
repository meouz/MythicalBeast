package Model;

import Model.Element.Element;

public class Monster {
    private String name;
    private int defend;
    private int level;
    private int maxHP;
    private int hp;
    private int maxEP;
    private int ep;
    private Element element;
    private Element weakness;
    private int monsterStr;

    Monster() {
    }

    Monster(String name, Element element, Element weakness, int monsterStr) {
        this.name = name;
        this.element = element;
        this.weakness = weakness;
        this.monsterStr = monsterStr;
    }

    public int basicAttack(Monster target) {
        target.setHp(getMonsterStr());???
        return getMonsterStr(); // Selalu kena
    }

    public int specialAttack(Monster target) {
        if (Math.random() > 0.95) {
            System.out.println("YAHAHAHA MISS");
            return 0;
        }
        return (int) (getMonsterStr() * 1.5); // Sangat jarang untuk miss
    }

    public int elementAttack(Monster target) {
        return element.damageGiven(target);
    }

    public int receiveDamage(int amount) {
        return 0;
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

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
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

    public int getMaxEP() {
        return maxEP;
    }

    public void setMaxEP(int maxEP) {
        this.maxEP = maxEP;
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

    public Element getWeakness() {
        return weakness;
    }

    public void setWeakness(Element weakness) {
        this.weakness = weakness;
    }

    public int getMonsterStr() {
        return monsterStr;
    }

    public void setMonsterStr(int monsterStr) {
        this.monsterStr = monsterStr;
    }
}
