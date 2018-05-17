package com.clientwin.fram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clientwin.core.CountInfo;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TestLine;
import com.clientwin.core.TimeUtil;
import com.clientwin.main.MessageFactory;
import com.clientwin.pool.ChatJPanelPool;

public class MainFrame  extends JFrame implements MouseListener,MouseWheelListener,FocusListener,KeyListener{
	/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
	String spath = System.getProperty("user.dir") + "/img/" ;
	private JLayeredPane j = null ;
	/**
	 * 好友项存储池<br>
	 * 对应的用户名存储好友项
	 */
	private ChatJPanelPool pool = ChatJPanelPool.newInstants() ;
	/**
	 * 聊天容器存储池<br>
	 * 对应的用户名存储聊天容器
	 */
	private ChatJPanelPool pool1 = new ChatJPanelPool() ;
	/**
	 * 聊天界面存储池<br>
	 * 对应用户名存储
	 */
	public static ChatJPanelPool chatWinPool = new ChatJPanelPool() ;
	/**
	 * 临时存储整个聊天界面容器<br>
	 * key固定为temp 当好友项点击时读取当前并设置层度
	 */
	private Map<String, JPanel> temppool = new HashMap<String, JPanel>() ; 
	/**
	 * 
	 */
	private JPanel tempjpanel = null ;
	
	/**
	 * 聊天内部容器全局变量，装载每条聊天信息
	 */
	private JPanel alljpanel = null ;
	/**
	 * 全局用户名 -- 标记<br>
	 * 当点击好友项时设置对应的用户名
	 */
	public String new_code = "" ;
	
	/**
	 * 存储每一个界面的最大滚动值<br>
	 * 创建界面时存进，默认值0<br>
	 * 当界面足够大时改变值
	 */
	private Map<String, Integer> map_max_wheel = new HashMap<String, Integer>() ;
	
	/**
	 * 存储每一个界面的聊天容器高度<br>
	 * 创建聊天界面时创建存储 默认值是0
	 */
	private Map<String, Integer> add_sum_height = new HashMap<String, Integer>() ;
	
	/**
	 * 存储每一个界面的聊天容器的滚动位置<br>
	 * 创建聊天界面时创建存储 默认值是0
	 */
	private Map<String, Integer> wheel_position = new HashMap<String, Integer>() ;
	
	/**
	 * 存储每个界面的滚动判断
	 * 当界面创建时初始化值
	 */
	private Map<String, Boolean> wheel_flag = new HashMap<String, Boolean>() ;
	
	/**
	 * 春存储每个好友项的背景JLabel
	 * 当好友模板界面创建时
	 */
	private Map<String, JLabel> map_freitem_bg = new HashMap<String, JLabel>() ;
	/**
	 * 上一个背景的保存
	 */
	private JLabel temp_bg = null ;
	/**
	 * 好友项被选中的背景状态路径
	 */
	private Map<String, String> ch_path_state = new HashMap<String, String>() ;
	/**
	 * 好友项未被选中的背景状态路径
	 */
	private Map<String, String> bu_path_state = new HashMap<String, String>() ;
	
	/**
	 * 发送按钮
	 */
	private Map<String, JButton> send_click = new HashMap<String, JButton>() ;  
	
	/**
	 * 输入框
	 */
	private Map<String, JTextField> input_text = new HashMap<String, JTextField>() ;  
	private String last_usrecode = "" ; 
	//基本图片路径
	private String basi_Path = "" ;
	
	private Map<String, JLabel> bg_store= new HashMap<String, JLabel>() ;
	
