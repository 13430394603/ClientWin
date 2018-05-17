package com.clientwin.conn;

import java.net.InetAddress;
import java.net.Socket;

import com.clientwin.main.MessageFactory;
import com.clientwin.util.RecordLog;

import java.net.*;
import java.io.*;
public class Client {
	static Socket socket=null;
	static InetAddress addr=null;
	static InetSocketAddress saddr=null;
	static DataInputStream in=null;
	static DataOutputStream out=null;
	static boolean flag_read=true;
	static boolean flag_write=true;
	private static Client c = new Client() ;
	public static Client newInstants(){
		return c ;
	}
	public Client(){
		onConnnect() ;
	}
	//连接
    private static void onConnnect(){
		try{
			System.out.println(ConnParam.getIP()) ;
			System.out.println(Integer.parseInt(ConnParam.getPort())) ;
			addr=InetAddress.getByName(ConnParam.getIP());
			saddr=new InetSocketAddress(addr, Integer.parseInt(ConnParam.getPort()));
			socket=new Socket();
			socket.connect(saddr);
			System.out.println("成功") ;
			in=new DataInputStream(socket.getInputStream());
			out=new DataOutputStream(socket.getOutputStream());
		}catch(IOException e){
			RecordLog.doLog("与服务器连接失败") ;
			System.out.println("连接失败") ;
		}
		new ReadThread();
	}
	//写
	public static void write_(String sendContent){
		System.out.println("S-write");
		try{
			out.writeUTF(sendContent);
			System.out.println("发送信息："+sendContent);
		}catch(Exception e){
			RecordLog.doLog("发送信息失败") ;
			System.out.println("服务器连接失败");
			//提示与服务器断开
		}
		System.out.println("E-write");
	}
	//读
	public static void read_(){
		System.out.println("S-read");
		String mess="";
			try{
			mess=in.readUTF() ;
			if(mess!=null){
				System.out.println("收到信息："+mess);
			}
			//消息进入工厂
			MessageFactory.newInstants().messageCome(mess) ;
		}catch(Exception e){
			RecordLog.doLog("收到信息失败，对方可能掉线") ;
			flag_read=false;
			System.out.println("对方离线");
			e.printStackTrace() ;
		}
		System.out.println("E-read");  
	}
	public static void main(String[] args){
		onConnnect() ;
	}
}
class ReadThread extends Thread
{
	ReadThread(){
	    start();
	}
	public void run(){
	    while(Client.flag_read){
		    Client.read_();
		}
	}
}
