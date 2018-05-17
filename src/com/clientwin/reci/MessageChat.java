package com.clientwin.reci;

import javax.swing.JPanel;

import com.chen.jdbc.SQLOperation;
import com.chen.jdbcutil.DataBaseFormat;
import com.clientwin.core.AnalyReceMessage;
import com.clientwin.fram.ConnFram;
import com.clientwin.fram.GetWinObject;
import com.clientwin.fram.MainFrame;
import com.clientwin.pool.ChatJPanelPool;
import com.clientwin.service.MessageType;
/**
 * 
 * @ClassName: MessageChat 
 * @Description: TODO(接收基本的聊天信息) 
 * @author 威 
 * @date 2017年5月27日 下午11:23:40 
 *
 */
public class MessageChat implements MessageType{
	private static MessageChat messageChat = new MessageChat() ;
	public static MessageChat newInstants(){
		return messageChat ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//基本的聊天信息
		//获取用户名，并查询好友池
		//通过并显示在好友的聊天窗口中
		//信息主体聊天信息
		//有到信息判断与聊天界面是已创建 -- 再添加模板
		//chatMudle(true, userCode, message) ;
		messageAnaly.getContent() ;
		messageAnaly.getFrom() ;
		if(GetWinObject.newIstants().getMainFrame().chatWinPool.isExist((JPanel) GetWinObject.newIstants().getMainFrame().chatWinPool.get(messageAnaly.getFrom()))){
			//存在界面直接创建信息
			System.out.println("存在聊天窗口") ;
			GetWinObject.newIstants().getMainFrame().chatMudle(true, messageAnaly.getFrom(), messageAnaly.getContent()) ;
			//如果不是现在打开的窗口那么就提醒
			System.out.println(messageAnaly.getFrom()+"-"+GetWinObject.newIstants().getMainFrame().new_code) ;
			if(!messageAnaly.getFrom().equals(GetWinObject.newIstants().getMainFrame().new_code)){
				//提示消息
				System.out.println("创建提示") ;
				/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
				String spath = System.getProperty("user.dir") + "/img/" ;
				GetWinObject.newIstants().getMainFrame().setAlertMessage(messageAnaly.getFrom(), spath+"messa.png") ;
			}
		} else{
			System.out.println("不存在聊天窗口-需创建") ;
			SQLOperation jdbc = new SQLOperation("root","123456", DataBaseFormat.MySql) ;
			//创建聊天界面
			GetWinObject.newIstants().getMainFrame().createChatMudle(messageAnaly.getFrom(), (String) jdbc.doQuery("mq", "select Aname from fre_info where usercode= '"+messageAnaly.getFrom()+"' ", "Aname").get(0).get("Aname")) ;
			//创建信息
			System.out.println("创建信息") ;
			GetWinObject.newIstants().getMainFrame().chatMudle(true, messageAnaly.getFrom(), messageAnaly.getContent()) ;
		}
	}

}
