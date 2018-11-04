package classes;

import java.io.Serializable;

public class IdInfo implements Serializable {
	/* 로그인 정보가 기록된다.*/
	
	private static final long serialVersionUID = 96435332L;
	
	String id;
	String password;
	int flag; 
	/*client쪽의 LoginController에서 보낸 신호를 저장  
	로그인->1
	회원가입->2
	*/
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
