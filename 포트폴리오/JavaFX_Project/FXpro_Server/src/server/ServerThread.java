package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import classes.Ability;
import classes.IdInfo;
import classes.Room;
import classes.UnitData;

public class ServerThread extends Thread{
	
	private ArrayList<Room> roomList;
	private Socket socket;
	private Connection connection;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;
	private Ability[] abilityInfo;
	private ArrayList<Ability> classList;
	private Vector<SocketListClass> socketList;
	private int idNumber;
	private ArrayList<UnitData> unitClass;	
	private Room clientRoom;
	
	ServerThread(Socket socket,Connection connection, ArrayList<Room> roomList,Vector<SocketListClass> socketList)
	{
		this.socket=socket;
		this.connection=connection;
		this.abilityInfo=new Ability[4];
		this.classList=new ArrayList<Ability>();
		this.roomList=roomList;
		this.socketList=socketList;
		this.unitClass=new ArrayList<UnitData>();
		
	}
	
	
	public void run()
	{

		Room room;
		boolean insertState;
		boolean loginState;
		IdInfo idInfo;
		int sign;
		InetAddress inetaddress=socket.getInetAddress();
		
		System.out.println(inetaddress.getHostAddress()+"로부터 접속하였습니다.");
		
		try{
			OutputStream outPut=socket.getOutputStream();
			InputStream inPut=socket.getInputStream();
			
			objectInput=new ObjectInputStream(inPut);
			objectOutput=new ObjectOutputStream(outPut);
			socketList.add(new SocketListClass(socket,objectOutput,objectInput));
			
			
			
			idInfo=(IdInfo)objectInput.readObject();
			
			if(idInfo.checkFlag()){
				/*
				 flag=1 이면 로그인 수행  flag=0이면 회원가입 수행
				 */
				
				loginState=checkIdInfo(idInfo);
			    objectOutput.writeObject(loginState);
			    objectOutput.flush();
			    objectOutput.reset();
			    
			    if(!loginState)
			    {
			    	objectInput.close();
			    	objectOutput.close();
			    	socket.close();
			    	System.out.println(inetaddress.getHostAddress()+"로 부터 접속이 끊김");
			    	return;
			    	}
			    
			    objectOutput.writeBoolean(readTutorialState(idInfo));
			    objectOutput.flush();
			    objectOutput.reset();
			    
			    objectOutput.writeInt(readIdInfo(idInfo));
			    objectOutput.flush();
			    objectOutput.reset();
			    
			    if(!readTutorialState(idInfo))
			    {
			    	 for(int i=0; i<socketList.size(); i++)
					    {
			    		 
					    	socketList.get(i).setRoomList(roomList);
					    	socketList.get(i).writeRoomInfo();
					    	System.out.println("리스트 전송");
						}
			    }
			    else
			    {
					    
			    	 for(int i=0; i<socketList.size(); i++)
					    {
			    		 
					    	socketList.get(i).setRoomList(roomList);
					    	socketList.get(i).writeRoomInfo();
					    	System.out.println("리스트 전송");
						}
			    }
			    
			  }
			else
			{
				insertState=idInfoInsert(idInfo);
				objectOutput.writeObject(insertState);
				objectOutput.flush();
				objectOutput.reset();

				
				 if(!insertState)
				 {
					 objectInput.close();
					 objectOutput.close();
					 socket.close();
					 System.out.println(inetaddress.getHostAddress()+"로 부터 접속이 끊김");
					 return;
				 }
				 
				 objectOutput.writeInt(readIdInfo(idInfo));
				 objectOutput.flush();
				 objectOutput.reset();
				 
			}
			
			

			while(true){
			
			objectOutput.reset();
			sign=objectInput.readInt();
				
			
			
			if(sign==1)
			{
				System.out.println("시작");
				startLobby(objectInput,objectOutput);
				

			}
			else if(sign==2)
			{
				objectOutput.reset();
				room=(Room)objectInput.readObject();
				System.out.println("방입장 확인");
			
				
				for(int k=0; k<roomList.size(); k++)
				{
					if(roomList.get(k).title.equals(room.title))
					{

						System.out.println("삭제 시작");
						roomList.remove(k);
						break;
					}
				}
				

				for(int i=0; i<socketList.size(); i++)
				{
				
					socketList.get(i).setRoomList(roomList);
					socketList.get(i).writeRoomInfo();
					System.out.println("리스트 전송3");
				}
				
			}
			else if(sign==0)
			{
				System.out.println("튜토리얼 DB 저장 시작");
				readAbilityInfo();
				System.out.println(abilityInfo[0].agility);
				objectOutput.writeObject(abilityInfo);
				objectOutput.flush();
				objectOutput.reset();
				
				classList=(ArrayList<Ability>)objectInput.readObject();
				insertClass(classList);
				
				 for(int i=0; i<socketList.size(); i++)
				    {
		    		 
				    	socketList.get(i).setRoomList(roomList);
				    	socketList.get(i).writeRoomInfo();
				    	System.out.println("리스트 전송");
					}
				
			
			 }
			else if(sign==3)
			{

				ResultSet r=getClassInfo();
				
				objectOutput.reset();
				objectOutput.writeUTF("info");
				objectOutput.flush();
				objectOutput.reset();
				
				
				objectOutput.writeUTF(idInfo.getId());
				objectOutput.flush();
				objectOutput.reset();
				
				
				while(r.next())
				{
					unitClass.add(new UnitData(r.getInt(3),r.getInt(5),r.getInt(6),r.getInt(7)
							,r.getInt(8),r.getInt(9),r.getInt(10),r.getInt(4)));
				}
				
				objectOutput.reset();
				objectOutput.writeObject(unitClass);
				objectOutput.flush();
				objectOutput.reset();

				unitClass.clear();
				
			}
			else if(sign==4)
			{
				 for(int i=0; i<socketList.size(); i++)
				    {
				    	socketList.get(i).setRoomList(roomList);
				    	socketList.get(i).writeRoomInfo();
				    	System.out.println("리스트 전송4");
					}
			}
			else
				break;
			}
			objectInput.close();
			objectOutput.close();
			socket.close();
			
		}catch(IOException | ClassNotFoundException | SQLException e){
			
				
				for(int i=0; i<roomList.size(); i++)
				{
					if(roomList.get(i).title.equals(clientRoom.title))
					{
						System.out.println("삭제");
						roomList.remove(i);
					}
				}
				
				for(int i=0; i<socketList.size(); i++)
			    {
			    	socketList.get(i).setRoomList(roomList);
			    	socketList.get(i).writeRoomInfo();
			    	System.out.println("리스트 전송4");
				}
		}
	}
	
