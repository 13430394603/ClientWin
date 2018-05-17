package com.clientwin.request;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;
/**
 * 
 * @ClassName: PersonRequest 
 * @Description: TODO(个人信息 -- 0002) 
 * @author 威 
 * @date 2017年5月28日 下午2:44:26 
 *
 */
public class PersonRequest implements Request{
	private static PersonRequest personRequest  = new PersonRequest() ;
	public static PersonRequest newInstants(){
		return personRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		Message.setFrom(CountInfo.newInstants().getUserCode()) ;
		Message.setType("0002") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
	}
}
