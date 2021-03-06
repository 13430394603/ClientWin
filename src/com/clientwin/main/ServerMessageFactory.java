package com.clientwin.main;

import com.clientwin.core.AnalyReceMessage;
import com.clientwin.reci.MessageDownLine;
import com.clientwin.reci.MessageFAgree;
import com.clientwin.reci.MessageLogin;
import com.clientwin.reci.MessagePer;
import com.clientwin.reci.MessageRegister;
import com.clientwin.reci.MessageSerch;
import com.clientwin.service.MsgFactory;

/**
 * 
 * @ClassName: DealServerMessage 
 * @Description: TODO(处理服务器信息) 
 * @author 威 
 * @date 2017年5月27日 下午6:27:34 
 *
 */
public class ServerMessageFactory  implements MsgFactory {
	private static ServerMessageFactory deal = new ServerMessageFactory() ;
	public static ServerMessageFactory newInstants(){
		return deal ;
	}
	public void messageCome(AnalyReceMessage messageAnaly){
		dealMessage(messageAnaly) ;
	}
	public void dealMessage(AnalyReceMessage messageAnaly){
		if(messageAnaly.getType().equals("0000")){
			MessageLogin.newInstants().reciMessage(messageAnaly); 
			System.out.print("登录验证") ;
		}else if(messageAnaly.getType().equals("0001")){
			MessageRegister.newInstants().reciMessage(messageAnaly);
			System.out.print("注册验证") ;
		}else if(messageAnaly.getType().equals("0002")){
			MessagePer.newInstants().reciMessage(messageAnaly);
			System.out.print("个人信息") ;
		}else if(messageAnaly.getType().equals("0003")){
			MessageSerch.newInstants().reciMessage(messageAnaly);
			System.out.print("查找好友") ;
		}else if(messageAnaly.getType().equals("0004")){
			MessageFAgree.newInstants().reciMessage(messageAnaly);
			System.out.print("对方同意被添加") ;
		}else if(messageAnaly.getType().equals("0006")){
			MessageDownLine.newInstants().reciMessage(messageAnaly);
			System.out.print("确认注销") ;
		}
		else{
			//将错误信息秘密返回给服务器，服务器记录下当前错误信息等待修补漏洞
			System.out.print("错误信息");
		}
		
	}
	
}
