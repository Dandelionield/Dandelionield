package Graphic.User.Interface;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.geom.RoundRectangle2D;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

import Graphic.Component.ComponentBuilder;

public class WindowPane{
	
	private static final ComponentBuilder cp = new ComponentBuilder(Color.BLACK);
	private int X = 0, Y = 0;
	
	private WindowPane(){}
	
	public static void showMessage(Component parentComponent, Object Message){
		
		int Width = 500;
		int Height = 200;
		int z = 0;
		Font Format = new Font("Clarendon Blk BT", Font.BOLD, 15);
		WindowPane wp = new WindowPane();
		
		cp.setForeground(Color.WHITE);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, 60, 60}, new Color(241, 251, 253));
		JLabel Title = cp.buildLabel("    Output", new int[] {0, 0, Width, Height/5}, SwingConstants.TOP, SwingConstants.LEFT, Format);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 40, 30}, new int [] {0, 0, 40, 30, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, Format, cp.getBackground(), true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height, 60, 60));
		
		Close.addActionListener(new ActionListener() {

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
	
}