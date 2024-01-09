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
import Physics.Measurement.*;
import Graphic.R2Space.*;
import Graphic.Component.*;
import Graphic.User.Interface.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Dimension;

public class Test {

    public static void main(String[] args){
		
		System.out.print("\n\n");
		
		WindowPane.showErrorMessage("zzzz");
		
		Graph();
		
		System.out.print("\n\n");
		
    }//*/
	
	private static void Graph(){
		
		int z = 0;
		
		JFrame frame = Frame();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		PlaceHolder Text = new PlaceHolder("Holaaaaa");
		Text.setBounds(frame.getWidth()/2 - 200, frame.getHeight()/2 - 20, 100, 20);
		
		PlaceHolder Text2 = new PlaceHolder("Holaaaaa");
		Text2.setBounds(frame.getWidth()/2, frame.getHeight()/2 - 20, 100, 20);
		
		panel.setLayout(null);
		panel.setComponentZOrder(Text, z);z++;
		panel.setComponentZOrder(Text2, z);z++;
		
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		contentPane.add(panel, JLayeredPane.PALETTE_LAYER);
		
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
	
	private static void temp(){
		
		temperature K = temperature.getKelvinValueOf(3);
		temperature C = temperature.getCelsiusValueOf(5);
		temperature F = temperature.getFahrenheitValueOf(2);
		
		temperature bup1 = C.doProduct(F);
		temperature bup2 = F.doProduct(C);
		
		System.out.print(F+" * "+C+" = \n\n");
		
		System.out.print(bup1+"\n\n");
		System.out.print(bup2+"\n\n");
		System.out.print(bup1.getToKelvin()+"\n");
		System.out.print(bup2.getToKelvin()+"\n\n");
		System.out.print("Resultados en Kelvin\n\n");
		System.out.print(bup1.toKelvin()+"\n\n");
		System.out.print(bup2.toKelvin()+"\n\n");
		
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
