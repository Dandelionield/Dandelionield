package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Taylor.Arithmetic.*;
import Geometry.Euclidean.*;
import Geometry.Algebra.*;
import Physics.Fundamental.*;
import Graphic.R2Space.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.EventQueue;
import java.awt.Dimension;

public class Test {

    public static void main(String[] args){
		
		System.out.print("\n\n");
		
		Graph();
		
		System.out.print("\n\n");
		
    }
	
	private static void Graph(){
		
		/*Parametric par = new Parametric(new char[] {'x','y','z'}, "x+y+z");
		
		par.intervalueOf(new double[] {0, 0, 0}, new double[] {10, 10, 10});
		
		System.out.print(par);//*/
		
		JFrame frame = Frame();
		
		degree Thetha = new degree(60);
		
		vector a = new vector(50, Thetha);
		
		function f = new function('x', "(2√(1-((x/100)-1)^2))*100");
		function g = new function('x', "(2√(1-(-(x/100)-1)^2))*100");
		function h = new function('x', "(-2.5*(2√(1-(2√((x/100)/2)))))*100");
		function d = new function('x', "(-2.5*(2√(1-(2√(-(x/100)/2)))))*100");
		
		Parametric fp = new Parametric('t', "(16*(Sen(t))^3)*25");
		Parametric gp = new Parametric('t', "(13*Cos(t)-5*Cos(2*t)-Cos(4*t))*25");
		
		Vectorial vec = new Vectorial(fp, gp);
		
		vec.intervalueOf(new double[] {-200}, new double[] {200});
		
		//System.out.print(vec);
		
		CartesianPlane R2 = new CartesianPlane(2, frame.getWidth(), frame.getHeight());
		R2.drawFunction(vec);
		
		/*for (int Theta = 0; Theta<360; Theta+=15){
			
			R2.drawVector(new vector(20, new degree(Theta)));
			
		}//*/
		
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		R2.setVisible(true);
		contentPane.add(R2, JLayeredPane.PALETTE_LAYER);
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
					
					frame.setContentPane(contentPane);
					//frame.setExtendedState(frame.MAXIMIZED_BOTH);
					frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });//*/
		
	}
	
	private static JFrame Frame(){
		
		JFrame frame = new JFrame("Plano Cartesiano");
		
		frame.setBounds(200, 200, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		
		return frame;
		
	}
    
}
