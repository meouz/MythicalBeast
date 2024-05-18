package Model.Element;

import Model.Monster;

public class Tanah extends Element {

    public Tanah() {
        super(100);
    }

    @Override
    public int damage(Monster monster) {
        int damage = getElementDmg();
        if (monster.getElement() instanceof Angin) damage *= 0.5; // Elemental Attack
        // if (monster.getElement() instanceof Air) damage *= 0; // Elemental Attack
        if (monster.getElement() instanceof Air) damage *= 1.5; // Elemental Attack
        return damage;
        // Pakai Defense ?
    }
}
