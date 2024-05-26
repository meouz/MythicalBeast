package Model.element.components;

import Model.element.Element;
import Model.entity.Monster;

public class Air extends Element {

    public Air() {
        super(15);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Api)
            damage *= 2; // Elemental Attack
        return damage;
    }
}