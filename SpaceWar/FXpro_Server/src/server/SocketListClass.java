package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import classes.Room;

public class SocketListClass {
	
	

	private Socket socket;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;
	private ArrayList<Room> roomList;
	
	SocketListClass(Socket socket,ObjectOutputStream objectOutput,ObjectInputStream objectInput)
	{
		this.socket=socket;
		this.objectInput=objectInput;
		this.objectOutput=objectOutput;
	}
	
	void setRoomList(ArrayList<Room> clientRoomList)
	{
		this.roomList=clientRoomList;
	}
	
	
	void writeRoomInfo()
	{
		try {
			
			objectOutput.writeUTF("refresh");
			//objectOutput.flush();
			objectOutput.reset();
			
			objectOutput.writeObject(roomList);
			objectOutput.flush();
			objectOutput.reset();
			
			for(int i=0; i<roomList.size(); i++)
				System.out.println("방 리스트 목록: "+roomList.get(i).title);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
