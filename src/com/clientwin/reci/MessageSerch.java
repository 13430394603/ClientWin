package com.clientwin.reci;

import java.util.List;
import java.util.Map;

import com.clientwin.core.AnalyReceMessage;
import com.clientwin.core.ArrayJson;
import com.clientwin.core.SSData;
import com.clientwin.fram.GetWinObject;
import com.clientwin.service.MessageType;

/**
 * 
 * @ClassName: MessageSerch 
 * @Description: TODO(接收查找好友信息处理) 
 * @author 威 
 * @date 2017年5月27日 下午11:06:43 
 *
 */
public class MessageSerch implements MessageType {
	private static MessageSerch messageSerch = new MessageSerch() ;
	public static MessageSerch newInstants(){
		return messageSerch ;
	}
	@Override
	public void reciMessage(AnalyReceMessage messageAnaly) {
		dealMessage(messageAnaly) ;
	}

	@Override
	public void dealMessage(AnalyReceMessage messageAnaly) {
		//查找好友请求是否存在
		//存在发送查询的详细信息
		//以json的形式返回
		//信息主体 state(失败时为false) msg(失败时提示) Aname User
		ArrayJson json = new ArrayJson() ;
		json.dealMessage(messageAnaly.getContent());
		if(json.get("state").equals("false")){
			//不存在false
			json.get("msg") ;
		}else{
			SSData ss = new SSData() ;
			List<Map<String, String>> lists = ss.toList(json.get("result")) ;
			if(lists.size() != 0){
				for(Map<String, String> maps : lists){
					GetWinObject.newIstants().getMainFrame().createSerchResultItem(maps.get("user"), maps.get("Aname")) ;
					System.out.println("创建项") ;
				}
			}
		}
	}
}