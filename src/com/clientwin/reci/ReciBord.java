package com.clientwin.reci;

import java.net.*;
import java.io.*;
/**
 *classNmae:
 *Desc:广播通信,需使用MulticastSokect 类中的joinGroup(); 方法
 *     加入到广播地址中，并且端口号要一致.(接收广播信息)
 *author:
 *Date:4/17
 */
public class ReciBord{
    DatagramPacket pap=null;
    InetAddress ia=null;
    MulticastSocket socket = null;
    boolean flag = true ;
    public ReciBord(){
    	try{
      	    socket=new MulticastSocket(318);
            ia=InetAddress.getByName("239.255.8.0");
            
        }catch(Exception e){
        	System.out.println("获取广播异常") ;
        }
    }
    public String reciIp(){
    	String mess = "" ;
    	try {
			socket.joinGroup(ia);
			byte[] data=new byte[8192];
	        while(flag){
	            pap=new DatagramPacket(data,data.length);
	            flag = false ;
	            socket.receive(pap);
	            mess=new String(pap.getData(),0,pap.getLength());
	            System.out.println("通过广播获取的ip地址" + mess);
	        }
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("自动获取IP异常") ;
		}
    	return mess ;
    }
}
