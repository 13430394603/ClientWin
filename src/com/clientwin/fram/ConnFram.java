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
import java.net.InetAddress;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clientwin.conn.ConnParam;
import com.clientwin.core.TestLine;
import com.clientwin.pool.JpanelPool;
import com.clientwin.reci.ReciBord;
/**
 * 
 * @ClassName: ConnFram 
 * @Description: TODO(这是连接参数配置界面) 
 * @author 威 
 * @date 2017年5月30日 下午10:36:23 
 *
 */
public class ConnFram extends JFrame implements MouseListener {
	//String spath = System.getProperty("user.dir") + "/src\\com\\clientwin\\img/" ;
	String spath = System.getProperty("user.dir") + "/img/" ;
	private static final long serialVersionUID = 1L ;
	private static LoginFrame log = null ;
	/**
	 * 连接参数
	 */
	private static String IP = "" ;
	private static String PORT = "" ;
	
	/**
	 * 组件
	 */
	JLayeredPane fram = null ;
	JButton top_close = null ;
	JButton conn = null ;
	static JTextField ip_component = null ;
	JTextField port_component = null ;
	
	/**
	 * 拖动参数
	 */
	private boolean isMoved = false ;
	private Point pre_point = null ;
	private Point end_point = null ;
	/**
	 * 连接参数配置界面 的构造方法
	 */
	public ConnFram(){
		int w_ = Toolkit.getDefaultToolkit().getScreenSize().width; 
	    int h_ = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(350, 216) ;
		this.setLocation((w_-350)/2, (h_-216)/2) ;  //计算窗口居中
		this.setUndecorated(true) ; //去边框
		this.setDragable(this) ; //自定义拖动
		setBacis() ;
		mainf() ;
		componentf() ;
		/*InetAddress address = null ;
		try {
			address = InetAddress.getLocalHost() ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ipadd = address.getHostAddress() ;
		String ipstring = ipadd.substring(ipadd.indexOf("/")+1, ipadd.length()) ;*/
		ip_component.setText(new ReciBord().reciIp()) ;
		//把对象设置暂存于GetWinObject对象中一遍一些操作的进行
		GetWinObject.newIstants().setConnFram(this) ; 
	} 
	/**
	 * 
	 * @Title: setBacis 
	 * @Description: TODO(设置基本的JFrame信息) 
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
	 * @Description: TODO(主要界面) 
	 * void
	 *
	 */
	public void mainf(){
		fram = new JLayeredPane(){
			private static final long serialVersionUID = 1L ;
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(spath+"conn.png");  
                Image img = icon.getImage() ;  
                g.drawImage(img, 0, 0, 350, 
                216, icon.getImageObserver()) ;
            } 
		};
		fram.setSize(350, 300) ;
		fram.setLayout(null) ;
		
		this.add(fram) ;
		
