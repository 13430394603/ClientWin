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
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.clientwin.conn.Client;
import com.clientwin.conn.ConnParam;
import com.clientwin.core.ArrayJson;
import com.clientwin.core.CrateSendMessage;
import com.clientwin.core.TestLine;
import com.clientwin.fram.ConnFram.ChatJPanel;
import com.clientwin.pool.JpanelPool;
import com.clientwin.request.RegisterRequest;

/**
 * 
 * @ClassName: RegisterFrame 
 * @Description: TODO(注册界面) 
 * @author 威 
 * @date 2017年5月30日 下午10:58:57 
 *
 */
public class RegisterFrame extends JFrame implements MouseListener{
	/*String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;*/
	String spath = System.getProperty("user.dir") + "/img/" ;
	private static final long serialVersionUID = 1L;
	/**
	 * 组件
	 */
	JLayeredPane fram = null ;
	JButton top_close = null ;
	JButton register = null ;
	JLabel login = null ;
	/**
	 * 处理拖动的参数
	 */
	private boolean isMoved = false ;
	private Point pre_point = null ;
	private Point end_point = null ;
	public RegisterFrame(){
		int w_ = Toolkit.getDefaultToolkit().getScreenSize().width ;
	    int h_ = Toolkit.getDefaultToolkit().getScreenSize().height ;
		this.setSize(350, 300) ;
		this.setLocation((w_-350)/2, (h_-300)/2) ;
		this.setUndecorated(true) ;
		this.setDragable(this) ;
		setBacis() ;
		mainf() ;
		topf() ;
		componentf() ;
		JpanelPool pool = JpanelPool.newInstans() ;
		System.out.println(pool.getSize()) ;
		ConnParam conn = ConnParam.newInstants() ;
		System.out.println("sfd"+conn.getIP()) ;
		GetWinObject.newIstants().setRegisterFrame(this) ; 
		
	}
	/**
	 * 
	 * @Title: setBacis 
	 * @Description: TODO(基础参数的配置) 
	 * void
	 *
	 */
	public void setBacis(){
		this.setLayout(null) ;
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ; 
		this.setVisible(true) ;
	}
	/**
	 * 
	 * @Title: mainf 
	 * @Description: TODO(主要) 
	 * void
	 *
	 */
	public void mainf(){
		fram = new JLayeredPane(){
			private static final long serialVersionUID = 1L ;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"register.png");  
                Image img = icon.getImage() ;  
                g.drawImage(img, 0, 0, 350, 
                300, icon.getImageObserver()) ;
            } 
		};
		fram.setSize(350, 300) ;
		fram.setLayout(null) ;
		
		this.add(fram) ;
		
		fram.setVisible(true) ;
		
	}
	//顶部
	//JPanel top_jpanel = null ;
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
	JTextField user = new JTextField() ;
	JPasswordField pass = new JPasswordField() ;
	JPasswordField pass_ = new JPasswordField() ;
	JTextField email = new JTextField() ;
	//其他组件	
	/**
	 * 
	 * @Title: componentf 
	 * @Description: TODO(其他组件堆放处 ) 
	 * void
	 *
	 */
	public void componentf(){
		user.setSize(180, 30) ;
		user.setLocation(110, 64) ;
		pass.setSize(180, 30) ;
		pass.setLocation(110, 118) ;
		pass_.setSize(180, 30) ;
		pass_.setLocation(110, 169) ;
		email.setSize(180, 30) ;
		email.setLocation(110, 220) ;
		user.setBorder(BorderFactory.createEmptyBorder()) ;
		pass.setBorder(BorderFactory.createEmptyBorder()) ;
		pass_.setBorder(BorderFactory.createEmptyBorder()) ;
		email.setBorder(BorderFactory.createEmptyBorder()) ;
		user.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		pass.setFont(new Font("微软雅黑",Font.PLAIN,13)) ; 
		pass_.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		email.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		
		register = new JButton() ;
		register.setSize(32, 32) ;
		register.setLocation(294, 261) ;
		register.setBorder(BorderFactory.createEmptyBorder()) ;
		register.setCursor(new Cursor(HAND_CURSOR)) ;
		
		login = new JLabel("前往登录") ;
		login.setSize(90, 30) ;
		login.setLocation(32, 268) ;
		login.setForeground(Color.GRAY) ;
		login.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		login.setCursor(new Cursor(HAND_CURSOR)) ;
		
		fram.add(user, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(pass, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(pass_, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(register, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(login, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(email, JLayeredPane.DEFAULT_LAYER) ;
		
		user.setOpaque(false) ;
		pass.setOpaque(false) ;
		pass_.setOpaque(false) ;
		email.setOpaque(false) ;
		register.setOpaque(false) ;
		register.setContentAreaFilled(false) ;
		register.setFocusable(false) ;
		
		user.setVisible(true) ;
		pass.setVisible(true) ;
		pass_.setVisible(true) ;
		email.setVisible(true) ;
		register.setVisible(true) ;
		login.setVisible(true) ;
		
		register.addMouseListener(this) ;
		login.addMouseListener(this) ;
	}	
	//实现拖拽
	// 为窗口加上监听器，使得窗口可以被拖动  
	/**
	 * 
	 * @Title: setDragable 
	 * @Description: TODO(处理窗口拖动) 
	 * @param frame
	 * void
	 *
	 */
	private void setDragable(final RegisterFrame frame) {
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
	public void setVisible(){
		this.setVisible(false) ;
	}
	/**
	 * 错误提示窗
	 * 外界可以调用
	 */
	public static JPanel err_alert_win = null ;
	public void err_win(String err_str){
		TestLine testLine = TestLine.newInstants() ;
		String err_msg = testLine.getMessage(err_str) ; 
		/**
		 * 提示窗
		 */
		err_alert_win = new JPanel();
		err_alert_win.setSize(350, 300) ;
		err_alert_win.setLocation(0, 0) ;
		err_alert_win.setLayout(null) ;
		JLabel bg = new JLabel() ;
		err_alert_win.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				err_alert_win.setVisible(false);
			}
		});
		bg.setSize(350, 300) ;
		ImageIcon ico = new ImageIcon(spath+"3.png") ;
		bg.setIcon(ico) ;
		
		/**
		 * 提示框
		 */
		JPanel err_alert = new AlertJPanel() ;
		err_alert.setLayout(null) ;
		
		/**
		 * 提示内容
		 */
		JLabel err_message = new JLabel(err_msg) ;
		err_message.setSize(testLine.getWidth2(), testLine.getHeight2());
		err_message.setLocation(6, 3);
		err_alert.setSize(err_message.getWidth()+8, err_message.getHeight()+16) ;
		err_alert.setLocation((350-(err_message.getWidth()+8))/2, (300-(err_message.getHeight()+16))/2) ;
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
		new RegisterFrame() ;
		
	}
	/**
	 * 事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(top_close)){
			System.exit(0);
		}else if(e.getSource().equals(register)){
			//点击判断信息无误发送注册信息到服务器
			int len1 = user.getText().length() ; 
			int len2 = pass.getText().length() ;
			int len3 = email.getText().length() ;
			int len4 = pass_.getText().length() ;
			if(len1 != 0 && len2 != 0 && len3 != 0 && len4 != 0){
				if(pass.getText().equals(pass_.getText())){
					ArrayJson json = new ArrayJson() ;
					json.put("user", user.getText()) ;
					json.put("pass", pass.getText()) ;
					json.put("email", email.getText()) ;
					CrateSendMessage msgMudle = CrateSendMessage.newInstans() ;
					msgMudle.setContent(json.getMessage()) ;
					RegisterRequest.newInstants().doRequest(msgMudle) ;
					//等待服务响应做出下一部动作
					return ;
				}
				else{
					err_win("确认密码不正确") ;
				}
			}
			else{
				err_win("输入不能为空") ;
			}
		}else if(e.getSource().equals(login)){
			this.setVisible(false) ;
			new LoginFrame() ;
		}
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 	
}
class AlertJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public AlertJPanel(){
		
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