	ResultSet getClassInfo() throws SQLException
	{
		return new DataBaseFunction().getClassInfo(connection, idNumber);
	}
	
	boolean checkIdInfo(IdInfo idInfo) throws IOException
	{
		if(new DataBaseFunction().readLoginInfo(connection,idInfo.getId(),idInfo.getPassword()))
				return true;
		else
			return false;
	}
	
	boolean idInfoInsert(IdInfo idInfo)
	{
		if(new DataBaseFunction().id_info_insert(connection, idInfo.getId(),idInfo.getPassword()))
			return true;
		else
			return false;
	}
	
	int readIdInfo(IdInfo idInfo) throws SQLException
	{
		int num=new DataBaseFunction().readIdInfo2(connection, idInfo.getId());
		idNumber=num;
		return num;
	}
	
	boolean readTutorialState(IdInfo idInfo) throws SQLException
	{
		if(new DataBaseFunction().readIdInfo(connection, idInfo.getId()))
			return true;
		else
			return false;
		
	}

	void readAbilityInfo() throws SQLException
	{
		ResultSet resultSet= new DataBaseFunction().readAbilityInfo(connection);
		int i=0;
		while(resultSet.next())
		{
			abilityInfo[i]=new Ability(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4)
					,resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7));
			i++;
		}
		
	}
	
	void insertClass(ArrayList<Ability> abilityList)
	{
		for(int i=0; i<abilityList.size(); i++)
		{
			new DataBaseFunction().insertClass(
					connection,abilityList.get(i).idNumber, abilityList.get(i).className,abilityList.get(i));
		}
		
	}
	

	void startLobby(ObjectInputStream input,ObjectOutputStream Output)
	{
		try {
			System.out.println("방생성");	
			clientRoom=(Room)input.readObject();
			roomList.add(clientRoom);
			
			for(int i=0; i<socketList.size(); i++)
			{
			
				socketList.get(i).setRoomList(roomList);
				socketList.get(i).writeRoomInfo();
				System.out.println("리스트 전송3");
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			
			for(int i=0; i<roomList.size(); i++)
			{
				if(roomList.get(i).title.equals(clientRoom.title))
				{
					System.out.println("삭제2");
					roomList.remove(i);
				}
			}
			
			for(int i=0; i<socketList.size(); i++)
		    {
		    	socketList.get(i).setRoomList(roomList);
		    	socketList.get(i).writeRoomInfo();
		    	System.out.println("리스트 전송4");
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
