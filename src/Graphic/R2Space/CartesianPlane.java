package Graphic.R2Space;

/*
 *
 * @author Dandelion
 * 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.FlowLayout;
import java.awt.BasicStroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

import Taylor.Math.Mayth;
import Geometry.Euclidean.vector;
import Geometry.Euclidean.coordinate;

public class CartesianPlane extends JPanel{
	
	public int OriginXAxis;
	public int OriginYAxis;
	
	private coordinate Mouse = new coordinate(0, 0);
	
	private double Scala = 1.00;
	private float axisThickness = 1;
	private int lastMouseX;
	private int lastMouseY;
	private int dragStartX;
    private int dragStartY;
	
	private ArrayList<vector> v = new ArrayList<>();
	
	public CartesianPlane(int Width, int Height){
		
		this.OriginXAxis = Width/2;
		this.OriginYAxis = Height/2;
		
		innitComponents(0, 0, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(int X, int Y, int Width, int Height){
		
		this.OriginXAxis = Width/2;
		this.OriginYAxis = Height/2;
		
		innitComponents(X, Y, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(int X, int Y, int Width, int Height, Color Background){
		
		this.OriginXAxis = Width/2;
		this.OriginYAxis = Height/2;
		
		innitComponents(X, Y, Width, Height, Background);
		
	}
	
	public void drawVector(vector v){
		
		this.v.add(v);
		
		this.repaint();
		
	}
	
	public void innitComponents(int X, int Y, int Width, int Height, Color Background){
		
		this.setLayout(new FlowLayout());
		
		this.setPreferredSize(new Dimension(Width, Height));
		this.setBounds(X, Y, Width, Height);
		this.setBackground(Background);
		
		Movements();
		
	}
	
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
    
		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(Render);
		
		g2d.translate(OriginXAxis, OriginYAxis);
		g2d.scale(Scala, Scala);
		
		Font font = new Font("Arial", Font.PLAIN, (int) (20/Scala));
		
		g2d.setColor(Color.BLACK);
        g2d.setFont(font);
		
		g2d.setStroke(new BasicStroke(axisThickness*2));
		
		DrawObjects(g2d);
		
		g2d.setStroke(new BasicStroke(axisThickness));
		
		g2d.drawLine( (int) -(OriginXAxis/Scala), 0, (int) Mayth.abs((OriginXAxis+1)*(getWidth()/Scala)), 0);
		g2d.drawLine(0, (int) Mayth.abs((OriginYAxis+1)*(getHeight()/Scala)), 0, (int) -(OriginYAxis/Scala));
		
		DrawLines(g2d);
		DrawCoordinates(g2d);
		
    }
	
	private void DrawLines(Graphics2D g2d) {
		
		int length = (int) (3.00);
		int delta = (int) (20.00/1);

		for (int x = delta; x <= Mayth.abs((OriginXAxis+1)*(getWidth()/Scala)); x += delta){//x++
			
			g2d.drawLine(x, -length, x, length);
			
		}

		for (int x = -delta; x >= -OriginXAxis/Scala; x -= delta){//x--
		
			g2d.drawLine(x, -length, x, length);
			
		}

		for (int y = delta; y <= Mayth.abs((OriginYAxis+1)*(getHeight()/Scala)); y += delta){//y--
		
			g2d.drawLine(-length, y, length, y);
			
		}

		for (int y = -delta; y >= -OriginYAxis*2/Scala; y -= delta){//y++
		
			g2d.drawLine(-length, y, length, y);
			
		}
		
	}
	
	private void DrawCoordinates(Graphics g2d){
		
		int dotRadius = 2;
		
		g2d.fillOval((int) (Mouse.getX()), (int) (-Mouse.getY()), (int) (dotRadius/Scala) * 2, (int) (dotRadius/Scala) * 2);
		
		if (Mouse.getOctant()== ((byte) 1)){
			
			g2d.drawString(Mouse.toString(), (int) (Mouse.getX() - 10*Mouse.toString().length()/Scala), (int) -(Mouse.getY() - 20.00/Scala));
			
		}else if (Mouse.getOctant()== ((byte) 2)){
			
			g2d.drawString(Mouse.toString(), (int) (Mouse.getX() + 10.00/Scala), (int) -(Mouse.getY() - 20.00/Scala));
			
		}else if (Mouse.getOctant()== ((byte) 3)){
			
			g2d.drawString(Mouse.toString(), (int) (Mouse.getX() + 10.00/Scala), (int) -(Mouse.getY() + 10.00/Scala));
			
		}else{
			
			g2d.drawString(Mouse.toString(), (int) (Mouse.getX() - 10*Mouse.toString().length()/Scala), (int) -(Mouse.getY() + 10.00/Scala));
			
		}
		
	}
	
	private void DrawObjects(Graphics g2d){
		
		if (this.v.size()!=0){
			
			for (vector p : this.v){
				
				p = p.doRedondear(0);
				
				g2d.drawLine((int) p.getTail().getX(),(int) p.getTail().getY(),(int) p.getHead().getX(),(int) -p.getHead().getY());
				
			}
			
		}
		
	}
	
	public void Movements(){
		
		this.addMouseMotionListener(new MouseAdapter(){
			
			public void mouseMoved(MouseEvent e){
				
				MouseCoordinates(e);
				
				repaint();
				
			}
			
			public void mouseDragged(MouseEvent e){
				
                int deltaX = e.getX() - lastMouseX;
				int deltaY = e.getY() - lastMouseY;
				
				OriginXAxis += deltaX;
				OriginYAxis += deltaY;
				
				lastMouseX = e.getX();
				lastMouseY = e.getY();
				
				MouseCoordinates(e);
				
                repaint();
				
            }
			
		});
		
		this.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				
				lastMouseX = e.getX();
                lastMouseY = e.getY();

			}

		});
		
		this.addMouseWheelListener(new MouseWheelListener(){
			
            public void mouseWheelMoved(MouseWheelEvent e){
				
                int Gear = e.getWheelRotation();
				
                if (Gear < 0){// Zoom in
					
                    Scala *= 1.1;
					
                }else{// Zoom out
                    
                    Scala /= 1.1;
					
                }
				
				if (Scala>20){Scala = 20.00;}
				if (Scala<0.5){Scala = 0.5;}
				
				axisThickness = (float) (1.00/Scala);
				
				MouseCoordinates((MouseEvent) e);
				
                repaint();
				
            }
			
        });
		
	}
	
	public void MouseCoordinates(MouseEvent e){
		
		Mouse = new coordinate((e.getX() - OriginXAxis)/Scala, (OriginYAxis - e.getY())/Scala);
		
	}
	
}