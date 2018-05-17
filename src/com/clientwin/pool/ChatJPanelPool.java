package com.clientwin.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.clientwin.service.*;

public class ChatJPanelPool implements Pool{
	private static ChatJPanelPool pool = new ChatJPanelPool() ;
	private List<Map<String, JPanel>> lists = new ArrayList<Map<String, JPanel>>() ;
	private Map<String, JPanel> map = null ;
	private int length = 0 ;
	public static ChatJPanelPool newInstants() {
		return pool ;
	}
	@Override
	public void put(String userName, Object jpanel) {
		map = new HashMap<String, JPanel>() ;
		map.put(userName, (JPanel)jpanel) ;
		lists.add(map) ;
		length ++ ;
	}

	@Override
	public Object get(String userName) {
		for(Map<String, JPanel> map : lists){
			for(Map.Entry<String, JPanel> item : map.entrySet()){
				if(item.getKey().equals(userName)){
					return item.getValue() ;
				}
			}
		}
		return null ;
	}

	@Override
	public void remove(String userName) {
		//map = new HashMap<String, JPanel>() ;
		for(Map<String, JPanel> maps : lists){
			for(Map.Entry<String, JPanel> item : maps.entrySet()){
				if(item.getKey().equals(userName)){
					lists.remove(maps) ;
					length -- ;
				}
			}
		}
	}
	/**
	 * 通过JPanel去获取用户名
	 */
	public String getUserName(JPanel jpanel){
		for(Map<String, JPanel> row : lists){
			for(Map.Entry<String, JPanel> item : row.entrySet()){
				if(item.getValue().equals((JPanel)jpanel)){
					return item.getKey() ;
				}
			}
		}
		return "" ;
	}
	public boolean isExist(JPanel jpanel){
		for(Map<String, JPanel> maps : lists){
			for(Map.Entry<String, JPanel> item : maps.entrySet()){
				if(item.getValue().equals(jpanel)){
					System.out.println("存在的") ;
					return true ;
				}
			}
		}
		return false ;
	}
	
	public int getSize(){
		return length ;
	}
	/**
	 * 获得所有
	 */
	public List<Map<String, JPanel>> getAll() {
		return lists ;
	}
	

}
