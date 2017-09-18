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
//			InetAddress me = InetAddress.getLocalHost();
			
			ip.add(getAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public static String getAddress() throws Exception {
		URL web = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
        	// 아마존 웹 서비스로부터 public ip를 받아온다.
            in = new BufferedReader(new InputStreamReader(web.openStream()));
            String ip = in.readLine();
            return ip;
        } catch(Exception ex) {
        	return null;
        }

	}  

}
