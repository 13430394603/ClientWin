package com.clientwin.core;
//已被替代
public class MassageTool {
	private static MassageTool tool = new MassageTool() ;
	public static MassageTool newInstans (){
		return tool ;
	}
	public void delMassage(String delContent){
		//查找发送至用户名的起始位置
		int startIndex = delContent.indexOf("##to##") + "##to##".length() + 1 ;
		//查找逗号的位置
		int endIndex =  delContent.indexOf("##,##", startIndex);
		String ToString = delContent.substring(startIndex, endIndex) ;
		//筛选出客户端发送给服务器的信息
		if(ToString.equals("server")){
			//调用客户端处理对象
			//ServerTool.newInstans().
		}
		//其他聊天信息转发出去
		else{
			//根据发送对象ToString获取查询用户在线状态
			
			//在线 -- 从用户池选择对象 -- 调用发送方法
		}
	}
}
