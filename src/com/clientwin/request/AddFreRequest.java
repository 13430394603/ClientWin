package com.clientwin.request;

import com.clientwin.conn.ConnParam;
import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;

/**
 * 
 * @ClassName: AddFreRequest 
 * @Description: TODO(添加好友请求) 
 * @author 威 
 * @date 2017年5月28日 下午1:43:42 
 *
 */
public class AddFreRequest implements Request{
	private static AddFreRequest addFreRequest  = new AddFreRequest() ;
	public static AddFreRequest newInstants(){
		return addFreRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage message) {
		message.setFrom(CountInfo.newInstants().getUserCode()) ;
		message.setType("0005") ;  //常量
		message.setDate(TimeUtil.getDatetime()) ;  //调用工具类获取时间
		MessageFactory.newInstants().messageSend(message);
	}
}
