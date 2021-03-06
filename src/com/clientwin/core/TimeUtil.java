package com.clientwin.core;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * <b>时间类<b>
 * @author 威 
 * <br>2017年9月4日 下午7:35:37 
 * @see   <br> G 年代标志符
  <br>y 年
  <br>M 月
  <br>d 日
  <br>h 时 在上午或下午 (1~12)
  <br>H 时 在一天中 (0~23)
  <br>m 分
  <br>s 秒
  <br>S 毫秒
  <br>E 星期
  <br>D 一年中的第几天
  <br>F 一月中第几个星期几
  <br>w 一年中第几个星期
  <br>W 一月中第几个星期
  <br>a 上午 / 下午 标记符 
  <br>k 时 在一天中 (1~24)
  <br>K 时 在上午或下午 (0~11)
  <br>z 时区
 *
 */
public class TimeUtil {
	public static String getDatetime(){
		Date datetime=new Date();
		SimpleDateFormat st=new SimpleDateFormat("MM/dd-HH:mm");
		return st.format(datetime);
	}
	public static void main(String[] args){
		System.out.println(getDatetime()) ;
	}
}
