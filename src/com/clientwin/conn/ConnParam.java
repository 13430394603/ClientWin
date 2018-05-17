package com.clientwin.conn;
/**
 * 
 * @ClassName: ConnParam 
 * @Description: TODO(连接服务器的配置参数可以 通过类名直接设置和获取) 
 * @author 威 
 * @date 2017年5月30日 下午5:39:20 
 *
 */
public class ConnParam {
	private static ConnParam connPram = new ConnParam() ;
	private static String IP = "" ;
	private static String port = "" ;
	public static ConnParam newInstants(){
		return connPram ;
	}
	public void setIp(String ip){
		IP = ip ;
	}
	public void setPort(String p){
		port = p ;
	}
	public static String getIP(){
		return IP ;
	}
	public static String getPort(){
		return port ;
	}
	
}
