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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clientwin.conn.ConnParam;
import com.clientwin.core.ArrayJson;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TestLine;
import com.clientwin.core.VerCodeUtil;
import com.clientwin.pool.JpanelPool;
import com.clientwin.reci.ReciBord;
import com.clientwin.request.CloseRequest;
import com.clientwin.request.LoginRequest;
import com.clientwin.util.RecordLog;
/**
 * 
 * @ClassName: LoginFrame 
 * @Description: TODO(登录界面) 
 * @author 威 
 * @date 2017年5月30日 下午10:37:17 
 *
 */
public class LoginFrame extends JFrame implements MouseListener{
	String spath = System.getProperty("user.dir") + "/img/" ;
	private static final long serialVersionUID = 1L;
	/**
	 * 主界面
	 */
	JLayeredPane fram = null ;
	/**
	 * 登录按钮
	 */
	JButton login = null ;
	/**
	 * 登录信息输入框组件
	 */
	private JTextField user = null ;
	private JTextField pass = null ;
	/**
	 * 验证码容器JLabel
	 */
	JLabel vercode_p = null ;
	/**
	 * 点击跳转注册界面
	 */
	JLabel register = null ;
	
	private JTextField vercode = null ;
	/**
	 * 拖动
	 */
	private boolean isMoved = false ;
	private Point pre_point = null ;
	private Point end_point = null ;
	/**
	 * 验证码的图片路径及其他相关的参数
	 */
	private String path = "" ;
	private String VER_CODE = "" ;
	
	/**
	 * 注册页面对象
	 */
	private static RegisterFrame rgr = null ;
	
