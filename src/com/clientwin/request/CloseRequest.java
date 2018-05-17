package com.clientwin.request;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: CloseRequest 
 * @Description: TODO(程序退出请求) 
 * @author 威 
 * @date 2017年5月28日 下午1:45:50 
 *
 */
public class CloseRequest implements Request{
	private static CloseRequest closeRequest  = new CloseRequest() ;
	public static CloseRequest newInstants(){
		return closeRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		
		Message.setTo("##server##") ;
		Message.setType("0007") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
	
}
