package com.clientwin.reci;

import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.fram.GetWinObject;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessageDownLine 
 * @Description: TODO(注销状态) 
 * @author 威 
 * @date 2017年5月27日 下午11:18:45 
 *
 */
public class MessageDownLine implements MessageType {
	private static MessageDownLine message = new MessageDownLine() ;
	public static MessageDownLine newInstants(){
		return message ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//确认注销
		//信息主体 state： msg：
		//true
		//false
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent());
		
		if(json.get("state").equals("false")){
			GetWinObject.newIstants().getMainFrame().createAlertWin(json.get("msg")) ;
		}else{
			GetWinObject.newIstants().getLoginFrame().setVisible(true) ;
			GetWinObject.newIstants().getMainFrame().setVisible(false) ;
		}
	}

}
