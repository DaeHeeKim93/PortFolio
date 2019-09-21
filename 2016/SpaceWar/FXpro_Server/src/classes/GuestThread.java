package classes;

import java.net.*;

public class GuestThread extends Thread {
	String hostIp;
	
	public GuestThread(String hostIp) {
		setDaemon(true);
		
		this.hostIp = hostIp;
	}
	
	@Override
	public void run() {
		try {
			Socket sock = new Socket(hostIp, 10002);
			System.out.println("방에 접속하였습니다.");
			
			while(true) {
				// 호스트와 통신 부분
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
