package com.clientwin.request;

import com.clientwin.conn.ConnParam;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: RegisterRequest 
 * @Description: TODO(注册请求) 
 * @author 威 
 * @date 2017年5月28日 下午1:40:57 
 *
 */
public class RegisterRequest implements Request{
	private static RegisterRequest registerRequest  = new RegisterRequest() ;
	public static RegisterRequest newInstants(){
		return registerRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		Message.setFrom(ConnParam.getIP()) ;
		Message.setTo("##server##") ;
		Message.setType("0001") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
}