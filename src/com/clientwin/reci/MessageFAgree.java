package com.clientwin.reci;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.chen.jdbc.SQLOperation;
import com.chen.jdbcutil.DataBaseFormat;
import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.fram.GetWinObject;
import com.clientwin.pool.ChatJPanelPool;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessageFAgree 
 * @Description: TODO(接收AlowFreRequest请求后服务器的是否允许被添加响应) 
 * @author 威 
 * @date 2017年5月27日 下午11:15:45 
 *
 */
public class MessageFAgree implements MessageType {
	private static MessageFAgree messageFAgree = new MessageFAgree () ;
	public static MessageFAgree newInstants(){
		return messageFAgree;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//接收服务器信息进行判断 -- 是否添加成功
		//对方同意被添加好友请求
		//返回true和详细的好友信息
		//信息主体 state fuser email Aname 
		//false
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent());
		String spath = System.getProperty("user.dir") ;
		/*String baci_path = spath + "/src\\com\\clientwin\\img/" ;*/
		String baci_path = spath + "/img/" ;
		if(json.get("result").equals("true")){
			//主动方收到消息
	        Map<String, String> m = new HashMap<String, String>() ;
	        m.put("bu_path", baci_path+"blurdownline.png") ;
	        m.put("ch_path", baci_path+"chdown.png") ;
			GetWinObject.newIstants().getMainFrame().freItem(json.get("user"), json.get("Aname"), m) ;
			//将返回的好友信息插入数据库
			String sql = "INSERT INTO fre_info(usercode,Aname,email,state) VALUE('"+json.get("user")+"','"+json.get("Aname")+"','"+json.get("email")+"','"+json.get("state")+"');" ;
			SQLOperation jdbc = new SQLOperation("root", "123456", DataBaseFormat.MySql) ;
			if(jdbc.doDataOperation(sql, "mq")){
				System.out.println("好友信息保存成功") ;
			}else{
				System.out.println("好友信息保存失败") ;
			}
			//创建同意交友信息回应项
			GetWinObject.newIstants().getMainFrame().createMfreReplyitem(json.get("user"), json.get("Aname"), messageAnaly.getDate(), json.get("msg")) ;
			//如果不是现在打开的窗口那么就提醒
			if("SYSTEM_FRAME" != GetWinObject.newIstants().getMainFrame().new_code){
				//提示消息
				GetWinObject.newIstants().getMainFrame().setAlertMessage("SYSTEM_FRAME", baci_path+"bu系统项-m.png") ;
			}	
		}else if(json.get("result").equals("self")){
			//自己是同意方--只需将好友添加至好友项即可
			//
	        Map<String, String> m = new HashMap<String, String>() ;
	        m.put("bu_path", baci_path+"blurdownline.png") ;
	        m.put("ch_path", baci_path+"chdown.png") ;
			GetWinObject.newIstants().getMainFrame().freItem(json.get("user"), json.get("Aname"), m) ;
			//将返回的好友信息插入数据库
			String sql = "INSERT INTO fre_info(usercode,Aname,email,state) VALUE('"+json.get("user")+"','"+json.get("Aname")+"','"+json.get("email")+"','"+json.get("state")+"');" ;
			SQLOperation jdbc = new SQLOperation("root", "123456", DataBaseFormat.MySql) ;
			if(jdbc.doDataOperation(sql, "mq")){
				System.out.println("好友信息保存成功") ;
			}else{
				System.out.println("好友信息保存失败") ;
			}
		}else{
			//返回的是拒绝交友信息
			System.out.println("接到交友回信，被拒绝") ;
			//创建同意交友信息回应项
			GetWinObject.newIstants().getMainFrame().createMfreReplyitem(json.get("user"), json.get("Aname"), messageAnaly.getDate(), json.get("msg")) ;
			//如果不是现在打开的窗口那么就提醒
			if("SYSTEM_FRAME" != GetWinObject.newIstants().getMainFrame().new_code){
				//提示消息
				GetWinObject.newIstants().getMainFrame().setAlertMessage("SYSTEM_FRAME", baci_path+"bu系统项-m.png") ;
			}	
		}
	}
	public static void main(String[] args){
		String str = "{##from####:######server######,####to####:####55####,####type####:####0004####,####content####:####{result:true,Aname:,user:555,email:55,state:downline,msg:同意您的好友请求}####,####date####:####06/04-16:07####,##}" ;
		AnalyReceMessage messageAnaly = AnalyReceMessage.newInstans() ;
		messageAnaly.delMsg(str) ;
		newInstants().reciMessage(messageAnaly) ;
	}
}