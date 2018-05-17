package com.clientwin.core;

/**
 * 
 * @ClassName: GenerateMessage 
 * @Description: TODO(生成可发送信息) 
 * @author 威 
 * @date 2017年5月27日 下午5:41:09 
 *
 */
public class CrateSendMessage{
	private String fromUserName = "" ;
	private String toUserName = "" ;
	private String messageType = "" ;
	private String content = "" ;
	private String date = "" ;
	private static CrateSendMessage msgFactory= new CrateSendMessage() ;
	public static CrateSendMessage newInstans(){
		return msgFactory ;
	}
	//发送人
	public void setFrom(String UserName){
		fromUserName = UserName ;
	}
	//接收人
	public void setTo(String UserName){
		toUserName = UserName ;
	}
	//信息类型
	public void setType(String type){
		messageType = type ;
	}
	//信息主体
	public void setContent(String content){
		this.content = content ;
	}
	//信息发送时间
	public void setDate(String dateString){
		date = dateString ;
	}
	public String getCompleteMessage(){
		//为了防止意外发生还需要过滤掉相关的信息--保证信息的可靠性
		return "{##from####:####"+fromUserName+"####,##" 
		+ "##to####:####"+toUserName+"####,##"
		+ "##type####:####"+messageType+"####,##"
		+ "##content####:####"+content+"####,##"
		+ "##date####:####"+date+"####,##"
		+ "}" ;
	}
}
