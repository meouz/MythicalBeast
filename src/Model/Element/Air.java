package Model.Element;

import Model.Monster;

public class Air extends Element {

    public Air() {
        super(100);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Tanah) damage *= 0.5; // Elemental Attack
        // if (monster.getElement() instanceof Air) damage *= 0; // Elemental Attack
        if (monster.getElement() instanceof Api) damage *= 1.5; // Elemental Attack
        return damage;
        // Pakai Defense ?
    }
}
