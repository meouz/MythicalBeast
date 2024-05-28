package Model.element.components;

import Model.element.Element;
import Model.entity.Monster;

public class Api extends Element {

    public Api() {
        super(12);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Es)
            damage *= 2;
        return damage;
    }

}
