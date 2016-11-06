package com.example.testme.client.lobby.forum.game;

/**
 * @author Alexander Thomas
 * @date 26.07.2016
 */
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Battle {
	int[] levelUps = new int[100];
	Monster monster;
	Fighter fighter;
	Logger logger = Logger.getLogger("Battle");

	public Battle(Monster m, Fighter f) {
		this.monster = m;
		this.fighter = f;
		levelUps[0] = 0;
		for (int i = 1; i < levelUps.length; i++) {
			levelUps[i] = i * 1000;
		}
	}

	public void fightround() {
		while (monster.hp > 0 && fighter.hp > 0) {
			attack(monster, fighter);
			if (monster.hp <= 0 || fighter.hp <= 0) {
				break;
			}
			attack(fighter, monster);
		}
		fightend(monster, fighter);
		monster.setHp(monster.maxHP);
		fighter.setHp(fighter.maxHP);
	}

	private void fightend(Character o, Character oo) {
		if (o.getHp() > 0) {
			o.addXp(oo.level * 10);
			LevelUp(o);
		}
		if (oo.getHp() > 0) {
			oo.addXp(o.level * 10);
			LevelUp(oo);
		}

	}

	private void LevelUp(Character o) {
		for (int i = 0; i < levelUps.length; i++) {
			if (o.getXp() > levelUps[i]) {
				
			}
			else{
				o.levelup(i);
				return;
			}
		}
	}

	private String status(Character o) {
		return o.getName() + " HP(" + o.getHp() + ")";
	}

	private void attack(Character o, Character oo) {
		Random r = new Random();
		int a = r.nextInt(100);
		logger.log(Level.INFO, status(o) + " attacks " + status(oo) + " -> ");
		if (a > 10) {
			if ((o.getStr() - oo.getDef()) >= 0) {
				oo.setHp(oo.getHp() - (o.getStr() - oo.getDef()));
				logger.log(Level.INFO, status(o) + " attacked with: "
						+ (o.getStr() - oo.getDef()) + " damage to "
						+ status(oo));
			} else {
				oo.setHp(oo.getHp() - 1);
				logger.log(Level.INFO, status(o) + " attacked with: 1"
						+ " damage to " + status(oo));
			}

		} else {
			logger.log(Level.INFO, "CRIT! ");
			if (((o.getStr() * o.getCrit()) - oo.getDef()) >= 0) {
				oo.setHp(oo.getHp()
						- ((int) (o.getStr() * o.getCrit()) - oo.getDef()));
				logger.log(Level.INFO, status(o) + "attacked with:"
						+ ((o.getStr() * o.getCrit()) - oo.getDef())
						+ " damage to " + status(oo));
			} else {
				oo.setHp(oo.getHp() - 2);
				logger.log(Level.INFO, status(o) + "attacked with: 2"
						+ " damage to " + status(oo));
			}

		}
		if (o.hp <= 0) {
			logger.log(Level.INFO, o.getName() + " wurde besiegt!");
		}
		if (oo.hp <= 0) {
			logger.log(Level.INFO, oo.getName() + " wurde besiegt!");
		}
	}
}