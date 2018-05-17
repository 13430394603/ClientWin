package com.clientwin.request;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: LoginRequest 
 * @Description: TODO( 是否允许他人添加 -- 请求服务器传送是否允许添加  -- 让服务器做出下一步动作 ) 
 * @author 威 
 * @date 2017年5月28日 下午1:44:37 
 *
 */
public class AlowFreRequest implements Request{
	private static AlowFreRequest alowFreRequest  = new AlowFreRequest() ;
	public static AlowFreRequest newInstants(){
		return alowFreRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		Message.setTo("##server##") ;
		Message.setFrom(CountInfo.newInstants().getUserCode()) ;
		Message.setType("0004") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
	
}
