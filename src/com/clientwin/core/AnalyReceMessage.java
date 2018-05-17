package com.clientwin.core;

/**
 * 
 * @ClassName: DelMessage 
 * @Description: TODO(解析收到的信息) 
 * @author 威 
 * @date 2017年5月27日 下午5:14:05 
 *
 */
public class AnalyReceMessage{
	private String fromUserName = "" ;
	private String toUserName = "" ;
	private String messageType = "" ;
	private String content = "" ;
	private String date = "" ;
    private static AnalyReceMessage delMessage = new AnalyReceMessage() ;
	public static AnalyReceMessage newInstans(){
		return delMessage ;
	}
	//处理接收信息
	public void delMsg(String message){
		fromUserName = delMsgg(message, "from") ;
		toUserName = delMsgg(message, "to") ;
		messageType = delMsgg(message, "type") ;
		content = delMsgg(message, "content") ;
		date = delMsgg(message, "date") ;
	}
	private String delMsgg(String message, String type){
		int startIndex = message.indexOf("##"+type+"##") + ("##"+type+"####:####").length() ;
		int endIndex = message.indexOf("##,##", startIndex) - "##".length() ;
		if(startIndex!=endIndex){
			try{
				return message.substring(startIndex, endIndex) ;
			}catch(StringIndexOutOfBoundsException e){
				System.out.println("信息不符合规范") ; 
			}
		}
		return null ;
	}
	//获取来信息人
	public String getFrom(){
		return fromUserName ;
	}
	//获取接收人
	public String getTo(){
		return toUserName ;
	}
	//获取信息的类型
	public String getType(){
		return messageType ;
	}
	//获取主体
	public String getContent(){
		return content ;
	}
	//获取信息的发送时间
	public String getDate(){
		return date ;	
	}	
}
