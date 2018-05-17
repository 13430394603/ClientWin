package com.clientwin.request;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: DownLineRequest 
 * @Description: TODO(注销请求类) 
 * @author 威 
 * @date 2017年5月28日 下午1:40:34 
 *
 */
public class DownLineRequest implements Request{
	private static DownLineRequest downlienRequest  = new DownLineRequest() ;
	public static DownLineRequest newInstants(){
		return downlienRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		Message.setFrom(CountInfo.newInstants().getUserCode()) ;
		Message.setTo("##server##") ;
		Message.setType("0006") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
	
}