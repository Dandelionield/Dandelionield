package Graphic.User.Interface;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.geom.RoundRectangle2D;

import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;
import javax.swing.border.AbstractBorder;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

import Graphic.Component.ComponentBuilder;
import javax.swing.border.AbstractBorder;

public class WindowPane{
	
	private static final ComponentBuilder cp = new ComponentBuilder(Color.BLACK);
	private int X = 0, Y = 0;
	
	private WindowPane(){}
	
	public static void showMessage(Component parentComponent, Object Message){
		
		WindowPane wp = new WindowPane();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Radio = 20;
		Font Format = new Font("Clarendon Blk BT", Font.BOLD, 15);
		
		cp.setForeground(Color.BLACK);
		cp.setBackground(Color.WHITE);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), new Font("Clarendon Blk BT", Font.PLAIN, 13), null, false, true);//new int[] {0, 0, Scroll.getWidth(), Scroll.getHeight()}
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>1036){
			
			Height = 1036;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Width, Height);
		
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<936){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Width, Height);
			
		}
		
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(Color.WHITE);
		cp.setBackground(Color.BLACK);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, Radio, Radio}, Color.WHITE);//new Color(241, 251, 253));
		contentPane.setBorder(new AbstractBorder(){
            
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
				
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(4));
                g2d.drawRoundRect(x+1, y, (width-3), (height-2), Radio, Radio);

                g2d.dispose();
				
            }
			
        });
		
		JLabel Title = cp.buildLabel("    Output", new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, Format);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, Format, cp.getBackground(), true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(Scroll, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, Radio, Radio));
		
		Close.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				Window.dispose();
				
			}
			
		});
		
		Title.addMouseListener(new MouseAdapter(){
			
            public void mousePressed(MouseEvent e){
				
                wp.X = e.getX();
                wp.Y = e.getY();
				
            }
			
        });
		
		Title.addMouseMotionListener(new MouseAdapter(){
			
            public void mouseDragged(MouseEvent e){
                
                int x = e.getXOnScreen() - wp.X;
                int y = e.getYOnScreen() - wp.Y;

                Window.setLocation(x, y);
				
            }
			
        });
		
		Window.add(contentPane);
		Window.setVisible(true);
		
	}
	
	private JScrollPane Scroll(int PanelWidth, int PanelHeight){
		
		JScrollPane Scroll = new JScrollPane();
		
		Scroll.setBounds(PanelWidth/15, 70, (int) (PanelWidth*0.85), (int) PanelHeight-70);
		Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		return Scroll;
		
	}
	
}