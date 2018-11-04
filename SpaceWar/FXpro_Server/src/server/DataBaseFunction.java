package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Ability;

public class DataBaseFunction {
	

public boolean id_info_insert(Connection connection,String id,String password){
		
		PreparedStatement ps=null;
		
		String query="insert into login_info(id,pw,flag) values(?,?,?)";
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setInt(3, 0);
			int n=ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return false;
		}
		
		
	}

	public Connection dbConnect()
	{
		
		Connection connection=null;
		String url="jdbc:mysql://127.0.0.1:3306/javafxproject";
		String user="root";
		String password="1234";
		try{
			connection=DriverManager.getConnection(url,user,password);
			System.out.println("My-SQL 접속 완료!!");
			return connection;
		}catch(SQLException e){
			System.out.println("My-SQL 접속 실패");
			e.printStackTrace();
			return connection;
		}
	}
	
	public ResultSet readAbilityInfo(Connection connection)
	{
		PreparedStatement preparedStatement=null;
		String query="select *  from ability";
		ResultSet resultSet=null;
		try{
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			return resultSet;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return resultSet;
		}
	}
	
	public boolean	readLoginInfo(Connection connection,String id,String password)
	{
		PreparedStatement preparedStatement=null;
		String query="select id,pw from login_info where id='"+id+"' && pw='"+password+"'";
		ResultSet resultSet;
		try{
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
		
			resultSet.next();
			resultSet.getString(1);
			
			if(resultSet.wasNull())
			{
				return false;
			}
			
			return true;
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet readAbility(Connection con) 
	{
		ResultSet r=null;
		PreparedStatement ps=null;
		int i=0;
		
		String query="select * from ability";
		try{
		ps=con.prepareStatement(query);
		r=ps.executeQuery();

		return r;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean readIdInfo(Connection connection,String id)
	{
		ResultSet resultSet;
		PreparedStatement preparedStatement=null;
		
		String query="select flag from login_info where id='"+id+"'";
	
		try{
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getInt(1)==0){
					return true;
				}
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public int readIdInfo2(Connection connection,String id) 
	{
		ResultSet resultSet;
		PreparedStatement preparedStatement=null;
		int idNumber=0;
		
		String query="select id_num from login_info where id='"+id+"'";
		
		try{
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			idNumber=resultSet.getInt(1);

			return idNumber;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return idNumber;
		}
	}
	
	public void insertClass(Connection connection,int id,String name, Ability ability)
	{
		int result;
		PreparedStatement preparedStatement=null;
		
		String query="insert into hasclass values("+id+",'"+name+"'"+","+ability.classId+","+0+","+ability.hp+","+ability.str
				+","+ability.agility+","+ ability.attackRange+","+ability.moveRange+","+ability.attackCount+")";
		
		try{
			
			preparedStatement=connection.prepareStatement(query);
			result= preparedStatement.executeUpdate();
			
			if(result==1)
			{
				updateTutorialFlag(connection,ability);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateTutorialFlag(Connection connection,Ability ability)
	{
		PreparedStatement preparedStatement=null;
		String query="update login_info set flag=1 where id_num="+ability.idNumber+" && flag=0";
		try{
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ResultSet getClassInfo(Connection connection,int idNumber)
	{
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		String query="select * from hasclass where id_num="+idNumber;
		try{
			preparedStatement=connection.prepareStatement(query);
			result=preparedStatement.executeQuery();
			return result;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return result;
		}
	}
	
}