	public MainFrame(){
		int w_ = Toolkit.getDefaultToolkit().getScreenSize().width;
	    int h_ = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(770, 500);
		this.setLocation((w_-770)/2, (h_-500)/2);
		this.setUndecorated(true);
		setBacis() ;
		m() ;
		this.setDragable(this) ;
		createSerch_function() ;
		createUpDateinfo_function() ;
		createdown_function() ;
		createClose_function() ;
		//将自身传递到代理对象GetWinObject
		GetWinObject.newIstants().setMainFrame(this) ;
		Map<String, String> maps = new HashMap<String, String>() ;
		maps.put("ch_path", spath+"ch系统项.png") ;
		maps.put("bu_path", spath+"bu系统项.png") ;
		//在好友栏创建系统信息项
		SysItem("SYSTEM_FRAME", "系统消息", maps) ;
		Map<String, String> m = new HashMap<String, String>() ;
		//初始化时创建系统界面
		createSysFrame("SYSTEM_FRAME", "系统消息") ;
		//机器人项
		m.put("ch_path", spath+"robot.png") ;
		m.put("bu_path", spath+"burobot.png") ;
		freItem("#robot#", "小威", m) ;
	}
	/**
	 * 
	 * @Title: setBacis 
	 * @Description: TODO(基本信息) 
	 * void
	 *
	 */
	public void setBacis(){
		this.setLayout(null);
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//设置聊天信息在最低端-便于查看--发送时调用，进入页面调用
	/**
	 * 
	 * @Title: setChatTop 
	 * @Description: TODO(设置聊天信息拉到最底部) 
	 * void
	 *
	 */
	public void setChatTop(String userCode){
		JPanel tempjp = (JPanel) pool1.get(userCode) ; 
		//容器已存在那么就可以判断
		if(tempjp != null ){
			if(tempjp.getHeight() > 369){
				tempjp.setLocation(0, -(tempjp.getHeight()-369)) ;
				tempjp.repaint() ;
				tempjp.validate() ;
				//每每发一条信息容器会自动移到最低
				//设置此时的位置
				wheel_position.put(userCode, tempjp.getY()) ;
			}
		}
	}
	/**
	 * 
	 * @Title: m 
	 * @Description: TODO(主要界面) 
	 * void
	 *
	 */
	public void m(){
		j = new JLayeredPane() { 
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"mainframe.png");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 770, 
                500, icon.getImageObserver());  
            }  
        };
		j.setSize(770, 500) ;
		j.setLayout(null) ;
		
		j.setVisible(true) ;
		
		this.add(j) ;
		lFram() ;
		
	}
	private JPanel frecntt = null ; 
	private JButton jb = null ;
	private JPanel ljpanel = null ;
	/**
	 * 
	 * @Title: lFram 
	 * @Description: TODO(好友列表部分) 
	 * void
	 *
	 */
	public void lFram(){
		JTextField serch = null ;
		JPanel frecnt = null ;
		JButton test = null ;
		
		ljpanel = new JPanel() ;
		ljpanel.setSize(220, 500) ;
		ljpanel.setLayout(null) ;
		
		//添加搜索框
		serch = new JTextField() ;
		serch.setSize(147,30) ;
		serch.setBorder(BorderFactory.createLineBorder(null, 0));
		serch.setLocation(23, 9) ;
		
		//添加好友列表容器
		frecnt = new JPanel() ;
		frecnt.setSize(220, 417) ;
		frecnt.setLocation(0, 44) ;
		frecnt.setLayout(null) ;
		frecnt.setBackground(Color.BLUE);
		
		//好友列表内部容器
		frecntt = new JPanel() ;
		frecntt.setSize(220, 417) ;
		frecntt.setLayout(null);
		frecntt.setLocation(0, 0) ;
		
		jb = new JButton() ;
		jb.setCursor(new Cursor(HAND_CURSOR)) ;
		jb.setBounds(95, 468, 30, 26) ;
		jb.setBorder(BorderFactory.createLineBorder(null, 0));
		
		//查找按钮
		test = new JButton() ;
		test.setBounds(173,9,29,29) ;
		test.setCursor(new Cursor(HAND_CURSOR)) ;
		test.setBorder(BorderFactory.createLineBorder(null, 0));
		
		ljpanel.setOpaque(false) ;
		frecnt.setOpaque(false) ;
		frecntt.setOpaque(false) ;
		serch.setOpaque(false) ;
		test.setOpaque(false) ;
		test.setContentAreaFilled(false) ;
		test.setFocusable(false) ;
		jb.setFocusable(false) ;
		jb.setOpaque(false) ;
		jb.setContentAreaFilled(false) ;
		
		j.add(ljpanel, JLayeredPane.DEFAULT_LAYER);
		ljpanel.add(frecnt) ;
		ljpanel.add(jb) ;
		ljpanel.add(serch) ;
		ljpanel.add(test) ;
		frecnt.add(frecntt) ;
		
		serch.setVisible(true) ;
		ljpanel.setVisible(true) ;
		frecnt.setVisible(true) ;
		jb.setVisible(true) ;
		test.setVisible(true) ;
		frecntt.setVisible(true) ;
		
		ljpanel.repaint() ;
		frecnt.repaint() ;
		
		serch.addMouseListener(this) ;
		frecntt.addMouseListener(this) ;
		frecntt.addMouseWheelListener(this) ;
		jb.addMouseListener(this) ; 
		jb.addFocusListener(this) ;
		
		Menuitem() ;
	}
	//实现拖拽
	// 为窗口加上监听器，使得窗口可以被拖动  
	private boolean isMoved = false ;
	private Point pre_point = null ;
	private Point end_point = null ;
	/**
	 * 
	 * @Title: setDragable 
	 * @Description: TODO(处理窗口拖动) 
	 * @param frame
	 * void
	 *
	 */
	private void setDragable(final MainFrame frame) {
    	this.addMouseListener(new MouseAdapter() {  
            public void mouseReleased(MouseEvent e) {  
                isMoved = false;// 鼠标释放了以后，是不能再拖拽的了  
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
  
            public void mousePressed(MouseEvent e) {  
                isMoved = true;  
                pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置  
                frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            } 
        });  
        //拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。  
    	this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {  
            public void mouseDragged(java.awt.event.MouseEvent e) {  
                if (isMoved) {// 判断是否可以拖拽  
                    end_point = new Point(frame.getLocation().x + e.getX() - pre_point.x,  
                    		frame.getLocation().y + e.getY() - pre_point.y);  
                    frame.setLocation(end_point);  
                }  
            }  
        });  
    	//滚动事件
    }  	
	
	
	
	
	

	//聊天窗口，模板
	private JPanel chatpanel = null ;
	private JButton send = null ;
	private int chatCntMudle_count = 0 ;
	/**
	 * 输入发送信息框
	 */
	private JTextField chatinfo = null ;
	//给一个优先级
	/**
	 * 
	 * @Title: createChatMudle 
	 * @Description: TODO(创建聊天界面) 
	 * void
	 *
	 */
	
	public void createChatMudle(String userCode, String name){
		System.out.println(userCode+"创建聊天界面");
		//设置JPanel的背景
		JPanel chatwin = new JPanel() { 
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"chatwin.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 549, 
                500, icon.getImageObserver()); 
            }  
        }; 
		chatwin.setSize(549, 500);
		chatwin.setLayout(null);
		chatwin.setLocation(221, 0) ;
		
		//昵称--在现状态
		JLabel Aname = new JLabel("<html><body>"+name+"</body></html>", JLabel.CENTER)  ;
		Aname.setSize(140, 30) ;
		Aname.setLocation((549-140)/2, 37) ;
		Aname.setForeground(Color.white) ;
		Aname.setFont(new Font("微软雅黑",Font.PLAIN,14));
		
		//显示聊天信息容器
		JPanel chatcnt = new JPanel() ;
		chatcnt.setLayout(null);
		chatcnt.setSize(549, 369);
		chatcnt.setLocation(0, 64);
		
		//聊天信息内部自增容器
		chatpanel = new JPanel() ;
		//值随聊天信息的变动而变动
		chatpanel.setSize(549, 369) ;
		chatpanel.setLocation(0, 0) ;
		chatpanel.setLayout(null) ;
		
		//输入发送信息
		chatinfo = new JTextField() ;
		chatinfo.setSize(428, 30);
		chatinfo.setBorder(BorderFactory.createLineBorder(null, 0));
		chatinfo.setLocation(23, 453);
		chatinfo.setFont(new Font("微软雅黑",Font.PLAIN,13));
		
		input_text.put(userCode, chatinfo) ;
		
		chatpanel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				flag_chat = true ;
			}
			public void mouseExited(MouseEvent e) {
				flag_chat = false ;
			}
		});
		
		//发送按钮
		send = new JButton() ;
		send.setSize(32, 32) ; 
		send.setCursor(new Cursor(HAND_CURSOR)) ;
		send.setBorder(BorderFactory.createLineBorder(null, 0));
		send.setLocation(453, 451) ;
		send_click.put(new_code, send) ;
		send_click.get(new_code).addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				chatMudle(false, userCode, input_text.get(new_code).getText()) ;
	    		//信息发完之后自动计算所发的信息所占的高度，累加构成界面高度
				//创建发送信息
				CrateSendMessage msg = new CrateSendMessage() ;
				msg.setFrom(CountInfo.newInstants().getUserCode()) ;
				msg.setTo(new_code) ;
				msg.setType("0008") ;
				msg.setContent(input_text.get(new_code).getText()) ;
				msg.setDate(TimeUtil.getDatetime()) ;
				input_text.get(new_code).setText("") ;
				MessageFactory.newInstants().messageSend(msg) ;
				chatwin.repaint() ; 
			}
		});
		
		//更多按钮
		JButton more = new JButton() ;
		more.setSize(32, 32);
		more.setCursor(new Cursor(HAND_CURSOR));
		more.setBorder(BorderFactory.createLineBorder(null, 0));
		more.setLocation(504, 451);
		
		//添加
		j.add(chatwin, JLayeredPane.DEFAULT_LAYER) ;
		chatwin.add(Aname) ;
		chatwin.add(chatinfo) ;
		chatwin.add(more) ;
		chatwin.add(send) ;
		chatwin.add(chatcnt) ;
		chatcnt.add(chatpanel) ;
		
		//显示
		chatpanel.setOpaque(false) ;
		chatcnt.setOpaque(false) ;
		chatinfo.setOpaque(false) ;
		send.setOpaque(false) ;
		send.setContentAreaFilled(false);
		send.setFocusable(false);
		more.setOpaque(false) ;
		more.setContentAreaFilled(false); 
		more.setFocusable(false);
		
		
		//设置可见
		chatwin.setVisible(true) ;
		Aname.setVisible(true) ;
		chatinfo.setVisible(true) ;
		more.setVisible(true) ;
		send.setVisible(true) ;
		chatcnt.setVisible(true) ;
		chatpanel.setVisible(true) ;
		
		chatpanel.addMouseWheelListener(this) ;
		chatpanel.addMouseListener(this) ;
		send.addMouseListener(this);
		
		temppool.put("temp", chatwin) ;
		pool1.put(userCode, chatpanel) ;
		chatWinPool.put(userCode, chatwin) ;
		//创建滚动最大值、高、滚动位置的初始值
		map_max_wheel.put(userCode, 0) ;
		add_sum_height.put(userCode, 0) ;
		wheel_position.put(userCode, 0) ;
	}
	
	//每条聊天信息的模板
	private int count = 0 ;
	private int addsun = 0 ;
	/**
	 * 每条信息的高度
	 */
	private int Chat_height = 0 ;
	/**
	 * 
	 * @Title: chatMudle 
	 * @Description: TODO(每条聊天信息的模板) 
	 * void
	 *
	 */
	public void chatMudle(boolean flag_chat_send, String userCode, String message){
		//处理信息
		TestLine testLine = TestLine.newInstants() ;
		//获取处理过后的信息
		String msg = testLine.getMessage(message) ; 
		//获取行数
		testLine.getLine() ;
		
		//返回聊天内容框高
		//返回chat的高
		
		/**
		 * 设置每条信息的高度 -- 需要的信息
		 */
		Chat_height = testLine.getHeight1() ;
		
		//
		JPanel chat = new JPanel() ;
		chat.setSize(549, testLine.getHeight1()) ;
		chat.setLayout(null);
		chat.setLocation(0, add_sum_height.get(userCode)) ;
		
		//设置头像
		JLabel Head_portrait = new JLabel() ;
		Head_portrait.setSize(48, 48);
		Head_portrait.setIcon(new ImageIcon(spath+"头像.png"));		
		
		//导向
		JLabel lef = new JLabel() ;
		lef.setSize(16, 16);
		JPanel c_info_win = null ;
		
		//4-70
		//每个字体占位*14
		//单行高25 -- 两行高40 -- 60   --  80    
		//    70        76    94   --  117
		//一行最多容纳二十个字
		//聊天内容框
		JLabel chatcontent = new JLabel(msg,JLabel.CENTER) ;
		//chatcontent.setBorder(BorderFactory.createLineBorder(Color.BLACK)) ;
		chatcontent.setSize(testLine.getWidth2(), testLine.getHeight2());//需测试两行35
		chatcontent.setLocation(6, 4);
		chatcontent.setFont(new Font("宋体",Font.PLAIN,13));
		
		//判断是收发信息
		if(flag_chat_send){
			//接收信息设置
			chatcontent.setForeground(Color.WHITE);
			//头像的位置
			Head_portrait.setLocation(5, 5);
			//左导向的位置
			lef.setLocation(59, 28) ;
			//导向图标
			lef.setIcon(new ImageIcon(spath+"lef.png"));
			//信息框
			c_info_win = new ChatJPanel() ;
			c_info_win.setSize(chatcontent.getWidth()+11, chatcontent.getHeight()+17);
			c_info_win.setLocation(72, 27);
			flag_chat_send = false ;
		}else{
			//发送信息设置
			chatcontent.setForeground(new Color(126, 118, 118));
			//头像的位置
			Head_portrait.setLocation(496, 5);
			//左导向的位置
			lef.setLocation(469, 28) ;
			//导向图标
			lef.setIcon(new ImageIcon(spath+"rig1.png"));
			//信息框
			c_info_win = new RChatJPanel() ;
			c_info_win.setSize(chatcontent.getWidth()+11, chatcontent.getHeight()+17);
			c_info_win.setLocation((549-75-c_info_win.getWidth()), 27);
			flag_chat_send = true ;
		}
		
		c_info_win.setLayout(null) ;
		
		//添加
		JPanel polljpanel = (JPanel) pool1.get(userCode) ;
		polljpanel.add(chat) ;
		chat.add(Head_portrait) ;
		chat.add(lef) ;
		chat.add(c_info_win) ;
		c_info_win.add(chatcontent) ;
		//设置背景透明
		chat.setOpaque(false);
		//设置可见
		chat.setVisible(true) ;
		chatpanel.setVisible(true) ;
		lef.setVisible(true) ;
		c_info_win.setVisible(true) ;
		//判断滚轮的最大值
		//设置列表容器的高
		//369为容器标准高度
		
		//从map中取出容器高度
		int h = add_sum_height.get(userCode) ;
		//设置新的高度
		h += testLine.getHeight1() ;
		
		if(h > 369){
			//设置最大滚动值
			map_max_wheel.put(userCode, (h - 369)) ;
			((JPanel) pool1.get(userCode)).setSize(549, h) ;
		}
		//覆盖旧值
		add_sum_height.put(userCode, h) ;
		count++ ;
		setChatTop(userCode) ;
		chatpanel.repaint() ;
		chatpanel.validate() ;
		((JPanel) chatWinPool.get(userCode)).repaint() ;
		
	}
	/**
	 * 好友列表项
	 */
	private int itemCount = 0 ;
	
	//两个路径可选
	/**
	 * 未选中的两种的状态 --路径
	 */
	private String path1 = "" ;
	
	/**
	 * 选中的两种状态 --路径
	 */
	private String path2 = "" ;
	
	//
	private Map<String, JPanel> map = new HashMap<String, JPanel>() ;
	/**
	 * 累加好友项的个数便于计算Location
	 */
	private int freItem_count = 0 ;
	/**
	 * 好友项背景
	 */
	private static JLabel bg = null ;
	/**
	 * 
	 * @Title: freItem 
	 * @Description: TODO(好友模板) 
	 * @param path_flag 判断是否在线
	 * @param userCode	用户名
	 * @param Aname	 昵称
	 * void
	 *
	 */
	
	/**
	 * 
	 * @Title: freItem 
	 * @Description: TODO(好友模板) 
	 * @param path_flag 判断是否在线
	 * @param userCode	用户名
	 * @param Aname	 昵称
	 * void
	 *
	 */
	public void freItem(String userCode, String Aname, Map<String, String> map){ 
        JPanel item = new JPanel() ;
        item.addMouseListener(new MouseAdapter() {   
	        public void mouseClicked(MouseEvent e) {
	        	new_code = pool.getUserName((JPanel) e.getSource()) ;
	        	JPanel tjp = temppool.get("temp") ;
	        	//临时值不等于空而且不等于当前聊天容器方可设置
	        	if(tjp != null && tjp != chatWinPool.get(new_code)){
	        		j. setPosition(tjp, JLayeredPane.PALETTE_LAYER) ;
	        	}
	        	//(JPanel) e.getSource())
	        	//moveToBack(Component c) 
	        	//通过查找池获取当前聊天的用户名
	        	/**
	        	 * 点击设置背景 -- 点击前设置上一次点击的组件为初始状态
	        	 */
	        	if(temp_bg!=null){
	        		temp_bg.setIcon(new ImageIcon(bu_path_state.get(last_usrecode))) ;
	        	}
	        	map_freitem_bg.get(new_code).setIcon(new ImageIcon(ch_path_state.get(new_code))) ;
	        	temp_bg = map_freitem_bg.get(new_code) ;
	        	last_usrecode = new_code ;
	        	//创建聊天界面
	        	
	        	if(!chatWinPool.isExist((JPanel) chatWinPool.get(new_code))){
	        		createChatMudle(pool.getUserName((JPanel) e.getSource()), Aname) ;
	        		System.out.println(new_code) ;
	        	}
	        	System.out.println(new_code) ;
	        	j.setPosition((JPanel) chatWinPool.get(new_code), JLayeredPane.DEFAULT_LAYER) ;
	        	j.repaint() ; 
	        	j.validate() ;
	        	//j.validate() ;
	        }
	    });
        
		item.setSize(220, 57) ;
		item.setLocation(0, itemCount*58) ;
		item.setBackground(Color.GRAY) ;
		item.setLayout(null) ;
		item.setCursor(new Cursor(HAND_CURSOR)) ;
		
		//昵称 
		JLabel An = new JLabel("<html><body>"+Aname+"</body></html>") ;
		An.setSize(120, 25) ;
		An.setLocation(68, 17) ;
		An.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		An.setForeground(Color.GRAY) ;
		
		bg = new JLabel() ;
		bg.setSize(220, 57);
		bg.setLocation(0, 0);
		bg.setIcon(new ImageIcon(map.get("bu_path"))) ;
		
		frecntt.add(item) ;
		item.add(An) ;
		item.add(bg) ;
		
		item.setVisible(true) ;
		An.setVisible(true) ;
		bg.setVisible(true) ;
		
		if(itemCount*58+57 > 417){
			max_wheel_fre = (itemCount*58+57) - 417 ; 
			frecntt.setSize(221, itemCount*58+57) ;
		}
		itemCount++ ;
		frecntt.repaint() ;
		
		//存储组件
        pool.put(userCode, item) ;
        map_freitem_bg.put(userCode, bg) ;
        ch_path_state.put(userCode, map.get("ch_path")) ;
        bu_path_state.put(userCode, map.get("bu_path")) ;
	}
	/**
	 * 
	 * @Title: setFreState 
	 * @Description: TODO(设置好友项的在线状态) 
	 * @param path_flag
	 * void
	 *
	 */
	public void setFreState(String usercode, Map<String, String> map){
		map_freitem_bg.get(usercode).setIcon(new ImageIcon(map.get("ch_path"))) ;
		ch_path_state.put(usercode, map.get("ch_path")) ;
		bu_path_state.put(usercode, map.get("bu_path")) ;
	} 
	/**
	 * 
	 * @Title: setAlertMessage 
	 * @Description: TODO(设置来消息提醒) 
	 * @param usercode
	 * @param path
	 * void
	 *
	 */
	public void setAlertMessage(String usercode, String path){
		map_freitem_bg.get(usercode).setIcon(new ImageIcon(path)) ;
	}
	
	/**
	 * 
	 * @Title: SysItem 
	 * @Description: TODO(系统信息项) 
	 * @param userCode
	 * @param desc
	 * @param ss
	 * void
	 *
	 */
	public void SysItem(String userCode, String desc, Map<String, String> ss){
        JPanel item = new JPanel() ;
        item.addMouseListener(new MouseAdapter() {   
	        public void mouseClicked(MouseEvent e) {
	        	new_code = userCode ;
	        	JPanel tjp = temppool.get("temp") ;
	        	//临时值不等于空而且不等于当前聊天容器方可设置
	        	if(tjp != null && tjp != chatWinPool.get(new_code)){
	        		j. setPosition(tjp, JLayeredPane.PALETTE_LAYER) ;
	        	}
	        	/**
	        	 * 点击设置背景 -- 点击前设置上一次点击的组件为初始状态
	        	 */
	        	if(temp_bg!=null){
	        		temp_bg.setIcon(new ImageIcon(bu_path_state.get(last_usrecode))) ;
	        	}
	        	map_freitem_bg.get(new_code).setIcon(new ImageIcon(ch_path_state.get(new_code))) ;
	        	temp_bg = map_freitem_bg.get(new_code) ;
	        	last_usrecode = new_code ;
	        	//创建系统界面
	        	if(!chatWinPool.isExist((JPanel) chatWinPool.get(new_code))){
	        		createSysFrame(userCode, desc) ;
	        		((JPanel) chatWinPool.get(new_code)).setVisible(true) ;
	        	}else{
	        		((JPanel) chatWinPool.get(new_code)).setVisible(true) ;
	        	}
	        	//设置当前界面为最顶层
	        	j.setPosition((JPanel) chatWinPool.get(new_code), JLayeredPane.DEFAULT_LAYER) ;
	        	j.repaint() ; 
	        	j.validate() ;
	        	//j.validate() ;
	        }
	    });
        
		item.setSize(220, 57) ;
		item.setLocation(0, itemCount*58) ;
		item.setBackground(Color.GRAY) ;
		item.setLayout(null) ;
		item.setCursor(new Cursor(HAND_CURSOR)) ;
		
		//昵称 
		JLabel An = new JLabel("<html><body>"+desc+"</body></html>") ;
		An.setSize(120, 25) ;
		An.setLocation(68, 17) ;
		An.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		An.setForeground(Color.GRAY) ;
		
		bg = new JLabel() ;
		bg.setSize(220, 57);
		bg.setLocation(0, 0);
		bg.setIcon(new ImageIcon(ss.get("bu_path")));
		
		frecntt.add(item) ;
		item.add(An) ;
		item.add(bg) ;
		
		item.setVisible(true) ;
		An.setVisible(true) ;
		bg.setVisible(true) ;
		
		if(itemCount*58+57 > 417){
			max_wheel_fre = (itemCount*58+57) - 417 ; 
			frecntt.setSize(221, itemCount*58+57) ;
		}
		itemCount++ ;
		frecntt.repaint() ;
		
		//存储组件
        pool.put(userCode, item) ;
        map_freitem_bg.put(userCode, bg) ;
        ch_path_state.put(userCode, ss.get("ch_path")) ;
        bu_path_state.put(userCode, ss.get("bu_path")) ;
	}
	/**
	 * 显示系统信息容器<br>
	 * syscnt  
	 */
	private JPanel syscnt = null ;
	private JPanel syspanel = null ;
	/**
	 * 
	 * @Title: chatCntMudle 
	 * @Description: TODO(创建系统界面 -- 返一些如交友请求信息 -- 交友添加信息 -- 交友结果 -- 信息信息 --  )
	 * void
	 *
	 */
	public void createSysFrame(String userCode, String name){
		//设置JPanel的背景
		JPanel syswin = new JPanel() { 
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"syswin.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 549, 
                500, icon.getImageObserver()); 
            }  
        }; 
        syswin.setSize(549, 500) ;
        syswin.setLayout(null) ;
        syswin.setLocation(221, 0) ;
		
		//昵称--在现状态
		JLabel desc = new JLabel("<html><body>"+name+"</body></html>", JLabel.CENTER)  ;
		desc.setSize(140, 30) ;
		desc.setLocation((549-140)/2, 37) ;
		desc.setForeground(Color.white) ;
		desc.setFont(new Font("微软雅黑",Font.PLAIN,14));
		
		//显示系统信息容器
		syscnt = new JPanel() ;
		syscnt.setLayout(null) ;
		syscnt.setSize(549, 436);
		syscnt.setLocation(0, 64);
		
		//聊天信息内部自增容器
		syspanel = new JPanel() ;
		//值随聊天信息的变动而变动
		syspanel.setSize(549, 369) ;
		syspanel.setLocation(0, 0) ;
		syspanel.setLayout(null) ;
		
		syspanel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				flag_chat = true ;
			}
			public void mouseExited(MouseEvent e) {
				flag_chat = false ;
			}
		});
		
		//添加
		j.add(syswin, JLayeredPane.DEFAULT_LAYER) ;
		syswin.add(desc) ;
		syswin.add(syscnt) ;
		syscnt.add(syspanel) ;
		
		//显示
		syspanel.setOpaque(false) ;
		syscnt.setOpaque(false) ;
		
		//设置可见
		syswin.setVisible(false) ;
		desc.setVisible(true) ;
		syscnt.setVisible(true) ;
		syspanel.setVisible(true) ;
		
		syspanel.addMouseWheelListener(this) ;
		syspanel.addMouseListener(this) ;
		
		temppool.put("temp", syswin) ;
		pool1.put(userCode, syspanel) ;
		chatWinPool.put(userCode, syswin) ;
		//创建滚动最大值、高、滚动位置的初始值
		map_max_wheel.put(userCode, 0) ;
		add_sum_height.put(userCode, 0) ;
		wheel_position.put(userCode, 0) ;
	}
	private JLabel ser = null ;
	private JLabel per = null ;
	private JLabel b3 = null ;
	private JLabel b4 = null ;
	private JPanel menu = new JPanel() ;
	/**
	 * 
	 * @Title: Menuitem 
	 * @Description: TODO(菜单) 
	 * void
	 *
	 */
	public void Menuitem(){
		menu = new JPanel(){ 
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"item.png");  //这样的路径打包能识别--登录页面背景
                Image img = icon.getImage() ;  
                g.drawImage(img, 0, 0, 120, 
                135, icon.getImageObserver()) ;
            }  
        } ;
		menu.setSize(125, 140) ;
		menu.setLocation(50, 330) ;
		menu.setLayout(null) ;
		menu.setBackground(Color.gray) ;
		
		ser = new JLabel("<html><body>查找好友</body></html>", JLabel.RIGHT) ;
		per = new JLabel("<html><body>个人信息</body></html>", JLabel.RIGHT) ;
		b3 = new JLabel("<html><body>注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销</body></html>", JLabel.RIGHT) ;
		b4 = new JLabel("<html><body>退出程序</body></html>", JLabel.RIGHT) ;
		ser.setSize(108, 34) ;
		ser.setLocation(0, 0) ;
		per.setSize(108, 34) ;
		per.setLocation(0, 34) ;
		b3.setSize(108, 34) ;
		b3.setLocation(0, 66) ;
		b4.setSize(108, 34) ;
		b4.setLocation(0, 98) ;
		ser.setCursor(new Cursor(HAND_CURSOR)) ;
		per.setCursor(new Cursor(HAND_CURSOR)) ;
		b3.setCursor(new Cursor(HAND_CURSOR)) ;
		b4.setCursor(new Cursor(HAND_CURSOR)) ;
		
		ser.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		per.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		b3.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		b4.setFont(new Font("微软雅黑",Font.PLAIN,14)) ;
		ser.setForeground(Color.GRAY) ;
		per.setForeground(Color.GRAY) ;
		b3.setForeground(Color.GRAY) ;
		b4.setForeground(Color.GRAY) ;
		
		j.add(menu, JLayeredPane.MODAL_LAYER) ;
		menu.add(ser) ;
		menu.add(per) ;
		menu.add(b3) ;
		menu.add(b4) ;
		
		menu.setVisible(false) ;
		ser.setVisible(true) ;
		per.setVisible(true) ;
		b3.setVisible(true) ;
		b4.setVisible(true) ;
		
		ser.addMouseListener(this) ;
		per.addMouseListener(this) ;
		b3.addMouseListener(this) ;
		b4.addMouseListener(this) ;
		menu.addMouseListener(this) ;
		menu.addFocusListener(this) ;
		
	}
	/**
	 * 
	 * @Title: createSysitem 
	 * @Description: TODO(在系统界面中添加项 -- 对方请求添加好友)
	 * @param usercode
	 * @param Aname
	 * @param path
	 * void
	 *
	 */
	public void createMfreRequeitem(String usercode,String Aname,String Date){
		JPanel item = ComponentF.showMfreRequestItem(usercode, Aname, Date) ;
		System.out.println("添加系统信息") ;
		((JPanel) pool1.get("SYSTEM_FRAME")).add(item) ;
		item.setVisible(true) ;
		item.setOpaque(false) ;
		((JPanel) pool1.get("SYSTEM_FRAME")).repaint() ;
		((JPanel) pool1.get("SYSTEM_FRAME")).revalidate() ;
		((JPanel) chatWinPool.get("SYSTEM_FRAME")).revalidate() ;
		System.out.println("添加系统信息") ;
	} 
	public void createMfreReplyitem(String usercode, String Aname, String Date, String msg){
		JPanel item = ComponentF.showMfreReplyItem(usercode, Aname, Date, msg) ;
		System.out.println("添加系统信息") ;
		((JPanel) pool1.get("SYSTEM_FRAME")).add(item) ;
		item.setVisible(true) ;
		item.setOpaque(false) ;
		((JPanel) pool1.get("SYSTEM_FRAME")).repaint() ;
		System.out.println("添加系统信息") ;
	}
	
	//添加好友展示信息
	//usercode 添加方的code
	//path 背景
	
	/**
	 * 查找结果项
	 */
	public void createSerchResultItem(String usercode, String Aname){
		JPanel ser = ComponentF.SerchFreItem(usercode, Aname) ;
		cnt.add(ser) ;
		ser.setVisible(true) ;
		((JPanel) chatWinPool.get("SYSTEM_FRAME")).repaint() ;
		cnt.repaint() ;
	}
	/**
	 * 查找窗口组件
	 */ 
	JPanel serch_win = null ;
	/**
	 * 查找结果容器
	 */
	JPanel cnt = null ;
	/**
	 * 
	 * @Title: createSerch_function 
	 * @Description: TODO(查找窗口-功能) 
	 * void
	 *
	 */
	public void createSerch_function(){
		serch_win = ComponentF.serch_function() ;
		cnt = ComponentF.getResultcnt() ;
		
		j.add(serch_win, JLayeredPane.DRAG_LAYER) ;
		serch_win.add(cnt) ;
		
		serch_win.setVisible(false) ;
		cnt.setVisible(true) ;
	}
	/**
	 * 修改个人信息界面
	 */
	private JPanel updata_info_ = null ;
	/**
	 * 
	 * @Title: createUpDateinfo_function 
	 * @Description: TODO(修改个人信息界面) 
	 * void
	 *
	 */
	public void createUpDateinfo_function(){
		updata_info_ = ComponentF.upDateinfo_function();
		j.add(updata_info_, JLayeredPane.DRAG_LAYER) ;
		updata_info_.setVisible(false) ;
	}
	private JPanel down_win = null ;
	/**
	 * 
	 * @Title: createdown_function 
	 * @Description: TODO(注销界面) 
	 * void
	 *
	 */
	public void createdown_function(){
		down_win = ComponentF.down_function() ;
		j.add(down_win, JLayeredPane.DRAG_LAYER) ;
		down_win.setVisible(false) ;
	}
	JPanel close_win = null ;
	/**
	 * 
	 * @Title: createClose_function 
	 * @Description: TODO(关闭程序界面) 
	 * void
	 *
	 */
	public void createClose_function(){
		close_win = ComponentF.close_function() ;
		j.add(close_win, JLayeredPane.DRAG_LAYER) ;
		close_win.setVisible(false) ;
	}
	/**
	 * 
	 * @Title: createAlertWin 
	 * @Description: TODO(创建提示窗口) 
	 * @param err_str
	 * void
	 *
	 */
	public void createAlertWin(String err_str){
		JPanel alert_win = ComponentF.alert_JPanel(err_str) ;
		j.add(alert_win, JLayeredPane.DRAG_LAYER) ;
		alert_win.setVisible(true) ;
	}
	
	//点击事件
	/*
	* serch
	* jb
	* send
	* more
	*/
	
	public static void main(String[] args){
		MainFrame t = new MainFrame() ;
	}
	/**
	 * 事件
	 */
	boolean flag_menu = true ;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(frecntt)){
    	}else if(e.getSource().equals(send)){
    		//创建新的信息模板后再设置最低端
    	}else if(e.getSource().equals(ser)){
    		closeMenu() ;
    		serch_win.setVisible(true) ;
    	}else if(e.getSource().equals(per)){
    		closeMenu() ;
    		updata_info_.setVisible(true) ;
    	}else if(e.getSource().equals(b3)){
    		closeMenu() ;
    		down_win.setVisible(true) ;
    	}else if(e.getSource().equals(b4)){
    		closeMenu() ;
    		close_win.setVisible(true) ;
    	}else if(e.getSource().equals(jb)){
    		if(flag_menu){
    			menu.setVisible(true);
    			flag_menu = false ;
    			menu.repaint();
    		}else{
    			menu.setVisible(false);
    			flag_menu = true ;
    			menu.repaint();
    		}
    		
    	}else if(e.getSource().equals(menu)){
    	}
	}
	
	public void closeMenu(){
		menu.setVisible(false);
		flag_menu = true ;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(frecntt)){
			flag_fre = true ;
    	} else if(e.getSource().equals(jb)){
    		menu.setVisible(true) ;
    		flag_menu = false ;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(frecntt)){
			flag_fre = false ;
    	} 
	}
	//滚动
	//
	/**
	 * 判断滚动的界面 -- 好友列表滚动<br> 
	 * 通过鼠标进入判断
	 */
	private boolean flag_fre = false ;
	/**
	 * 判断当前滚动的界面 -- 聊天容器滚动<br>
	 * 通过鼠标进入判断
	 */
	private boolean flag_chat = false ;
	/**
	 * 好友容器的滚动值 <br>不能大于零
	 */
	private int wheel_fre = 0 ;
	/**
	 * 好友容器的滚动最大值
	 */
	private int max_wheel_fre = 0 ;
	public void mouseWheelMoved(MouseWheelEvent e) {
		//取界面的的滚动位置
		int position = wheel_position.get(new_code) ;
		//获取最大滚动位置
		int max = map_max_wheel.get(new_code) ;
 		if(e.getWheelRotation() > 0){
			if(flag_fre){
				if(wheel_fre < 0){
					wheel_fre += 10 ;
				}
			}
			if(flag_chat){
				if(position < 0){
					position += 20 ;
				}
			}
		}else{
			if(flag_fre){
				if(wheel_fre > -max_wheel_fre){
					wheel_fre -= 10 ;
				}
			}
			if(flag_chat){
				if(position > -max){
					position -= 20 ;
				}
			}
		}
		frecntt.setLocation(0, wheel_fre) ;
		frecntt.repaint() ;
		JPanel jpanel_now = (JPanel) pool1.get(new_code) ;
		jpanel_now.setLocation(0, position) ;
		jpanel_now.repaint() ;
		//覆盖旧值
		wheel_position.put(new_code, position) ;
	}
	@Override
	public void focusGained(FocusEvent e) {
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(jb)){
			closeMenu() ;
		}else if(e.getSource().equals(menu)){
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			System.out.println("回车") ;
			//chatMudle(new_code, chatinfo.getText()) ;
    		//信息发完之后自动计算所发的信息所占的高度，累加构成界面高度
			chatinfo.setText("") ;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("按键") ;
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			System.out.println("回车") ;
			//chatMudle(new_code, chatinfo.getText()) ;
    		//信息发完之后自动计算所发的信息所占的高度，累加构成界面高度
			chatinfo.setText("") ;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class ChatJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public ChatJPanel(){
		
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(18, 150, 219));
		RoundRectangle2D.Double rect=new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight()-10, 15, 15);		 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); 
		g2d.fill(rect);
		g2d.draw(rect);
	}
}
class RChatJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public RChatJPanel(){
		
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(255, 255, 255));
		RoundRectangle2D.Double rect=new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight()-10, 15, 15);		 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); 
		g2d.fill(rect);
		g2d.draw(rect);
	}
}
