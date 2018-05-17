package com.clientwin.request;

import com.clientwin.conn.ConnParam;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.service.Request;
import com.clientwin.util.RecordLog;

/**
 * 
 * @ClassName: LoginRequest 
 * @Description: TODO(登录) 
 * @author 威 
 * @date 2017年5月28日 下午1:36:24 
 *
 */
public class LoginRequest implements Request{
	private static LoginRequest loginRequest  = new LoginRequest() ;
	public LoginRequest(){
		RecordLog.doLog("Login后台请求") ;
	}
	public static LoginRequest newInstants(){
		return loginRequest ;
	}
	@Override
	public void doRequest(CrateSendMessage Message) {
		RecordLog.doLog("Login后台请求 - 准备向服务器发送请求") ;
		// TODO Auto-generated method stub
		//传入一个ArrayJson对象
		//这个对象类可以生成ArrayJson也可以反向解析成集合
		Message.setType("0000") ;
		Message.setFrom(ConnParam.getIP()) ;
		Message.setTo("##server##") ;
		Message.setDate(TimeUtil.getDatetime()) ;
		MessageFactory.newInstants().messageSend(Message) ;
		RecordLog.doLog("Login后台请求 - 向服务器发送请求完毕") ;
	}
}
