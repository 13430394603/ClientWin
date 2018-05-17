package com.clientwin.core;
/**
 * 
 * @ClassName: TestLine 
 * @Description: TODO(处理主体信息在JLabel中换行) 
 * @author 威 
 * @date 2017年5月29日 下午10:12:13 
 *
 */
//当主体信息少于20个时  getLine方法就会返回 0 具体变动按依次递增的规律
//Message返回可换行的数据
//
//
public class TestLine {
	private int line = 0 ;
	private static TestLine tcount = new TestLine() ;
	private int length = 0 ;
	public static TestLine newInstants(){
		return tcount ;
	}
	public int getLine(){
		return line ; 
	}
	/**
	 * 
	 * 将要发送的信息处理后返回
	 * @see
	 * @param message
	 * @return
	 * String
	 *
	 */
	public String getMessage(String message){
		length = message.length() ;
		line = length/20 ;
		StringBuffer sb = new StringBuffer() ;
		int start, end;
		for (int i = 0; i < line+1; i++){
			start = i*20 ;
			end = i!=line?(i+1)*20+1:length ;
			sb.append(message.substring(start,end) + (i!=line?"<br>":"")) ;
		}
		return "<html><body>"+sb.toString()+"</body></html>" ;
	}
	/**
	 * 
	 * 聊天容器存储池的高度 
	 * @see
	 * @return
	 * int
	 *
	 */
	public int getHeight1(){
		int height = 0 ;
		switch(line){
			case 0 : height = 70 ; break ;
			case 1 : height = 76 ; break ;
			case 2 : height = 94 ; break ;
			case 3 : height = 117 ; break ;
		}
		return height ;
	}
	/**
	 * 
	 * 这里用一句话描述这个方法的作用 
	 * @see
	 * @return
	 * int
	 *
	 */
	public int getHeight2(){
		int height = 0 ;
		switch(line){
			case 0 : height = 23 ; break ;
			case 1 : height = 38 ; break ;
			case 2 : height = 58 ; break ;
			case 3 : height = 78 ; break ;
		}
		return height ;
	}
	public int getWidth2(){
		if(line == 0){
			if(length == 1)
				return 2*14 ;
			else
				return length*14 ;
		}
		return 280 ;
	}
	public static void main(String[] args){
		TestLine line = TestLine.newInstants() ;
		System.out.println(line.getMessage("哈打开士大夫加上快递费来设计圣诞节疯狂记事六答复链接爱国看来就爱就放开手的解放军阿克苏的积分数据库打飞机临时卡大姐夫")) ;
		System.out.println(line.getLine()) ;
	}
}
