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
		
		/*Parametric par = new Parametric(new char[] {'x','y','z'}, "x+y+z");
		
		par.intervalueOf(new double[] {0, 0, 0}, new double[] {10, 10, 10});
		
		System.out.print(par);//*/
		
		JFrame frame = Frame();
		
		degree Thetha = new degree(60);
		
		vector a = new vector(50, Thetha);
		
		a.setTail(new coordinate(20, 10));
		
		coordinate b = new coordinate(100, new degree(15));
		
		CartesianPlane R2 = new CartesianPlane(frame.getWidth(), frame.getHeight());
		//R2.drawVector(a);
		//R2.drawCoordinate(b);
		
		/*for (int Theta = 0; Theta<360; Theta+=15){
			
			R2.drawVector(new vector(20, new degree(Theta)));
			
		}//*/
		
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		R2.setVisible(true);
		contentPane.add(R2);
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
					
					frame.setContentPane(contentPane);
					frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });//*/
		
		System.out.print("\n\n");
		
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
