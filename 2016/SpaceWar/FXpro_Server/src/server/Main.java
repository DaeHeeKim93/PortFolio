package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import classes.Room;

public class Main {
	
	public static void main(String[] args) throws Exception{
	
		Connection connection=new DataBaseFunction().dbConnect();
		ArrayList<Room> roomlist=new ArrayList<Room>();
		
		Vector<SocketListClass> clientSockList=new Vector<SocketListClass>();
		
		try{
			
			ServerSocket server=new ServerSocket(10003);
			System.out.println("Wating Connect");
			
		
			//Socket sockect=null;
	
			while(true){
			ServerThread clientThread=new ServerThread(server.accept(),connection, roomlist,clientSockList);
			clientThread.start();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
	}

}
