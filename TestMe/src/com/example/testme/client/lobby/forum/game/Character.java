package com.example.testme.client.lobby.forum.game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Thomas
 * @date 26.07.2016
 */
public class Character {

	int maxHP;
	int hp;
	String name;
	int def;
	int str;
	double crit;
	int xp;
	int level;
	
	Logger logger = Logger.getLogger("Character");

	public Character(String n, int hp, int def, int str, double crit) {
		this.name = n;
		this.hp = hp;
		this.def = def;
		this.str = str;
		this.crit = crit;
		this.level = 1;
		this.maxHP = hp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public double getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void addXp(int xp) {
		logger.log(Level.INFO, this.name + " got " + xp + " xp.");
		this.xp += xp;
	}

	public void levelup(int level) {
		if (this.level < level) {
			while (this.level < level) {
				this.level += 1;
				this.def += 1;
				this.str += 1;
				this.maxHP += 1;
			}
			logger.log(Level.INFO, this.name + " aufgestiegen auf Level: "
					+ this.level);
		}
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel() {
		this.level += 1;
		this.def += 1;
		this.str += 1;
		this.maxHP += 1;
	}

	@Override
	public String toString() {
		return "Character [hp=" + hp + ", name=" + name + ", def=" + def
				+ ", str=" + str + ", crit=" + crit + ", xp=" + xp + ", level="
				+ level + "]";
	}

}
