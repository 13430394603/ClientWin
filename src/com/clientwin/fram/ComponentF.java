package com.clientwin.fram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clientwin.core.ArrayJson;
import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.SSData;
import com.clientwin.core.TestLine;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.request.AlowFreRequest;
import com.clientwin.request.CloseRequest;
import com.clientwin.request.DownLineRequest;
import com.clientwin.request.PersonRequest;
import com.clientwin.request.SerchRequest;
/**
 * 
 * @ClassName: Component 
 * @Description: TODO(组件存放处) 
 * @author 威 
 * @date 2017年6月2日 下午4:59:16 
 *
 */
public class ComponentF {
	/*static String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
	static String spath = System.getProperty("user.dir") + "/img/" ;
	private static int createSysitem_count = 0 ;
	
	/**
	 * 
	 * @Title: showSysMfreItem 
	 * @Description: TODO(系统信息 -- 好友请求信息展示项的组件) 
	 * @param usercode
	 * @param Aname
	 * @param path
	 * @return
	 * JPanel 返回组件JPanel
	 *
	 */
	public static JPanel showMfreRequestItem(String usercode,String Aname, String Date){
		ArrayJson sendjson = new ArrayJson() ;
		CrateSendMessage msgMudle = new CrateSendMessage();
		
		//展示项面板
		JPanel show = new JPanel() ;
		show.setLayout(null) ;
		show.setSize(549, 85) ;
		show.setLocation(0, createSysitem_count*85) ;
		
		JLabel time = new JLabel(Date) ;
		time.setSize(74, 12) ;
		time.setLocation(243, 5) ;
		time.setFont(new Font("微软雅黑",Font.PLAIN,12)) ;
		time.setForeground(Color.GRAY) ;
		
		//背景
		JLabel bg = new JLabel() ;
		bg.setSize(549, 85) ;
		bg.setLocation(0, 0) ;
		bg.setIcon(new ImageIcon(spath+"mfre.png")) ;			
		
		//描述展示内容
		JLabel desc = new JLabel("<html><body>"+Aname+"("+usercode+")"+"&nbsp;&nbsp;请求添加您为好友。</body></html>") ;
		desc.setSize(253, 30) ;
		desc.setLocation(130, 50) ;
		desc.setForeground(Color.WHITE) ;
		desc.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		
		//点击后显示的结果
		//点击后的结果
		JLabel result = new JLabel() ;
		result.setSize(80, 24) ;
		result.setLocation(405, 54) ;
		result.setFont(new Font("微软雅黑", Font.PLAIN, 14)) ;
		result.setForeground(Color.white) ;
		//是否同意
		JButton agree_click = new JButton("同意") ;
		JButton disagree_click = new JButton("拒绝") ;
		disagree_click.setBorder(BorderFactory.createEmptyBorder()) ;
		agree_click.setBorder(BorderFactory.createEmptyBorder()) ;
		agree_click.setSize(40, 25) ;
		agree_click.setLocation(380, 46) ;
		disagree_click.setSize(40, 25) ;
		disagree_click.setLocation(425, 46) ;
		agree_click.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("ok") ;
				sendjson.put("state" ,"true") ;
				sendjson.put("active_side", usercode);
				msgMudle.setContent(sendjson.getMessage()) ;
				agree_click.setVisible(false) ;
				disagree_click.setVisible(false) ;
				result.setVisible(true) ;
				result.setText("同意") ;
				AlowFreRequest.newInstants().doRequest(msgMudle) ;
			}
		}) ;

		//不同意
		//disagree_click.setSize() ;
		//disagree_click.setLocation() ;
		disagree_click.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("no") ;
				sendjson.put("state" ,"false") ;
				sendjson.put("active_side", usercode) ;
				msgMudle.setContent(sendjson.getMessage()) ;
				agree_click.setVisible(false) ;
				disagree_click.setVisible(false) ;
				result.setVisible(true) ;
				result.setText("拒绝") ;
				AlowFreRequest.newInstants().doRequest(msgMudle) ;
			}
		}) ;

		show.add(agree_click) ;
		show.add(disagree_click) ;
		show.add(desc) ;
		show.add(result) ;
		show.add(time) ;
		show.add(bg) ;
		
		time.setVisible(true) ;
		bg.setVisible(true) ;
		//show.setVisible(true) ;
		agree_click.setVisible(true) ;
		disagree_click.setVisible(true) ;
		desc.setVisible(true) ;
		result.setVisible(false) ;
		createSysitem_count++ ;
		System.out.println("do") ;
		return show ;
	}
	/**
	 * 
	 * @Title: showSysMfreItem 
	 * @Description: TODO(系统信息 -- 交友返回结果) 
	 * @param usercode
	 * @param Aname
	 * @param path
	 * @return
	 * JPanel 返回组件JPanel
	 *
	 */
	public static JPanel showMfreReplyItem(String usercode,String Aname, String Date, String msg){
		ArrayJson sendjson = new ArrayJson() ;
		CrateSendMessage msgMudle = new CrateSendMessage();
		
		//展示项面板
		JPanel show = new JPanel() ;
		show.setLayout(null) ;
		show.setSize(549, 85) ;
		show.setLocation(0, createSysitem_count*85) ;
		
		JLabel time = new JLabel(Date) ;
		time.setSize(74, 12) ;
		time.setLocation(243, 5) ;
		time.setFont(new Font("微软雅黑",Font.PLAIN,12)) ;
		time.setForeground(Color.GRAY) ;
		
		//背景
		JLabel bg = new JLabel() ;
		bg.setSize(549, 85) ;
		bg.setLocation(0, 0) ;
		bg.setIcon(new ImageIcon(spath+"mfre.png")) ;			
		
		//描述展示内容
		JLabel desc = new JLabel("<html><body>"+Aname+"("+usercode+")"+"&nbsp;&nbsp;"+msg+"。</body></html>") ;
		desc.setSize(300, 30) ;
		desc.setLocation(130, 50) ;
		desc.setForeground(Color.WHITE) ;
		desc.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		
		show.add(desc) ;
		show.add(time) ;
		show.add(bg) ;
		
		time.setVisible(true) ;
		bg.setVisible(true) ;
		desc.setVisible(true) ;
		createSysitem_count++ ;
		System.out.println("do") ;
		return show ;
	}
	/**
	 * 
	 * @Title: down_function 
	 * @Description: TODO(注销) 
	 * void
	 *
	 */
	private static JPanel down_win = null ;
	public static JPanel down_function(){
		JButton down = null ;
		JButton down_back = null ;
		
		down_win = new JPanel() { 
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"down.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 770, 
                500, icon.getImageObserver());  
            }  
        };
		down_win.setSize(770, 500) ;
		down_win.setLocation(0, 0) ;
		down_win.setLayout(null) ;
		
		//返回
		down_back = new JButton() ;
		down_back.setSize(32, 32) ;
		down_back.setLocation(319, 258) ;
		down_back.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		down_back.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		//注销
		down = new JButton() ;
		down.setSize(32, 32) ;
		down.setLocation(423, 256) ;
		down.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		down.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		down_win.add(down_back) ;
		down_win.add(down) ;
		
		
		down_win.setOpaque(false) ;
		down.setOpaque(false) ;
		down_back.setOpaque(false) ;
		
		down.setContentAreaFilled(false) ;
		down_back.setContentAreaFilled(false) ;
		
		down_win.setVisible(false) ;
		down.setVisible(true) ;
		down_back.setVisible(true) ;
		
		down_back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//注销
				down_win.setVisible(false) ;
			}
		}) ;
		down.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				DownLineRequest.newInstants().doRequest(new CrateSendMessage());
			}
		}) ;
		
		return down_win ;
	}
	
	
	
	/**
	 * 
	 * @Title: serch_function 
	 * @Description: TODO(查找好友窗口) 
	 * void
	 *
	 */
	public static JPanel serch_function(){
		JPanel serch_win = new JPanel() {  
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"serchwin.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 770, 
                500, icon.getImageObserver());  
            }  
        };
		serch_win.setSize(770, 500) ;
		serch_win.setLocation(0, 0) ;
		serch_win.setLayout(null) ;
		
		/**
		 * 返回按钮
		 */
		JButton back = new JButton() ;
		back.setSize(32, 32) ;
		back.setLocation(145, 7) ;
		back.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		back.setBorder(BorderFactory.createLineBorder(null, 0));
		
		/**
		 * 查找按钮
		 */
		JButton ser_ = new JButton() ;
		ser_.setSize(32, 32) ;
		ser_.setLocation(474, 79) ;
		ser_.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		ser_.setBorder(BorderFactory.createLineBorder(null, 0));
		
		/**
		 * 输入框
		 */
		JTextField input_ser = new JTextField() ;
		input_ser.setSize(188, 32);
		input_ser.setLocation(278, 79);
		input_ser.setBorder(BorderFactory.createLineBorder(null, 0));
		input_ser.setForeground(Color.GRAY) ;
		input_ser.setText("您可以输入账号、在线、所有关键字查找") ;
		
		/**
		 * 查找结果容器
		 */
		
		/**
		 * 添加进组件
		 */
		
		serch_win.add(back) ;
		serch_win.add(ser_) ;
		serch_win.add(input_ser) ;
		
		/**
		 * 设置组件透明
		 */
		serch_win.setOpaque(false) ;
		back.setOpaque(false) ;
		ser_.setOpaque(false) ;
		input_ser.setOpaque(false) ;
		
		/**
		 * 设置按钮组件透明
		 */
		back.setContentAreaFilled(false);
		ser_.setContentAreaFilled(false);
		
		/**
		 * 设置可见度
		 */
		serch_win.setVisible(false) ;
		back.setVisible(true) ;
		ser_.setVisible(true) ;
		input_ser.setVisible(true) ;
		
		/**
		 * 注册事件
		 */
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				createSerchFreItem_count = 0 ;
				serch_win.setVisible(false) ;
			}
		}) ;
		ser_.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("add");
				ArrayJson json = new ArrayJson() ;
				json.put("serch_key", input_ser.getText()) ;
				CrateSendMessage msgMudle = new CrateSendMessage() ;
				msgMudle.setContent(json.getMessage()) ;
				SerchRequest.newInstants().doRequest(msgMudle) ;
				System.out.println("add_end") ;
			}
		}) ;
		/**
		 * 刷新界面
		 */
		return serch_win ;
	}
	/**
	 * 查找结果容器
	 */
	public static JPanel getResultcnt(){
		JPanel result = new JPanel() ;
		result.setSize(316, 300) ;
		result.setLocation(224, 156) ;
		result.setLayout(null) ;
		result.setOpaque(false) ;
		
		return result ;
	}
	//显示时间组件
	public JPanel showTime(){
		JPanel showcnt = new JPanel() ;
		showcnt.setSize(549, 27) ;
		showcnt.setLocation(0, 0) ;
		
		JLabel time = new JLabel(TimeUtil.getDatetime()) ;
		time.setSize(74, 12) ;
		time.setLocation(243, 5) ;
		time.setFont(new Font("微软雅黑",Font.PLAIN,12)) ;
		time.setForeground(Color.GRAY) ;
		
		JLabel bg = new JLabel() ;
		bg.setSize(549, 27) ;
		bg.setLocation(0, 0) ;
		bg.setIcon(new ImageIcon(spath+"showtime.png"));
		
		showcnt.add(time) ;
		showcnt.add(bg) ;
		
		time.setVisible(true) ;
		bg.setVisible(true) ;
		
		return showcnt ;
	}
	/**
	 * 查找结果 计数
	 */
	private static int createSerchFreItem_count = 0 ;
	
	/**
	 * 
	 * @Title: createSerchFreItem 
	 * @Description: TODO(创建好友查找结果项) 
	 * void
	 *
	 */
	public static JPanel SerchFreItem(String usercode, String Aname){
		/**
		 * 单个项容器
		 */
		JPanel ser_result_item = new JPanel() ;
		ser_result_item.setSize(316, 40) ;
		ser_result_item.setLocation(0, createSerchFreItem_count*41) ;
		ser_result_item.setLayout(null) ;
		
		/**
		 * 昵称显示
		 */
		JLabel Aname_str = new JLabel("<html><body>"+Aname+"</body></html>") ;
		Aname_str.setSize(120, 30);
		Aname_str.setLocation(8, 5);
		Aname_str.setFont(new Font("微软雅黑" ,Font.PLAIN, 14));
		Aname_str.setForeground(Color.GRAY) ;
		
		/**
		 * 用户编号显示
		 */
		JLabel user_code = new JLabel("<html><body>"+usercode+"</body></html>") ;
		user_code.setSize(120, 30) ;
		user_code.setLocation(130, 5) ;
		user_code.setFont(new Font("微软雅黑" ,Font.PLAIN, 14));
		user_code.setForeground(Color.GRAY) ;
		
		/**
		 * 添加好友按钮
		 */
		JLabel add_fre_click = new JLabel("添加") ;
		add_fre_click.setSize(70, 30) ;
		add_fre_click.setLocation(264, 5) ;
		add_fre_click.setFont(new Font("微软雅黑" ,Font.PLAIN, 14));
		add_fre_click.setForeground(Color.WHITE) ;
		
		JLabel bg = new JLabel() ;
		bg.setSize(316, 40) ;
		bg.setLocation(0, 0) ;
		bg.setIcon(new ImageIcon(spath+"serresult.png")) ;
		
		/**
		 * 添加组件
		 */
		ser_result_item.add(user_code) ;
		ser_result_item.add(Aname_str) ;
		ser_result_item.add(add_fre_click) ;
		ser_result_item.add(bg) ;
		
		/**
		 * 设置可视
		 */
		ser_result_item.setVisible(true) ;
		user_code.setVisible(true) ;
		Aname_str.setVisible(true) ;
		add_fre_click.setVisible(true) ;
		bg.setVisible(true) ;
		
		add_fre_click.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("addfre");
				//添加
				CrateSendMessage msgMudle = new CrateSendMessage() ;
				ArrayJson json = new ArrayJson() ;
				json.put("user", CountInfo.newInstants().getUserCode()) ;
				json.put("Aname", CountInfo.newInstants().getAname()) ;
				msgMudle.setTo(usercode) ;
				msgMudle.setType("0005") ;
				msgMudle.setFrom(CountInfo.newInstants().getUserCode()) ;
				msgMudle.setDate(TimeUtil.getDatetime()) ;
				msgMudle.setContent(json.getMessage()) ;
				MessageFactory.newInstants().messageSend(msgMudle) ;
				GetWinObject.newIstants().getMainFrame().createAlertWin("已发送添加信息") ;
			}
		}) ;
		
		createSerchFreItem_count ++ ;
		return ser_result_item ;
	}
	/**
	 * 
	 * @Title: upDateinfo_function 
	 * @Description: TODO(修改个人信息) 
	 * void
	 *
	 */
	public static JPanel upDateinfo_function(){
		JPanel upDateinfo_win = new JPanel() { 
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"info.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 770, 
                500, icon.getImageObserver());  
            }  
        };
		upDateinfo_win.setSize(770, 500) ;
		upDateinfo_win.setLocation(0, 0) ;
		upDateinfo_win.setLayout(null) ;
		
		//返回
		JButton upback = new JButton() ;
		upback.setSize(32, 32) ;
		upback.setLocation(145, 7) ;
		upback.setCursor(new Cursor(Cursor.HAND_CURSOR));
		upback.setBorder(BorderFactory.createLineBorder(null, 0));

		//保存
		JButton store_click = new JButton() ;
		store_click.setSize(32, 32) ;
		store_click.setLocation(590, 7) ;
		store_click.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		store_click.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		JLabel title = new JLabel("<html><body>个人信息修改</body></html>") ;
		JLabel Aname = new JLabel("<html><body>昵&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;:</body></html>") ;
		JLabel email_ = new JLabel("<html><body>email&nbsp;&nbsp;:</body></html>") ;
		JLabel username_ = new JLabel("<html><body>用户名&nbsp;:</body></html>") ;
		title.setSize(173, 40) ;
		title.setLocation(307, 88) ;
		Aname.setSize(95, 25) ;
		Aname.setLocation(220, 210) ;
		email_.setSize(95, 25) ;
		email_.setLocation(220, 269) ;
		username_.setSize(95, 25) ;
		username_.setLocation(220, 332) ;
		
		title.setFont(new Font("微软雅黑",Font.BOLD,24)) ;
		Aname.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		email_.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		username_.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		title.setForeground(Color.WHITE) ;
		Aname.setForeground(Color.GRAY) ;
		email_.setForeground(Color.GRAY) ;
		username_.setForeground(Color.GRAY) ;
		//信息框1
		JTextField info1 = new JTextField() ;
		info1.setSize(203, 32) ;
		info1.setLocation(329, 207) ;
		info1.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		//信息框2
		JTextField info2 = new JTextField() ;
		info2.setSize(203, 32) ;
		info2.setLocation(329, 266) ;
		info2.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		//信息框3
		JTextField info3 = new JTextField() ;
		info3.setSize(203, 32) ;
		info3.setLocation(329, 329) ;
		info3.setBorder(BorderFactory.createLineBorder(null, 0)) ;
		
		upDateinfo_win.add(upback) ;
		upDateinfo_win.add(store_click) ;
		upDateinfo_win.add(info1) ;
		upDateinfo_win.add(info2) ;
		upDateinfo_win.add(info3) ;
		upDateinfo_win.add(title) ;
		upDateinfo_win.add(Aname) ;
		upDateinfo_win.add(email_) ;
		upDateinfo_win.add(username_) ;
		
		upDateinfo_win.setOpaque(false) ;
		upback.setOpaque(false) ;
		store_click.setOpaque(false) ;
		info1.setOpaque(false) ;
		info2.setOpaque(false) ;
		info3.setOpaque(false) ;
		
		upDateinfo_win.setVisible(false) ;
		upback.setVisible(true) ;
		store_click.setVisible(true) ;
		info1.setVisible(true) ;
		info2.setVisible(true) ;
		info3.setVisible(true) ;
		Aname.setVisible(true) ; 
		email_.setVisible(true) ;
		username_.setVisible(true) ;
		
		upback.setContentAreaFilled(false) ;
		store_click.setContentAreaFilled(false) ;
		
		upback.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				upDateinfo_win.setVisible(false) ;
			}
		}) ;
		store_click.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				CrateSendMessage msgMudle = new CrateSendMessage() ;
				ArrayJson josn = new ArrayJson() ;
				josn.put("Aname", info1.getText()) ;
				josn.put("email", info2.getText()) ;
				msgMudle.setContent("{content:"+josn.getMessage()+"}");
				PersonRequest.newInstants().doRequest(msgMudle) ;
			}
		}) ;
		return upDateinfo_win ;
	}
	
	/**
	 * 
	 * @Title: close_function 
	 * @Description: TODO(关闭功能) 
	 * void
	 *
	 */
	public static JPanel close_function(){
		JPanel close_win = new JPanel(){ 
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"closell.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 770, 
                500, icon.getImageObserver());  
            }  
        };
		close_win.setSize(770, 500) ;
		close_win.setLocation(0, 0) ;
		close_win.setLayout(null) ;
		
		//返回
		JButton close_back = new JButton() ;
		close_back.setSize(32, 32) ;
		close_back.setLocation(319, 258) ;
		close_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close_back.setBorder(BorderFactory.createLineBorder(null, 0));
		
		//注销
		JButton close = new JButton() ;
		close.setSize(32, 32) ;
		close.setLocation(423, 256) ;
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.setBorder(BorderFactory.createLineBorder(null, 0));		
		
		
		close_win.add(close_back) ;
		close_win.add(close) ;
		
		close_win.setOpaque(false) ;
		close_back.setOpaque(false);
		close.setOpaque(false);
		
		close_win.setVisible(false) ;
		close_back.setVisible(true);
		close.setVisible(true);
		
		close_back.setContentAreaFilled(false) ;
		close.setContentAreaFilled(false) ;
		
		close_back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				close_win.setVisible(false) ;
			}
		}) ;
		close.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				CrateSendMessage Message = new CrateSendMessage() ;
				Message.setFrom(CountInfo.newInstants().getUserCode()) ;
				CloseRequest.newInstants().doRequest(Message) ;
				System.exit(0) ;
			}
		}) ;
		
		return close_win ;
	}
	/**
	 * 错误提示窗
	 * 外界可以调用
	 */
	public static JPanel err_alert_win = null ;
	public static JPanel alert_JPanel(String err_str){
		TestLine testLine = TestLine.newInstants() ;
		String err_msg = testLine.getMessage(err_str) ; 
		/**
		 * 提示窗
		 */
		err_alert_win = new JPanel();
		err_alert_win.setSize(770, 500) ;
		err_alert_win.setLocation(0, 0) ;
		err_alert_win.setLayout(null) ;
		JLabel bg = new JLabel() ;
		err_alert_win.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				err_alert_win.setVisible(false) ;
			}
		});
		bg.setSize(770, 500) ;
		ImageIcon ico = new ImageIcon(spath+"4.png") ;
		bg.setIcon(ico) ;
		
		/**
		 * 提示框
		 */
		JPanel err_alert = new ChatJPanelel() ;
		err_alert.setLayout(null) ;
		
		/**
		 * 提示内容
		 */
		JLabel err_message = new JLabel(err_msg) ;
		err_message.setSize(testLine.getWidth2(), testLine.getHeight2()) ;
		err_message.setLocation(6, 3) ;
		err_alert.setSize(err_message.getWidth()+8, err_message.getHeight()+16) ;
		err_alert.setLocation((770-(err_message.getWidth()+8))/2, (550-(err_message.getHeight()+16))/2) ;
		err_message.setForeground(new Color(255, 255, 255)) ;
		/**
		 * 设置字体
		 */
		err_message.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		
		/**
		 * 添加
		 */
		err_alert_win.add(err_alert) ;
		err_alert_win.add(bg) ;
		err_alert.add(err_message) ;
		err_alert_win.setOpaque(false) ;
		
		/**
		 * 设置可视
		 */
		err_alert.setVisible(true) ;
		err_message.setVisible(true) ;
		bg.setVisible(true) ;
		
		return err_alert_win ;
	}
}
