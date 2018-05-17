package com.clientwin.core;
/**
 * 
 * @ClassName: ServerTool 
 * @Description: TODO(接收到发送于发送于服务端的信息进行处理) 
 * @author 威 
 * @date 2017年5月26日 下午10:13:31 
 *
 */
//已被ServerMassageFactory替代
public class ServerTool {
	private static ServerTool serverTool = new ServerTool() ;
	public static ServerTool newInstans(){
		return serverTool ;
	}
	public void doTool(String delContent){
		int startIndex = delContent.indexOf("##type##") + "##type##".length() + 1 ;
		int endIndex = delContent.indexOf("##,##", startIndex) ;
		String typeString = delContent.substring(startIndex, endIndex) ;
		if(typeString.equals("0000")){
			//登录验证请求
			
		}else if(typeString.equals("0001")){
			//注册验证请求
			
		}else if(typeString.equals("0002")){
			//个人信息请求
			
		}else if(typeString.equals("0003")){
			//查找好友请求
			
		}else if(typeString.equals("0004")){
			//对方同意被添加好友请求
			
		}else if(typeString.equals("0005")){
			//添加好友请求
			
		}else if(typeString.equals("0006")){
			//注销请求
			
		}else if(typeString.equals("0007")){
			//程序退出请求
			
		}
	}
	
	
	
}
