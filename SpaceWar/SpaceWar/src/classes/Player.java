package classes;
import java.util.*;

import javafx.application.*;
import javafx.scene.layout.*;

public class Player {
	private String name;
	private List<Unit> units;
	private int number;
	
	private Board board; // 게임 진행을 위한 Board 객체
	private Pane stage; // 게임 진행을 위한 Pane 레이아웃
	
	public Player(Pane stage, Board board, String name, int number) {
		this.stage = stage;
		this.board = board;
		this.name = name;
		this.number = number;
		this.units = new Vector<>();
	}
	
	public Player(Pane stage, Board board, String name, int number, Unit... units) {
		this(stage, board, name, number);
		addUnits(units);
	}
	
	public String getName() {
		return name;
	}
	
	public Pane getStage() {
		return stage;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void addUnit(
			String name, 
			String job,
			int hp, 
			int power, 
			int armor,
			int x, 
			int y, 
			int attackRange, 
			int moveRange,
			int attackCount
			) {
		Unit unit = new Unit(name, job, hp, power, armor, x, y, attackRange, moveRange, attackCount); 
		addUnit(unit);
	}
	
	public void addUnit(Unit unit) {
		Platform.runLater(()->{
			units.add(unit);
			unit.setPlayer(Player.this);
		});
		
	}
	
	public void addUnits(Collection<? extends Unit> units) {
		for(Unit unit : units){
			addUnit(unit);
		}
	}
	
	public void addUnits(Unit... units) {
		for(Unit unit : units){
			addUnit(unit);
		}
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	
	public Board getBoard() {
		return board;
	}
}
