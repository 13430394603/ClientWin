package com.clientwin.fram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.LineMetrics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * 
 * @ClassName: ButtonStyle3 
 * @Description: TODO(��ť��ʽ3) 
 * @author �� 
 * @date 2017��4��15�� ����1:51:28 
 *
 */
class ButtonStyle3 extends JButton{
	private static final long serialVersionUID = 1965063150601339314L;
	private String state="normal";
	private String text="";
	ButtonStyle3(String text){
		super(text);
		this.text=text;
		this.setVisible(true);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
		this.setFocusable(false);
		this.setFont(new Font("����",Font.PLAIN,13));
		setOpaque(false);           //����Ϊ͸��
        setContentAreaFilled(false); // ��һ��ǳ���Ҫ, �����໹����ư�ť������. 
	}
	/**
	 * �ӱ߿�Ч�������
	 */
	public void paintComponent(Graphics g){
		int width=this.getWidth();
		int height=this.getHeight();
		Graphics2D g2d = (Graphics2D) g;
		if(state.equals("normal")){
			Shape s=new RoundRectangle2D.Double(
					2.0,2.0,75.0,20.0,10.0,10.0);//������ֵΪ����Բ�ǵĶ�
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);           //ʹ����Բ��
			g2d.setColor(new Color(210,210,210,225));
			g2d.fill(s);
			g2d.setColor(new Color(200,200,200,225));
			g2d.draw(s);
			super.paintComponent(g);
		}else if(state.equals("focused")){
			Shape s=new RoundRectangle2D.Double(
					2.0,2.0,75.0,20.0,10.0,10.0);//������ֵΪ����Բ�ǵĶ�
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);           //ʹ����Բ��
			g2d.setColor(new Color(220,220,220,255));
			g2d.fill(s);
			g2d.setColor(new Color(200,200,200,225));
			g2d.draw(s);
			super.paintComponent(g);
		}else if(state.equals("pressed")){
			//����ͨ��Point2D�������м�Ľ���ɫ����Բ״
			Point2D center=new Point2D.Float(width/2,15);
			float radius = width /2;
			float[] dist = { 0.0f, 1.0f};
			Color[] colors = {new Color(230,230,230,50),
					new Color(200,200,200,255)};
			//RadialGradientPaint ���ṩʹ��Բ�η�����ɫ����ģʽ���ĳһ��״�ķ�ʽ��
			RadialGradientPaint paint = new RadialGradientPaint(
					center,radius,dist,colors);
			g2d.setPaint(paint);
			g2d.fill(new RoundRectangle2D.Double(
					2,2,75,20,10,10));
			//�ӱ߿�
			g2d.setColor(new Color(200,200,200,225));
			g2d.draw(new RoundRectangle2D.Double(
					2,2,75,20,10,10));
			Font deaultFont=getFont();
			g2d.setFont(deaultFont);
			g2d.setColor(Color.BLACK);
			//Rectangle2D ������ͨ��λ�� (x,y) �ͳߴ� (w x h) ����ľ��Ρ�
			Rectangle2D rect = deaultFont.getStringBounds(
					text,g2d.getFontRenderContext());
			LineMetrics lineMetrics = deaultFont.getLineMetrics(
					text,g2d.getFontRenderContext());
			g2d.drawString(text, 
					(float)(width/2-rect.getWidth()/2),
					(float)((height/2)+((lineMetrics.getAscent()+
							lineMetrics.getDescent())/2-lineMetrics.
							getDescent())));
		}else if(state.equals("released")){
			Shape s=new RoundRectangle2D.Double(
					2.0,2.0,75.0,20.0,10.0,10.0);//������ֵΪ����Բ�ǵĶ�
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);           //ʹ����Բ��
			g2d.setColor(new Color(210,210,210,255));
			g2d.fill(s);
			g2d.setColor(new Color(200,200,200,225));
			g2d.draw(s);
			super.paintComponent(g);
		}
		addMouseListener(new MouseAdapter() {  
	        public void mouseEntered(MouseEvent e) { 
	           
	            state = "focused";  
	            repaint();  
	        }  
	        public void mouseExited(MouseEvent e) { 
	         
	            state = "normal";  
	            repaint();  
	        }   
	        public void mousePressed(MouseEvent e) {   
	            
	            state = "pressed";  
	            repaint();  
	        }  
	        public void mouseReleased(MouseEvent e) { 
	           
	            state = "released";  
	            repaint();  
	        }  
	    });
	} 
}
