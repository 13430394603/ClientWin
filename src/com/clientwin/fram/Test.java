package com.clientwin.fram;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.chen.jdbc.SQLOperation;
import com.chen.jdbcutil.DataBaseFormat;

public class Test{
	public static void main(String[] args){
		InetAddress address = null ;
		InetAddress address1 = null ;
		try {
			//win7-PC/10.1.208.96 输入主机名可以获取IP地址
			//www.baidu.com/183.232.231.172
			address = InetAddress.getByName("10.1.208.98") ;
			//address.getHostName() 会返回你提供的四分四段式的主机名
			
			//address1 = InetAddress.getByAddress(String, byte[]) ;
			address1 = InetAddress.getLocalHost() ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("主机名"+address.getHostName());
		System.out.println("返回address"+address.getAddress());
		System.out.println("IP地址"+address.getHostAddress());
		System.out.println("主机名"+address.getCanonicalHostName());
		System.out.println("获取IP地址"+address1);
		//System.out.println("查找ip主机名"+address.getCanonicalHostName()) ;
		try {
			System.out.print("检测是否可达"+address.isReachable(3000));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ip = address1.toString() ;
		String ipp = ip.substring(ip.indexOf("/")+1, ip.length()) ;
		System.out.println(ipp);
	}
}
