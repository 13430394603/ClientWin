package com.clientwin.reci;

import javax.swing.JPanel;

import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.fram.GetWinObject;
import com.clientwin.pool.ChatJPanelPool;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessageMFre 
 * @Description: TODO(收到陌生人添加好友请求 - 0005) 
 * @author 威 
 * @date 2017年5月27日 下午11:24:26 
 *
 */
public class MessageMFre implements MessageType {
	private static MessageMFre messageMFre = new MessageMFre() ;
	public static MessageMFre newInstants(){
		return messageMFre ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//收到陌生人添加好友请求
		/*
		显示到系统项的面板中
		并依据 - 同意
			 - 不同意
			 通过MessageFAgree对象请求服务器 -- 并传递是否允许添加
		*/
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent()) ;
		if(GetWinObject.newIstants().getMainFrame().chatWinPool.isExist((JPanel) ChatJPanelPool.newInstants().get("SYSTEM_FRAME"))){
			//!chatWinPool.isExist((JPanel) chatWinPool.get(new_code)
			System.out.println("存在系统界面") ;
			GetWinObject.newIstants().getMainFrame().createMfreRequeitem(json.get("user"), json.get("Aname"), messageAnaly.getDate()) ;
			//如果不是现在打开的窗口那么就提醒
			if("SYSTEM_FRAME" != GetWinObject.newIstants().getMainFrame().new_code){
				//提示消息
				/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
				String spath = System.getProperty("user.dir") + "/img/" ;
				GetWinObject.newIstants().getMainFrame().setAlertMessage("SYSTEM_FRAME", spath+"bu系统项-m.png") ;
			}
		}else{
			//创建系统界面
			GetWinObject.newIstants().getMainFrame().createSysFrame("SYSTEM_FRAME", "系统消息") ;
			GetWinObject.newIstants().getMainFrame().createMfreRequeitem(json.get("user"), json.get("Aname"), messageAnaly.getDate()) ;
			//如果不是现在打开的窗口那么就提醒
			if("SYSTEM_FRAME" != GetWinObject.newIstants().getMainFrame().new_code){
				//提示消息
				/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
				String spath = System.getProperty("user.dir") + "/img/" ;
				GetWinObject.newIstants().getMainFrame().setAlertMessage("SYSTEM_FRAME", spath+"bu系统项-m.png") ;
			}
		}
	}

}
