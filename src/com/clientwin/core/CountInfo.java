package com.clientwin.core;
/**
 * 
 * @ClassName: CountInfo 
 * @Description: TODO(当前这个用户的信息) 
 * @author 威 
 * @date 2017年6月3日 上午10:31:54 
 *
 */
public class CountInfo {
	private static CountInfo info = new CountInfo() ;
	private String usercode = "" ;
	private String Aname = "" ;
	public static CountInfo newInstants(){
		return info ;
	}
	public void setUserCode(String usercode){
		System.out.println("CountInfo"+usercode) ;
		this.usercode = usercode ;
	}
	public String getUserCode(){
		return usercode ;
	}
	public void setAname(String Aname){
		this.Aname = Aname ;
	}
	public String getAname(){
		return Aname ;
	}
	
	
	
}
