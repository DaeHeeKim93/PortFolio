package classes;

import java.io.Serializable;

public class Ability implements Serializable {
	/* DB에 저장되어있는 클래스 능력치를 저장하는 클래스 */
	/* uid 처리 */
	private static final long serialVersionUID = 545294127235L;
	
	public int classId;
	public int str;
	public int hp;
	public int agility;
	public String className;
	public int idNumber;
	public int attackRange;
	public int moveRange;
	public int attackCount;
	
	
	public Ability(int classNum, int hp, int str, int agility,int attackRange,
			int moveRange, int attackCount) {
		this.classId=classNum;
		this.hp=hp;
		this.str=str;
		this.agility=agility;
		this.attackRange=attackRange;
		this.moveRange=moveRange;
		this.attackCount=attackCount;
	}


	
	public void setName(String name)
	{
		this.className=name;
	}

	public void setid_num(int idNumber)
	{
		this.idNumber=idNumber;
	}

}
