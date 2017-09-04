package classes;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HostThread extends Thread {

	public HostThread() {
		setDaemon(true);
	}

	public void run() {
		System.out.println("방안입니다");
		ServerSocket server;
		Socket sock = null;
		try {
			server = new ServerSocket(10002);
			sock = server.accept();
			InetAddress inetaddr = sock.getInetAddress();

			System.out.println(inetaddr.getHostAddress() + "로부터 접속하였습니다.");

			while (true) {
				// 게스트와 통신 부분


			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			if (!sock.isClosed()) {
				sock.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}