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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.border.LineBorder;
import javax.swing.border.AbstractBorder;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.Box;

import Graphic.Component.ComponentBuilder;

public class WindowPane{
	
	private int X = 0, Y = 0;
	private Object Input = "";
	
	private WindowPane(){}
	
	public static void showOutputMessage(Object Message){
		
		showOutputMessage(null, Message, "Output", null);
		
	}
	
	public static void showOutputMessage(Object Message, Object Title){
		
		showOutputMessage(null, Message, Title, null);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message){
		
		showOutputMessage(parentComponent, Message, "Output", null);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar){
		
		showOutputMessage(parentComponent, Message, Titlebar, null);
		
	}
	
	public static Object getInputMessage(Object Message){
		
		return getInputMessage(null, Message, "Input", null);
		
	}
	
	public static Object getInputMessage(Object Message, Object Title){
		
		return getInputMessage(null, Message, Title, null);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message){
		
		return getInputMessage(parentComponent, Message, "Input", null);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title){
		
		return getInputMessage(parentComponent, Message, Title, null);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, ImageIcon Icono){
		
		final ComponentBuilder cp = new ComponentBuilder();
		final WindowPane wp = new WindowPane();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		final int Radio = 20;
		final Font Format = new Font("Clarendon Blk BT", Font.BOLD, 15);
		
		cp.setForeground(Color.BLACK);
		cp.setBackground(Color.WHITE);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), new Font("Clarendon Blk BT", Font.PLAIN, 13), null, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>1036){
			
			Height = 1036;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<936){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(Color.WHITE);
		cp.setBackground(Color.BLACK);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, Radio, Radio}, Color.WHITE);
		contentPane.setBorder(wp.getAbstractBorder(Radio, Color.BLACK));
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, Format);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, Format, cp.getBackground(), true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
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
		
		wp.addListeners(Title, Close, Window, wp);
		
		Window.add(contentPane);
		Window.setVisible(true);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Titlebar, ImageIcon Icono){
		
		final ComponentBuilder cp = new ComponentBuilder();
		final WindowPane wp = new WindowPane();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		final int Radio = 20;
		final Font Format = new Font("Clarendon Blk BT", Font.BOLD, 15);
		
		cp.setForeground(Color.BLACK);
		cp.setBackground(Color.WHITE);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), new Font("Clarendon Blk BT", Font.PLAIN, 13), null, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>936){
			
			Height = 936;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<836){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Height+= 50;
		
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(Color.WHITE);
		cp.setBackground(Color.BLACK);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, Radio, Radio}, Color.WHITE);
		contentPane.setBorder(wp.getAbstractBorder(Radio, Color.BLACK));
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, Format);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, Format, cp.getBackground(), true, true);
		
		cp.setForeground(Color.BLACK);
		cp.setBackground(Color.WHITE);
		
		JTextField Input = cp.buildTextField("", new int[] {Width/15, Height-50, Scroll.getWidth(), 20}, SwingConstants.LEFT, new Font("Clarendon Blk BT", Font.PLAIN, 14), Color.BLACK, Color.BLACK, true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
		contentPane.setComponentZOrder(Input, z);z++;
		contentPane.setComponentZOrder(Scroll, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		Input.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, Radio, Radio));
		
		Input.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					wp.Input = Input.getText();
					Window.dispose();

				}

            }
			
		});
		
		wp.addListeners(Title, Close, Window, wp);
		
		Window.add(contentPane);
		Window.setVisible(true);
		
		return wp.Input;
		
	}
	
	private AbstractBorder getAbstractBorder(int Radio, Color color){
		
		return new AbstractBorder(){
            
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
				
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(4));
                g2d.drawRoundRect(x+1, y, (width-3), (height-2), Radio, Radio);

                g2d.dispose();
				
            }
			
        };
		
	}
	
	private void addListeners(JLabel Title, JButton Close, JDialog Window, WindowPane wp){
		
		Close.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				wp.Input = null;
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
		
	}
	
	private JScrollPane Scroll(int Sum, int PanelWidth, int PanelHeight){
		
		JScrollPane Scroll = new JScrollPane();
		
		Scroll.setBounds(PanelWidth/15 + Sum, 70, (int) (PanelWidth*0.85)-Sum, (int) PanelHeight-70);
		Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		return Scroll;
		
	}
	
}