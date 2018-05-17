package com.clientwin.main;
import com.clientwin.core.AnalyReceMessage;
import com.clientwin.reci.MessageChat;
import com.clientwin.reci.MessageMFre;
import com.clientwin.service.MsgFactory;
/**
 * 
 * @ClassName: FriendMessageFactory 
 * @Description: TODO(好友信息接收处理工厂) 
 * @author 威 
 * @date 2017年5月27日 下午9:52:59 
 *
 */
public class FriendMessageFactory implements MsgFactory {
	private static FriendMessageFactory friendmessage = new FriendMessageFactory() ;
	/**
	 * 
	 * @Title: newInstants 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * FriendMessageFactory
	 *
	 */
	public static FriendMessageFactory newInstants(){
		return friendmessage ;
	}
	/**
	 * 信息进入
	 */
	public void messageCome(AnalyReceMessage messageAnaly){
		dealMessage(messageAnaly) ;
	}
	/**
	 * 处理进入信息
	 */
	public void dealMessage(AnalyReceMessage messageAnaly){
		if(messageAnaly.getType().equals("0008")){
			MessageChat.newInstants().reciMessage(messageAnaly);
			System.out.print("聊天信息") ;
			
		}else if(messageAnaly.getType().equals("0005")){
			MessageMFre.newInstants().reciMessage(messageAnaly);
			System.out.print("陌生人添加好友请求") ;
		}
		else{
			//将错误信息秘密返回给服务器，服务器记录下当前错误信息等待修补漏洞
			System.out.print("错误信息");
		}
	}
	public static void main(String[] args){
		//测试内容
		FriendMessageFactory ser = FriendMessageFactory.newInstants() ;
		String mm = "{##from####:####12356####,##" 
				+ "##to####:######server######,##"
				+ "##type####:####0005####,##"
				+ "##content####:####hello####,##"
				+ "##date####:####2017####,##"
				+ "}" ;
		AnalyReceMessage msg = AnalyReceMessage.newInstans() ;
		msg.delMsg(mm);
		ser.dealMessage(msg);
	}
}
