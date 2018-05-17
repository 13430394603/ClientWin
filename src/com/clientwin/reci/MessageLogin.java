package com.clientwin.reci;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chen.jdbc.SQLOperation;
import com.chen.jdbcutil.DataBaseFormat;
import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.core.CountInfo;
import com.clientwin.core.SSData;
import com.clientwin.fram.GetWinObject;
import com.clientwin.fram.LoginFrame;
import com.clientwin.fram.MainFrame;
import com.clientwin.service.MessageType;
import com.clientwin.util.RecordLog;

/**
 * 
 * @ClassName: MessageLogin 
 * @Description: TODO(处理登录信息类) 
 * @author 威 
 * @date 2017年5月27日 下午10:30:34 
 *
 */
public class MessageLogin implements MessageType{
	private static MessageLogin messageLogin = new MessageLogin() ;
	public static MessageLogin newInstants() {
		return messageLogin ;
	}
	public MessageLogin() {
		RecordLog.doLog("Login后台接收") ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		RecordLog.doLog("Login后台接收 - 解析传递") ;
		dealMessage(messageAnaly) ;
	}
	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		RecordLog.doLog("Login后台接收 - 深入解析") ;
		/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
		String spath = System.getProperty("user.dir") + "/img/" ;
		RecordLog.doLog("Login后台接收 - 深入解析中》") ;
		//登录验证是否成功
		//成功 -- 跳转
		//失败 -- 提示失败 -- 依然在原页面
		//信息主体 -- state msg(在出错时才有)
		ArrayJson json = new ArrayJson() ;
		RecordLog.doLog("Login后台接收 - 深入解析中》》") ;
		json.dealMessage(messageAnaly.getContent()) ;
		RecordLog.doLog("Login后台接收 - 深入解析中》》》") ;
		//SQLOperation opar = new SQLOperation("root", "123456", DataBaseFormat.MySql) ;
		RecordLog.doLog("Login后台接收 - 深入解析中》》》》") ;
		if(json.get("state").equals("false")){
			RecordLog.doLog("Login后台接收 - 收到服务器错误状态 S") ;
			System.out.println(json.getMessage()) ;
			//失败 -- 提示失败 -- 依然在原页面
			GetWinObject.newIstants().getLoginFrame().err_win(json.get("msg")) ;
			RecordLog.doLog("Login后台接收 - 收到服务器错误状态 E") ;
		}else if(json.get("state").equals("true")){
			RecordLog.doLog("Login后台接收 - 收到服务器正确状态 S") ;
			CountInfo.newInstants().setUserCode(messageAnaly.getTo()) ;
			CountInfo.newInstants().setAname((String) json.get("Aname")) ;
			//跳转 -- 恢复页面
			RecordLog.doLog("Login后台接收 - 收到服务器正确状态 将要跳转主界面") ;
			MainFrame mainFrame = new MainFrame() ;
			GetWinObject.newIstants().getLoginFrame().setVisible(false) ;
			//解析好友状态
			//SSData -- 编辑好友状态数据
			SSData ss = new SSData() ;
			System.out.println("json.get(fre_info)"+json.get("fre_info")) ;
			List<Map<String, String>> listss = ss.toList(json.get("fre_info")) ;
			//设置状态
			if(listss.size() != 0){
				for(Map<String, String> maps : listss){
					boolean flag = maps.get("state") == "online" ? true : false ;
			        Map<String, String> m = new HashMap<String, String>() ;
			        if(flag){
			        	m.put("bu_path", spath+"blurnline.png") ;
				        m.put("ch_path", spath+"chline.png") ;
			        }else{
			        	m.put("bu_path", spath+"blurdownline.png") ;
				        m.put("ch_path", spath+"chdown.png") ;
			        }
			        mainFrame.freItem(maps.get("usercode"), maps.get("Aname"), m) ;	
				}
			}
			RecordLog.doLog("Login后台接收 - 收到服务器正确状态 恢复主界面中") ;
			//确认连接过
			LoginFrame.flag_close = true ;
			GetWinObject.newIstants().getLoginFrame().setVisible(false) ;
			RecordLog.doLog("Login后台接收 - 收到服务器正确状态 恢复主界面完毕") ;
			RecordLog.doLog("Login后台接收 - 收到服务器正确状态 E") ;
		}else{
			System.out.println("有错误信息") ;
			RecordLog.doLog("Login后台接收 - 收到服务器传来的无效信息") ;
		}
	}
}