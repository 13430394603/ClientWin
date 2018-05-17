package com.clientwin.util;
import java.io.*;              //io层所有类--FileWriter
/**
 * 
 * <b>日志工具 <br>需要在源码设置path路径<b>
 * @author 威 
 * <br>2017年9月4日 下午8:06:28 
 *
 */
public class RecordLog{
	private static String path = "D:\\ioTest.txt" ;
	public RecordLog(){
	}
	public static void doStartLog(){
		File f= null ;
    	FileWriter out = null ;
    	BufferedWriter w= null ;
    	try{
    		f = new File(path) ;
    		out = new FileWriter(f, true) ;    //Writer out=new Writer(f);Writer是抽象的类   参数true：从未部写入
    		w = new BufferedWriter(out) ;
    		w.write("\r\n\r\n" + TimeUtil.getDatetime() + " 启动程序") ;
    		w.flush() ;                                              //刷新缓冲流
    		w.close() ;
    	}catch(IOException e){ 
       		e.printStackTrace() ;
    	}
	}
	public static void doLog(String logStr){
		File f= null ;
    	FileWriter out = null ;
    	BufferedWriter w= null ;
    	try{
    		f = new File(path) ;
    		out = new FileWriter(f, true) ;    //Writer out=new Writer(f);Writer是抽象的类   参数true：从未部写入
    		w = new BufferedWriter(out) ;
    		w.write("\r\n" + TimeUtil.getDatetime() + " " +logStr) ;
    		w.flush() ;                                              //刷新缓冲流
    		w.close() ;
    	}catch(IOException e){ 
    		e.printStackTrace() ;
    	}
	}
}