	public static boolean flag_close = false ;
	/**
	 * 登录界面构造方法
	 */
	public LoginFrame(){
		RecordLog.doStartLog() ; // 打开登录页面时日志开始
		RecordLog.doLog("启动登录页面") ;
		
		int w_ = Toolkit.getDefaultToolkit().getScreenSize().width;
	    int h_ = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(350, 249) ;
		this.setLocation((w_-350)/2, (h_-250)/2) ;
		this.setUndecorated(true) ;
		this.setDragable(this) ;
		this.setIconImage(new ImageIcon(getClass().getResource("/ico/appico.png")).getImage()) ;
		getVercode() ;
		setBacis() ;
		mainf() ;
		topf() ;
		componentf() ;
		JpanelPool pool = JpanelPool.newInstans() ;
		System.out.println(pool.getSize()) ;
		ConnParam conn = ConnParam.newInstants() ;
		
		//可以直接通过广播接收服务器的ip号和端口号，每次打开软件只需要直接打开登录页面而不需要打开配置页面
		goConnecte() ; //测试服务器是否异常
		System.out.println("_"+conn.getIP());
		GetWinObject.newIstants().setLoginFrame(this) ;
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(flag_close){
					//发送关闭使当前用户池删除该链接
					CrateSendMessage Message = new CrateSendMessage() ;
					Message.setFrom(ConnParam.getIP()) ;
					CloseRequest.newInstants().doRequest(Message) ;
				}
			}
		});
	}
	/**
	 * 
	 * 测试服务器是否异常
	 * @see
	 * void
	 *
	 */
	public void goConnecte(){
		System.out.println("正在进行连接服务器测试") ;
		String ip = new ReciBord().reciIp() ;
		int lenght1 = ip.length() ;
		if(lenght1 != 0){
			ConnParam conn = ConnParam.newInstants() ;
			System.out.println("获取ip显示"+ip) ;
			conn.setIp(ip) ;
			conn.setPort("2010") ;
			//检测一下当前输入的服务器ip地址在三秒内是否可达
			//可达则进行下一步操作
			try {
				if(!InetAddress.getByName(ip).isReachable(2000)){
					System.out.println("该服务器不可连接") ;
					err_win("该服务器不可连接") ;
					//提示框提示并重新填写
				}
			} catch (Exception e1) {
				e1.printStackTrace() ;
			}
		}else{
			//为空则有错提示
			err_win("参数不能为空") ;
		}
	}
	/**
	 * 
	 * @Title: getVercode 
	 * @Description: TODO(调用验证码工具类生成验证码) 
	 * void
	 *
	 */
	public void getVercode(){
		VerCodeUtil vercode = VerCodeUtil.newInstants() ;
		Object[] object = vercode.createVerCode() ;
		VER_CODE = (String) object[1] ;
		path = (String) object[2] ;
	}
	/**
	 * 
	 * @Title: setBacis 
	 * @Description: TODO(JFrame的基本信息配置) 
	 * void
	 *
	 */
	public void setBacis(){
		this.setLayout(null) ;
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ; 
		this.setVisible(true) ;
	}
	
	//主界面
	
	/**
	 * 
	 * @Title: mainf 
	 * @Description: TODO(主界面) 
	 * void
	 *
	 */
	public void mainf(){
		fram = new JLayeredPane(){
			private static final long serialVersionUID = 1L ;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"login2.png");  
                Image img = icon.getImage() ;  
                g.drawImage(img, 0, 0, 350, 
                250, icon.getImageObserver()) ;
            } 
		};
		fram.setSize(350, 250) ;
		fram.setLayout(null) ;
		
		this.add(fram) ;
		
		fram.setVisible(true) ;
		
	}
	//顶部
	JButton top_close = null ;
	public void topf(){
		//关闭按钮
		top_close = new JButton() ;
		top_close.setSize(32, 32) ;
		top_close.setLocation(316, 2);
		top_close.setBorder(BorderFactory.createEmptyBorder());
		top_close.setCursor(new Cursor(HAND_CURSOR)) ;
		
		fram.add(top_close, JLayeredPane.PALETTE_LAYER) ;
		
		top_close.setContentAreaFilled(false) ;
		top_close.setFocusable(false) ;
		
		
		top_close.addMouseListener(this) ;
	}
	//其他组件
	
	/**
	 * 
	 * @Title: componentf 
	 * @Description: TODO(其他组价堆积处) 
	 * void
	 *
	 */
	public void componentf(){
		user = new JTextField() ;
		pass = new JTextField() ;
		vercode = new JTextField() ;
		user.setSize(180, 30) ;
		user.setLocation(110, 64) ;
		pass.setSize(180, 30) ;
		pass.setLocation(110, 118) ;
		vercode.setSize(80, 30) ;
		vercode.setLocation(110, 170) ;
		user.setBorder(BorderFactory.createEmptyBorder()) ;
		pass.setBorder(BorderFactory.createEmptyBorder()) ;
		vercode.setBorder(BorderFactory.createEmptyBorder()) ;
		user.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		pass.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		vercode.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		
		login = new JButton() ;
		login.setSize(32, 32) ;
		login.setLocation(293, 207) ;
		login.setBorder(BorderFactory.createEmptyBorder()) ;
		login.setCursor(new Cursor(HAND_CURSOR)) ;
		
		vercode_p = new JLabel("4562") ;
		vercode_p.setSize(90, 39) ;
		vercode_p.setLocation(205, 166) ;
		vercode_p.setIcon(new ImageIcon(path));
		vercode_p.setCursor(new Cursor(HAND_CURSOR)) ;
		
		register = new JLabel("前往注册") ;
		register.setSize(90, 30) ;
		register.setLocation(32, 218) ;
		register.setForeground(Color.GRAY) ;
		register.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		register.setCursor(new Cursor(HAND_CURSOR)) ;
		
		fram.add(user, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(pass, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(vercode, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(login, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(vercode_p, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(register, JLayeredPane.DEFAULT_LAYER);
		
		user.setOpaque(false) ;
		pass.setOpaque(false) ;
		vercode.setOpaque(false) ;
		login.setOpaque(false) ;
		login.setContentAreaFilled(false) ;
		login.setFocusable(false) ;
		
		user.setVisible(true) ;
		pass.setVisible(true) ;
		vercode.setVisible(true) ;
		login.setVisible(true) ;
		vercode_p.setVisible(true) ;
		register.setVisible(true) ;
		
		login.addMouseListener(this) ;
		vercode_p.addMouseListener(this) ;
		register.addMouseListener(this) ;
	}
	//实现拖拽
	// 为窗口加上监听器，使得窗口可以被拖动  
	
	/**
	 * 
	 * @Title: setDragable 
	 * @Description: TODO(拖动) 
	 * @param frame
	 * void
	 *
	 */
	private void setDragable(final LoginFrame frame) {
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
    } 
	/**
	 * 错误提示窗
	 * 外界可以调用
	 */
	public static JPanel err_alert_win = null ;
	public void err_win(String err_str){
		RecordLog.doLog("登录错误提示 " + err_str) ;
		TestLine testLine = TestLine.newInstants() ;
		String err_msg = testLine.getMessage(err_str) ; 
		/**
		 * 提示窗
		 */
		err_alert_win = new JPanel();
		err_alert_win.setSize(350, 249) ;
		err_alert_win.setLocation(0, 0) ;
		err_alert_win.setLayout(null) ;
		JLabel bg = new JLabel() ;
		err_alert_win.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				err_alert_win.setVisible(false) ;
			}
		});
		bg.setSize(350, 249) ;
		ImageIcon ico = new ImageIcon(spath+"img/2.png") ;
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
		err_message.setSize(testLine.getWidth2(), testLine.getHeight2());
		err_message.setLocation(6, 3);
		err_alert.setSize(err_message.getWidth()+8, err_message.getHeight()+16) ;
		err_alert.setLocation((350-(err_message.getWidth()+8))/2, (249-(err_message.getHeight()+16))/2) ;
		err_message.setForeground(new Color(255, 255, 255)) ;
		/**
		 * 设置字体
		 */
		err_message.setFont(new Font("微软雅黑",Font.PLAIN,14));
		
		/**
		 * 添加
		 */
		fram.add(err_alert_win, JLayeredPane.DRAG_LAYER) ;
		err_alert_win.add(err_alert) ;
		err_alert_win.add(bg) ;
		err_alert.add(err_message) ;
		err_alert_win.setOpaque(false) ;
		
		/**
		 * 设置可视
		 */
		err_alert_win.setVisible(true) ;
		err_alert.setVisible(true) ;
		err_message.setVisible(true) ;
		bg.setVisible(true) ;
	}
	public static void main(String[] args){
		LoginFrame f = new LoginFrame() ;
	}
	/**
	 * 
	 * @Title: close 
	 * @Description: TODO(调用关闭此程序) 
	 * void
	 *
	 */
	public static void close(){
		RecordLog.doLog("退出登录页面") ;
		System.exit(0) ;
	}
	public static RegisterFrame getRegisterWin(){
		return rgr ;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(top_close)){
			RecordLog.doLog("退出登录页面") ;
			System.exit(0);
		}else if(e.getSource().equals(login)){
			RecordLog.doLog("点击登录按钮") ;
			//判断登录数据是否正确
			System.out.println("login...") ;
			int length1 = user.getText().length() ;
			int length2 = pass.getText().length() ;
			int length3 = vercode.getText().length() ;
			if(length1!=0&&length2!=0&&length3!=0){
				if(vercode.getText().equals(VER_CODE)){
					System.out.println("允许登录") ;
					ArrayJson json = new ArrayJson() ;
					json.put("user", user.getText()) ;
					json.put("pass", pass.getText()) ;
					CrateSendMessage msgMudle = CrateSendMessage.newInstans() ;
					msgMudle.setContent(json.getMessage()) ;
					LoginRequest.newInstants().doRequest(msgMudle) ;
					//等待服务响应做出下一部动作
				}
				else{
					err_win("填写的验证码错误") ;
				}
			}else{
				err_win("填写的用户名、密码或验证码不能为空") ;
			}
			getVercode() ;
			//ImageIcon 需要使用时间戳路径防止路径一致刷新不了
			vercode_p.setIcon(new ImageIcon(path)) ;
			
		}else if(e.getSource().equals(register)){
			RecordLog.doLog("进入注册页面") ;
			this.setVisible(false) ;
			rgr = new RegisterFrame() ;
		}else if(e.getSource().equals(vercode_p)){
			RecordLog.doLog("获取新的验证码") ;
			getVercode() ;
			//ImageIcon 需要使用时间戳路径防止路径一致刷新不了
			vercode_p.setIcon(new ImageIcon(path)) ;
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
class ChatJPanelel extends JPanel{
	private static final long serialVersionUID = 1L;
	public ChatJPanelel(){
		
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(25, 25, 25));
		RoundRectangle2D.Double rect=new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight()-10, 15, 15);		 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); 
		g2d.fill(rect);
		g2d.draw(rect);
	}
}
