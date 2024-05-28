package Model.element.components;

import Model.element.Element;
import Model.entity.Monster;

public class Tanah extends Element {

    public Tanah() {
        super(12);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Air)
            damage *= 2;
        return damage;
    }
}
