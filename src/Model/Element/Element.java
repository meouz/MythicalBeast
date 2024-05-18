package Model.Element;

import Model.Monster;

public abstract class Element {
    private int elementDmg;

    Element() {

    }

    Element(int elementDmg) {
        this.elementDmg = elementDmg;
    }

    public int getElementDmg() {
        return elementDmg;
    }

    public void setElementDmg(int elementDmg) {
        if (elementDmg < 0)
            elementDmg = 0;
        this.elementDmg = elementDmg;
    }

    abstract public int damage(Monster monster);
}
