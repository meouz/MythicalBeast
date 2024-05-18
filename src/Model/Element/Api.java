package Model.Element;

import Model.Monster;

public class Api extends Element {

    public Api() {
        super(100);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Air) damage *= 0.5; // Elemental Attack
        // if (monster.getElement() instanceof Api) damage *= 0; // Elemental Attack
        if (monster.getElement() instanceof Es) damage *= 1.5; // Elemental Attack
        return damage;
        // Pakai Defense ?
    }

}
