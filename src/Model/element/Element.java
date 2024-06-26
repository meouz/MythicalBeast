package Model.element;

import java.io.Serializable;

import Model.entity.Monster;

public abstract class Element implements Serializable{
    private int elementDmg;

    Element() {
    }

    protected Element(int elementDmg) {
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
