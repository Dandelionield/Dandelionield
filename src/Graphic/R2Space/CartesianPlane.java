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
import java.awt.Polygon;

import java.awt.geom.AffineTransform;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

import Taylor.Math.Mayth;
import Taylor.Arithmetic.function;
import Geometry.Euclidean.Matriz;
import Geometry.Euclidean.vector;
import Geometry.Euclidean.coordinate;
import Geometry.Euclidean.degree;

public class CartesianPlane extends JPanel{
	
	private int OriginXAxis;
	private int OriginYAxis;
	
	private coordinate Mouse = new coordinate(0, 0);
	
	private double Scala;
	private float axisThickness;
	private int lastMouseX;
	private int lastMouseY;
	private int dragStartX;
    private int dragStartY;
	
	private ArrayList<vector> v = new ArrayList<>();
	private ArrayList<coordinate> xy = new ArrayList<>();
	private ArrayList<function> f = new ArrayList<>();
	
	public CartesianPlane(int Width, int Height){
		
		innitComponents(1, 0, 0, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(int X, int Y, int Width, int Height){
		
		innitComponents(1, X, Y, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(int X, int Y, int Width, int Height, Color Background){
		
		innitComponents(1, X, Y, Width, Height, Background);
		
	}
	
	public CartesianPlane(double Scala, int Width, int Height){
		
		innitComponents(Scala, 0, 0, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(double Scala, int X, int Y, int Width, int Height){
		
		innitComponents(Scala, X, Y, Width, Height, Color.WHITE);
		
	}
	
	public CartesianPlane(double Scala, int X, int Y, int Width, int Height, Color Background){
		
		innitComponents(Scala, X, Y, Width, Height, Background);
		
	}
	
	public void drawVector(vector v){
		
		this.v.add(v);
		
		this.repaint();
		
	}
	
	public void drawCoordinate(coordinate xy){
		
		this.xy.add(xy);
		
		this.repaint();
		
	}
	
	public void drawFunction(function f){
		
		this.f.add(f);
		
		this.repaint();
		
	}
	
	public void innitComponents(double Scala, int X, int Y, int Width, int Height, Color Background){
		
		this.setLayout(new FlowLayout());
		
		this.OriginXAxis = Width/2;
		this.OriginYAxis = Height/2;
		this.Scala = Scala;
		this.axisThickness = (float) (1.00/Scala);
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
		
		if (Scala>4){
			
			for (int x = 4; x <= Mayth.abs((OriginXAxis+1)*(getWidth()/Scala)); x += 4){//x++
			
				g2d.drawLine(x, -1, x, 1);
				
			}

			for (int x = -4; x >= -OriginXAxis/Scala; x -= 4){//x--
			
				g2d.drawLine(x, -1, x, 1);
				
			}

			for (int y = 4; y <= Mayth.abs((OriginYAxis+1)*(getHeight()/Scala)); y += 4){//y--
			
				g2d.drawLine(-1, y, 1, y);
				
			}

			for (int y = -4; y >= -OriginYAxis*2/Scala; y -= 4){//y++
			
				g2d.drawLine(-1, y, 1, y);
				
			}
			
		}
		
	}
	
	private void DrawCoordinates(Graphics g2d){
		
		g2d.fillOval((int) (Mouse.getX()), (int) (-Mouse.getY()), (int) Mayth.Redondear((2.00/Scala) * 2, 0), (int) Mayth.Redondear((2.00/Scala) * 2, 0));
		
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
				
				g2d.drawLine((int) p.getTail().getX(),(int) -p.getTail().getY(),(int) p.getHead().getX(),(int) -p.getHead().getY());
				drawArrow(g2d, p);
				
			}
			
		}
		
		if (this.f.size()!=0){
			
			int x1 = 0;
			int x2 = 0;
			int y1 = 0;
			int y2 = 0;
			
			for (function p : this.f){
				
				if (p.getParsers().size()==0){
					
					p.intervalueOf(-10, 10);
					
				}
				
				for (int i=p.getParsers().size()-2; i>=0; i--){
					
					try{
						
						x1 = (int) p.getInput(i);
						y1 = (int) -p.getOutput(i).get().setScale(15, RoundingMode.HALF_UP).doubleValue();
						x2 = (int) p.getInput(i+1);
						y2 = (int) -p.getOutput(i+1).get().setScale(15, RoundingMode.HALF_UP).doubleValue();
						
					}catch(Exception e){
						
						continue;
						
					}
					
					g2d.drawLine(x1, y1, x2, y2);
					
				}
				
			}
			
		}
		
		if (this.xy.size()!=0){
			
			double dotRadius = 2;
			
			if (Scala<4){
				
				dotRadius = 2;
				
			}else if (Scala<6){
				
				dotRadius = 3;
				
			}else if (Scala<7.4){
				
				dotRadius = 3.5;
				
			}else if (Scala<9.8){
				
				dotRadius = 4;
				
			}else if (Scala<16.5){
				
				dotRadius = 4.4;
				
			}else{
				
				dotRadius = 5;
				
			}
			
			for (coordinate p : this.xy){
				
				p = p.doRedondear(0);
				
				g2d.fillOval((int) p.getX(), (int) (-p.getY()), (int) Mayth.Redondear((dotRadius/Scala)*2.00, 0), (int) Mayth.Redondear((dotRadius/Scala) * 2.00, 0));
		
				if (p.getOctant()== ((byte) 1)){
					
					g2d.drawString(p.toString(), (int) (p.getX() - 10*p.toString().length()/Scala), (int) -(p.getY() - 20.00/Scala));
					
				}else if (p.getOctant()== ((byte) 2)){
					
					g2d.drawString(p.toString(), (int) (p.getX() + 10.00/Scala), (int) -(p.getY() - 20.00/Scala));
					
				}else if (p.getOctant()== ((byte) 3)){
					
					g2d.drawString(p.toString(), (int) (p.getX() + 10.00/Scala), (int) -(p.getY() + 10.00/Scala));
					
				}else{
					
					g2d.drawString(p.toString(), (int) (p.getX() - 8*p.toString().length()/Scala), (int) -(p.getY() + 8.00/Scala));
					
				}
				
			}
			
		}
		
	}
	
	private void drawArrow(Graphics g2d, vector v){
		
		double ArrowScala = 2.00;
		
		int x1 = (int) (v.getHead().getX()-ArrowScala*v.getDirection().doResta(new degree(45)).getCos());
		int y1 = (int) (-v.getHead().getY()+ArrowScala*v.getDirection().doResta(new degree(45)).getSen());
		int x2 = (int) v.getHead().getX();
		int y2 = (int) -v.getHead().getY();
		int x3 = (int) (v.getHead().getX()-ArrowScala*v.getDirection().doSuma(new degree(45)).getCos());
		int y3 = (int) (-v.getHead().getY()+ArrowScala*v.getDirection().doSuma(new degree(45)).getSen());
		
		Polygon ArrowHead = new Polygon();
		
		ArrowHead.addPoint(x1, y1);
		ArrowHead.addPoint(x2, y2);
		ArrowHead.addPoint(x3, y3);
		
		g2d.fillPolygon(ArrowHead);//*/
		
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
	
	/*if (v.getOctant()==1){
			
			x2 = (int) (v.getHead().getX()+1);
			y2 = (int) -(v.getHead().getY()+1);
			
		}else if (v.getOctant()==2){
			
			x1++;
			x2 = (int) (v.getHead().getX()+2);
			y2 = (int) -v.getHead().getY();
			x3++;
			
		}else if (v.getOctant()==3){
			
			x2 = (int) (v.getHead().getX()-1);
			y2 = (int) -(v.getHead().getY()-1);
			
		}else{
			
			x1--;
			x2 = (int) (v.getHead().getX()-2);
			y2 = (int) -(v.getHead().getY());
			x3--;
			
		}//*/
	
}