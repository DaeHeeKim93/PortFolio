package classes;

import java.io.*;
import java.net.*;
import java.util.*;

public class Room  implements Serializable {
	private static final long serialVersionUID = 124235890322L;
	
    public ArrayList<String> ip;
	public String title;
	
	public Room() {
		this("");
	}
	
	public Room(String title) {
		this.ip = new ArrayList<String>();
		this.title = title;
		
		try {
			InetAddress me = InetAddress.getLocalHost();
			ip.add(me.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public List<String> getIpList(){
		return this.ip;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
