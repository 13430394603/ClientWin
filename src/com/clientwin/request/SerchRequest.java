package com.clientwin.request;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: LoginRequest 
 * @Description: TODO(查找好友请求) 
 * @author 威 
 * @date 2017年5月28日 下午1:41:40 
 *
 */
public class SerchRequest implements Request{
	private static SerchRequest serchReuest  = new SerchRequest() ;
	public static SerchRequest newInstants(){
		return serchReuest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		Message.setFrom(CountInfo.newInstants().getUserCode()) ;
		Message.setTo("##server##") ;
		Message.setType("0003") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
	
}