		fram.setVisible(true) ;
		
	}
	
	/**
	 * 
	 * @Title: componentf 
	 * @Description: TODO(其他组件堆积处) 
	 * void
	 *
	 */
	public void componentf(){
		//关闭按钮
		top_close = new JButton() ;
		top_close.setSize(32, 32) ;
		top_close.setLocation(316, 2);
		top_close.setBorder(BorderFactory.createEmptyBorder());
		top_close.setCursor(new Cursor(HAND_CURSOR)) ;
		
		ip_component = new JTextField() ;
		port_component = new JTextField() ;
		
		ip_component.setSize(180, 30) ;
		ip_component.setLocation(110, 76) ;
		port_component.setSize(180, 30) ;
		port_component.setLocation(110, 130) ;
		
		conn = new JButton() ;
		conn.setSize(64, 64) ;
		conn.setLocation(282, 168) ;
		conn.setCursor(new Cursor(HAND_CURSOR)) ;
		
		ip_component.setFont(new Font("微软雅黑",Font.PLAIN,13)) ;
		port_component.setFont(new Font("微软雅黑",Font.PLAIN,13)) ; 
		ip_component.setBorder(BorderFactory.createEmptyBorder()) ;
		port_component.setBorder(BorderFactory.createEmptyBorder()) ;
		conn.setBorder(BorderFactory.createEmptyBorder()) ;
		
		fram.add(top_close, JLayeredPane.PALETTE_LAYER) ;
		fram.add(ip_component, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(port_component, JLayeredPane.DEFAULT_LAYER) ;
		fram.add(conn, JLayeredPane.DEFAULT_LAYER) ;
		
		conn.setContentAreaFilled(false) ;
		top_close.setContentAreaFilled(false) ;
		top_close.setFocusable(false) ;
		top_close.setOpaque(false) ;
		ip_component.setOpaque(false) ;
		port_component.setOpaque(false) ;
		conn.setOpaque(false) ;
		
		ip_component.setVisible(true) ;
		port_component.setVisible(true) ;
		top_close.setVisible(true) ;
		
		top_close.addMouseListener(this) ;
		conn.addMouseListener(this) ;
		fram.repaint() ;
	}
	
		
	/**
	 * 
	 * @Title: setDragable 
	 * @Description: TODO(拖动界面) 
	 * @param frame
	 * void
	 *
	 */
	//实现拖拽
	// 为窗口加上监听器，使得窗口可以被拖动  
	private void setDragable(final ConnFram frame) {
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
                    frame.setLocation(end_point) ;  
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
		TestLine testLine = TestLine.newInstants() ;
		String err_msg = testLine.getMessage(err_str) ; 
		/**
		 * 提示窗
		 */
		err_alert_win = new JPanel();
		err_alert_win.setSize(350, 216) ;
		err_alert_win.setLocation(0, 0) ;
		err_alert_win.setLayout(null) ;
		JLabel bg = new JLabel() ;
		err_alert_win.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				err_alert_win.setVisible(false);
			}
		});
		bg.setSize(350, 216) ;
		ImageIcon ico = new ImageIcon(spath+"1.png") ;
		bg.setIcon(ico) ;
		
		/**
		 * 提示框
		 */
		JPanel err_alert = new ChatJPanel() ;
		err_alert.setLayout(null) ;
		
		/**
		 * 提示内容
		 */
		JLabel err_message = new JLabel(err_msg) ;
		err_message.setSize(testLine.getWidth2(), testLine.getHeight2());
		err_message.setLocation(6, 3);
		err_alert.setSize(err_message.getWidth()+8, err_message.getHeight()+16) ;
		err_alert.setLocation((350-(err_message.getWidth()+8))/2, (216-(err_message.getHeight()+16))/2) ;
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
	/**
	 * 
	 * @Title: main 
	 * @Description: TODO(main方法) 
	 * @param args
	 * void
	 *
	 */
	public static void main(String[] args){
		new ConnFram() ;
		JPanel jp = new JPanel() ;
		JpanelPool.newInstans().put("123456", jp);
	}
	public static LoginFrame getLoginWin(){
		return log ;
	}
	/**
	 * 事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(top_close)){
			System.exit(0) ;
		}else if(e.getSource().equals(conn)){
			int lenght1 = ip_component.getText().length() ;
			int length2 = port_component.getText().length() ;
			if(lenght1 != 0 && length2 != 0){
				IP = ip_component.getText() ;
				PORT = port_component.getText() ;
				ConnParam conn = ConnParam.newInstants() ;
				conn.setIp(IP) ;
				conn.setPort(PORT) ;
				//检测一下当前输入的服务器ip地址在三秒内是否可达
				//可达则进行下一步操作
				try {
					if(InetAddress.getByName(IP).isReachable(2000)){
						log = new LoginFrame() ;
						this.setVisible(false) ;
					}
					else{
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
	class ChatJPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChatJPanel(){
			
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

}
