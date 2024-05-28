package Model.element.components;

import Model.element.Element;
import Model.entity.Monster;

public class Es extends Element {

    public Es() {
        super(12);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Angin) 
            damage *= 2;
        return damage;
    }
}
