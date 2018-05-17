package com.clientwin.reci;

import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.fram.GetWinObject;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessagePer 
 * @Description: TODO(接收个人信息保存状态) 
 * @author 威 
 * @date 2017年5月27日 下午11:11:17 
 *
 */
public class MessagePer implements MessageType{
	private static MessagePer messagePer = new MessagePer() ;
	public static MessagePer newInstants(){
		return messagePer ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//确认个人信息是否成功
		//成功 -- 跳转页面
		//失败 -- 提示失败 -- 依然在原页面
		//信息主体 state msg(在出错时有)
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent());
		if(json.get("state").equals("false")){
			//失败 -- 提示失败 -- 依然在原页面
			GetWinObject.newIstants().getMainFrame().createAlertWin(json.get("msg")) ;
		}else{
			GetWinObject.newIstants().getMainFrame().createAlertWin(json.get("msg")) ;
		}
		//成功 -- 跳转页面
	}

}
