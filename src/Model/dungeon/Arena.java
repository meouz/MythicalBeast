package Model.dungeon;

import Model.entity.Monster;
import Model.entity.Player;

public class Arena implements Battle {

	public Arena() {
		
	}

	@Override
	public String printDetail(Monster monster) {
		String str = "";
		str += "Level:" + monster.getLevel() + "\n";
		str += "HP:" + monster.getHp() + "/" + monster.getMaxHP() + "\n";
		str += monster + "\n";
		return str;
	}

	public boolean run(Monster player, Monster enemy) {
		int reducedLevel = player.getLevel() - enemy.getLevel();
		if ((Math.random() * 10) + reducedLevel >= 8) {
			return true;
		}
		return false;
	}

	public boolean catchMonster(Player player, Monster enemy) {
		if (player.getMonsterSize() < 3 && enemy.getHp() < (enemy.getMaxHP() * 0.20)) {
			player.getMonsters()[player.getMonsterSize()] = enemy;
			return true;
		}
		return false;
	}
}