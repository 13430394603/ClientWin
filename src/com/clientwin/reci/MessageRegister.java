package com.clientwin.reci;

import com.chen.jdbc.SQLOperation;
import com.chen.jdbcutil.DataBaseFormat;
import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.fram.GetWinObject;
import com.clientwin.fram.LoginFrame;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessageRegister 
 * @Description: TODO(处理注册信息) 
 * @author 威 
 * @date 2017年5月27日 下午10:58:36 
 *
 */
public class MessageRegister implements MessageType {
	private static MessageRegister messageRegister = new MessageRegister() ;
	public static MessageRegister newInstants(){
		return messageRegister ;
	}
	/**
	 * 承接信息
	 */
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}
	/**
	 * 信息的处理
	 */
	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//注册验证是否成功
		//成功 -- 跳转
		//失败 -- 提示失败 -- 依然在原页面
		//信息主体 state msg(在出错时可以调用)
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent());
		
		if(json.get("state").equals("false")){
			//失败 -- 提示失败 -- 依然在原页面
			GetWinObject.newIstants().getRegisterFrame().err_win(json.get("msg")) ;
		}else{
			//创建数据库 -- 如何创建 -- 同写在一个数据库中 -- 表分别加上用户名标记
			/*String sql = "CREATE DATABASE m"+ usercode ;
			SQLOperation opar = new SQLOperation("root", "123456",
					DataBaseFormat.MySql) ;
			//设置编码
			String sql1 = "set names utf8" ;
			String fresql = "CREATE TABLE fre_info("
				+ "usercode VARCHAR(16) NOT NULL PRIMARY KEY,"
				+ "Aname VARCHAR(20) NULL,"
				+ "email VARCHAR(26) NULL,"
				+ "state VARCHAR(10) NULL"
				+ ")" ;
			if(opar.doDataOperation(sql, "")){
				System.out.println("创建数据库成功--创建表") ;
				if(opar.doDataOperation(sql1, "") && opar.doDataOperation(fresql, "m" + usercode)){
					
					return ;
				}
				GetWinObject.newIstants().getRegisterFrame().err_win("操作数据库异常") ;
			}else{
				GetWinObject.newIstants().getRegisterFrame().err_win("创建数据库失败") ;
			}*/
			GetWinObject.newIstants().getRegisterFrame().setVisible() ;
			new LoginFrame() ;
		}
	}
}
