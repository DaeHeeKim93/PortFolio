package classes;

import java.io.*;

public class UnitData implements Serializable {
	private static final long serialVersionUID = 43224010011L;
	
	int classId;
	int hp;
	int str;
	int agility;
	int attackRange;
	int moveRange;
	int attackCount;
	int skill;
	
	public UnitData(int classId,int hp,int str, int agility,int attackRange,int moveRange,int attackCount,
			int skill)
	{
		this.classId=classId;
		this.hp=hp;
		this.str=str;
		this.agility=agility;
		this.attackRange=attackRange;
		this.moveRange=moveRange;
		this.attackCount=attackCount;
		this.skill=skill;
	}

	public int getAgility() {
		return agility;
	}
	
	public int getAttackCount() {
		return attackCount;
	}
	
	public int getAttackRange() {
		return attackRange;
	}
	
	public int getClassId() {
		return classId;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getMoveRange() {
		return moveRange;
	}
	
	public int getSkill() {
		return skill;
	}
	
	public int getStr() {
		return str;
	}
}
