package classes;
import java.io.*;

public class Action implements Serializable {
	private static final long serialVersionUID = 552007210413710L; 
	
	public enum Kind {
		MOVE, ATTACK, HEAL, SKILL
	}
	
	private int sourceX, sourceY; // 시작 유닛의 x, y 좌표
	private int targetX, targetY; // 대상 유닛의 x, y 좌표
	private Kind actionType; // 행동의 종류 
	
	public Action(){
	}
	
	public Action(int sourceX, int sourceY, int targetX, int targetY, Kind actionType) {
		this.sourceX = sourceX;
		this.sourceY = sourceY;
		this.targetX = targetX;
		this.targetY = targetY;
		this.actionType = actionType;
	}
	
	public void setActionType(Kind actionType) {
		this.actionType = actionType;
	}
	
	public void setSourceX(int sourceX) {
		this.sourceX = sourceX;
	}
	
	public void setSourceY(int sourceY) {
		this.sourceY = sourceY;
	}
	
	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}
	
	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
	
	public Kind getActionType() {
		return actionType;
	}
	
	public int getSourceX() {
		return sourceX;
	}
	
	public int getSourceY() {
		return sourceY;
	}
	
	public int getTargetX() {
		return targetX;
	}
	
	public int getTargetY() {
		return targetY;
	}
	
	@Override
	public String toString() {
		return new String(sourceX + ", " + sourceY + " -> " + targetX + ", " + targetY + "[" + actionType.toString() + "]");
	}
}
