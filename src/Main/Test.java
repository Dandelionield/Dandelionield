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
		
		degree Thetha = new degree(30);
		
		vector v = new vector(20, Thetha);
		
		System.out.print(v+"\n");
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
				
				CartesianPlane R2 = null;

                try {

                    JFrame frame = new JFrame("Plano Cartesiano");
					
					frame.setBounds(200, 200, 1000, 500);
					
					R2 = new CartesianPlane(frame.getWidth(), frame.getHeight());
					//R2.drawVector(v);
					
					JLayeredPane contentPane = new JLayeredPane();
					contentPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
					
					R2.setVisible(true);
					contentPane.add(R2);
					
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setContentPane(contentPane);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });
		
		System.out.print("\n\n");
		
    }
    
}
