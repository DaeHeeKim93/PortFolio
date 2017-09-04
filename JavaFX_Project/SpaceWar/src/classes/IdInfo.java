package classes;

import java.io.Serializable;

public class IdInfo implements Serializable {
	private static final long serialVersionUID = 96435332L;
	
	String id;
	String password;
	int flag;
	
	public IdInfo(String id,String password, int flag)
	{
		this.id=id;
		this.password=password;
		this.flag=flag;
	}
	
	public void show()
	{
		System.out.println(id+"  "+password);
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public boolean checkFlag()
	{
		if(flag==1)
			return true;
		return false;
	}
}
