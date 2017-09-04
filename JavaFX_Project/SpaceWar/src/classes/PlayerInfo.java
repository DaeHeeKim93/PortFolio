package classes;
import java.io.*;
import java.util.*;

/**
 * 
 * @author jaehyeon
 *
 */
public class PlayerInfo implements Serializable {
	private static final long serialVersionUID = 4313523434125L;
	
	private String id;
	private List<Unit> currentUnits;
	
	/**
	 * 
	 * @param id 플레이어의 ID
	 * @param units 플레이어가 소유한 유닛 목록
	 */
	public PlayerInfo(String id, List<Unit> units){
		this.id = id;
		this.currentUnits = units;
	}

	public PlayerInfo(){
		this("NoName", new ArrayList<Unit>());
	}
	
	public PlayerInfo(String id){
		this(id, new ArrayList<Unit>());
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public List<Unit> getCurrentUnits() {
		return currentUnits;
	}
}
