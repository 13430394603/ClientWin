package com.clientwin.fram;
/**
 * 
 * @ClassName: GetWinObject 
 * @Description: TODO(获取所有启动过的界面的对象) 
 * @author 威 
 * @date 2017年6月1日 下午1:48:01 
 *
 */
public class GetWinObject {
	private ConnFram conn = null ;
	private RegisterFrame register = null ;
	private LoginFrame login = null ;
	private MainFrame maiFrame = null ;
	private static GetWinObject object = new GetWinObject() ;
	/**
	 * 
	 * @Title: newIstants 
	 * @Description: TODO(获取自身实例化对象) 
	 * @return
	 * GetWinObject
	 *
	 */
	public static GetWinObject newIstants(){
		return object ; 
	}
	/**
	 * 
	 * @Title: setConnFram 
	 * @Description: TODO(设置getConnFram对象) 
	 * @param connwin
	 * void
	 *
	 */
	public void setConnFram(ConnFram connwin){
		conn = connwin ;
	}
	/**
	 * 
	 * @Title: getConnFram 
	 * @Description: TODO(获取getConnFram对象) 
	 * @return
	 * ConnFram
	 *
	 */
	public ConnFram getConnFram(){
		return conn;
	}
	/**
	 * 
	 * @Title: setRegisterFrame 
	 * @Description: TODO(设置RegisterFrame对象) 
	 * @param r
	 * void
	 *
	 */
	public void setRegisterFrame(RegisterFrame r){
		register = r ;
	}
	/**
	 * 
	 * @Title: getRegisterFrame 
	 * @Description: TODO(获取RegisterFrame对象) 
	 * @return
	 * RegisterFrame
	 *
	 */
	public RegisterFrame getRegisterFrame(){
		return register ;
	}
	/**
	 * 
	 * @Title: setRegisterFrame 
	 * @Description: TODO(设置RegisterFrame对象) 
	 * @param r
	 * void
	 *
	 */
	public void setLoginFrame(LoginFrame login){
		this.login = login ;
	}
	/**
	 * 
	 * @Title: getRegisterFrame 
	 * @Description: TODO(获取RegisterFrame对象) 
	 * @return
	 * RegisterFrame
	 *
	 */
	public LoginFrame getLoginFrame(){
		return login ;
	}
	/**
	 * 
	 * @Title: setRegisterFrame 
	 * @Description: TODO(设置RegisterFrame对象) 
	 * @param r
	 * void
	 *
	 */
	public void setMainFrame(MainFrame maiFrame){
		this.maiFrame = maiFrame ;
	}
	/**
	 * 
	 * @Title: getRegisterFrame 
	 * @Description: TODO(获取RegisterFrame对象) 
	 * @return
	 * RegisterFrame
	 *
	 */
	public MainFrame getMainFrame(){
		return maiFrame ;
	}
}
