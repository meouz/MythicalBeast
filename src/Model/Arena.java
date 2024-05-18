package Model;

import Model.Element.*;

public class Arena implements Battle {

	Arena() {
	}

	// public Element checkElement(Monster monster) {
	// if (monster.getElement() instanceof Air)
	// return monster.getElement();
	// if (monster.getElement() instanceof Angin)
	// return monster.getElement();
	// if (monster.getElement() instanceof Api)
	// return monster.getElement();
	// if (monster.getElement() instanceof Es)
	// return monster.getElement();
	// if (monster.getElement() instanceof Tanah)
	// return monster.getElement();
	// return null;
	// }

	@Override
	public String action(Monster target, int act) {
		switch (act) {
			case value:

				break;

			default:
				break;
		}
	}

	@Override
	public String printDetail(Monster monster) {
		String str = "";
		str += "Level:" + monster.getLevel() + "\n";
		str += "HP:" + monster.getHp() + "/" + monster.getMaxHP() + "\n";
		str += monster. + "\n";
		return str;
	}
}