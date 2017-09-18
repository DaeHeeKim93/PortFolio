package classes;
public class Board {
	private Unit[][] board;
	private int row, column;
	
	/***
	 * 
	 * @param row 생성할 행의 수
	 * @param col 생성할 열의 수
	 */
	public Board(int row, int col) {
		this.board = new Unit[row][col];
		this.row = row;
		this.column = col;
	}
	
	/**
	 * 
	 * @param row 유닛을 가져올 행 위치
	 * @param col 유닛을 가져올 열 위치
	 * @return Board 상에서 해당 행, 열에 위치한 유닛 반환
	 */
	public Unit getAt(int row, int col) {
		return this.board[row][col];
	}
	
	public void setAt(int row, int col, Unit unit) {
		this.board[row][col] = unit;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	/**
	 * 
	 * @return Board 상의 유닛을 행, 열에 매칭되는 유닛 배열으로 반환
	 */
	public Unit[][] getArray() {
		return this.board;
	}
}
