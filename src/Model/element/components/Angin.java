package Model.element.components;

import Model.element.Element;
import Model.entity.Monster;

public class Angin extends Element {

    public Angin() {
        super(12);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Tanah)
            damage *= 2;
        return damage;
    }
}