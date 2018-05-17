package com.clientwin.core;

import java.util.HashMap;
import java.util.Map;

import com.clientwin.service.Json;

public class ArrayJson implements Json {
	
	Map<String, String> maps = new HashMap<String, String>() ;
	public ArrayJson(){
		
	}
	@Override
	public void put(String key, String value) {
		maps.put(key, value) ;
	}
	public String getMessage(){
		StringBuffer sb = new StringBuffer() ;
		boolean flag = true ;
		for(Map.Entry<String, String> item : maps.entrySet()){
			sb.append(flag?"":",") ;
			flag = false ;
			sb.append(item.getKey()+":"+item.getValue()) ;
		}
		return "{"+sb.toString()+"}";
	}

	@Override
	public String get(String key) {
		return maps.get(key) ;
	}
	public void dealMessage(String message){
		message = message.substring(message.indexOf("{")+1, message.indexOf("}")) ;
		String[] msgArs = message.split(",") ;
		for(int i = 0; i < msgArs.length; i++){
			maps.put(msgArs[i].substring(0, msgArs[i].indexOf(":")), msgArs[i].substring(msgArs[i].indexOf(":")+1, msgArs[i].length())) ;
		}
	}
	public static void main(String[] args) throws CloneNotSupportedException{
		ArrayJson json = new ArrayJson();
		json.put("pass", "1235");
		ArrayJson js = new ArrayJson();
		String msg = "{user:1235}" ;
		js.dealMessage(msg) ;
		System.out.println(js.get("user"));
		System.out.println(json.getMessage());
	}
	 
}
