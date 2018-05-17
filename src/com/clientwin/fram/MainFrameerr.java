package com.clientwin.fram;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @ClassName: MainFrameerr 
 * @Description: TODO(聊天程序的主要界面 -- 实验界面) 
 * @author 威 
 * @date 2017年5月30日 下午11:02:14 
 *
 */
public class MainFrameerr extends JFrame {
	private static final long serialVersionUID = 1L;
	//分层容器
	static JLayeredPane jlayer = null ;
	//展示背景图的label
	static JLabel jbackground = null ;
	//背景图
	static ImageIcon ico = null ;
	//左边Panel
	static JPanel ljpanel = null ;
	
	JTextField serch = null ;
	
	//右边Panel
	
	JButton test = null ;
	
	MainFrameerr(){
		this.setSize(770, 500);
		this.setLocation(400, 500);
		this.setUndecorated(true);
		setBacis() ;
		mFrame() ;
		
		//repaint_manual() ;
	}
	public void repaint_manual() {
	    this.validate();
	    this.repaint();
	}
	public void setBacis(){
		this.setLayout(null);
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void mFrame(){
		jlayer = new JLayeredPane() ;
		jlayer.setSize(770, 500);
		jlayer.setLayout(null);
		jlayer.setVisible(true);
		this.add(jlayer);
		//添加的组件置放这
		//添加背景 -- 背景被设在最低层
		jbackground = new JLabel() ;
		jbackground.setSize(770, 500);
		//页面背景
		ico = new ImageIcon("C:\\Users\\win7\\Desktop/main3.png");  
		jbackground.setIcon(ico);
		jlayer.add(jbackground, 0);
		jbackground.setVisible(true);
		
		lFram() ;
		
	}
	JPanel frecnt = null ;
	public void lFram(){
		ljpanel = new JPanel() ;
		ljpanel.setSize(221, 500) ;
		ljpanel.setLayout(null);
		
		
		
		//添加ljpanel到jlayer
		
		
		//添加搜索框
		serch = new JTextField() ;
		serch.setSize(150,30);
		serch.setLocation(21, 8);
		
		
		//添加好友列表容器
		frecnt = new JPanel() ;
		frecnt.setSize(220, 417);
		frecnt.setLocation(0, 49);
		frecnt.setLayout(null) ;
		frecnt.setBackground(Color.BLUE);
		
		
		JButton jb = new JButton("aya") ;
		jb.setBounds(0, 0, 120, 30);
		
		
		test = new JButton("gougou") ;
		test.setBounds(173,9,29,29) ;
		
		
		ljpanel.setOpaque(false);
		frecnt.setOpaque(true);
		jlayer.add(ljpanel, 1);
		ljpanel.add(frecnt) ;
		frecnt.add(jb) ;
		ljpanel.add(serch) ;
		ljpanel.add(test) ;
		
		serch.setVisible(true);
		ljpanel.setVisible(true);
		frecnt.setVisible(true) ;
		jb.setVisible(true);
		test.setVisible(true);
		
		ljpanel.repaint() ;
		frecnt.repaint() ;
	}
	public void rFram(){
		
	}
	
	//实现拖拽
	// 为窗口加上监听器，使得窗口可以被拖动  
	private boolean isMoved = false ;
	private Point pre_point = null ;
	private Point end_point = null ;
	private void setDragable(final MainFrameerr frame) {
    	this.addMouseListener(new java.awt.event.MouseAdapter() {  
            public void mouseReleased(java.awt.event.MouseEvent e) {  
                isMoved = false;// 鼠标释放了以后，是不能再拖拽的了  
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
  
            public void mousePressed(java.awt.event.MouseEvent e) {  
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
	public static void main(String[] args){
		MainFrameerr frame = new MainFrameerr();
		
		//frame.validate() ;
		//条用拖拽方法
		frame.setDragable(frame) ;
		
	}
	
}
