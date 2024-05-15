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

import System.console;
import System.Numeric.*;
import System.Games.*;

import Algorithm.Nodes.*;
import Algorithm.LinkedArrays.*;
import Algorithm.Trees.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.border.MatteBorder;

import javax.swing.JViewport;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;

import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;
import java.util.Scanner;

public class Test {

	public static void main(String[] args){
		
		//console.clean();
		
		System.out.print("\n\n");
		
		/*Digit[] n = new Digit[] {
			
			new Digit(10), new Digit(40), new Digit(35), new Digit(25), 
			new Digit(60), new Digit(30), new Digit(80), new Digit(50), 
			new Digit(27), new Digit(28), new Digit(38), new Digit(55)
		};
		
		AVLTree<Digit> Tree = new AVLTree<>();
		
		for (Digit p : n){
			
			Tree.add(p);
			
		}
		
		System.out.println(Tree+"\n");
		
		Tree.remove(n[4]);
		Tree.remove(n[11]);
		Tree.remove(n[7]);
		Tree.remove(n[5]);
		Tree.remove(n[8]);
		Tree.remove(n[1]);
		
		System.out.print(Tree);//*/
		
		Digit[] n = new Digit[] {
			
			new Digit(19), new Digit(15), new Digit(20), new Digit(14), 
			new Digit(17), new Digit(30), new Digit(13), new Digit(16), 
			new Digit(18), new Digit(25), new Digit(26)
		};
		
		BinarySearchTree<Digit> Tree = new BinarySearchTree<>();
		
		for (Digit p : n){
			
			Tree.add(p);
			
		}
		
		System.out.println("\n"+Tree+"\n");
		
		System.out.println("Pre: "+Tree.preOrder());
		System.out.println("In: "+Tree.inOrder());
		System.out.println("Post: "+Tree.postOrder());
		
		System.out.print("\n\n");
		
    }//*/
	
	private static void Graph(){
		
		int z = 0;
		
		final Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54), new Color(60, 133, 100)};
		
		JFrame frame = Frame();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		JButton Boton = new JButton("Columna");
		Boton.setBounds(20, 340, 100, 40);
		
		JButton Boton1 = new JButton("Columna 2");
		Boton1.setBounds(160, 340, 100, 40);

		Table Tabla = new Table(new Object[][] {
			
			{"CP1", "Carne", 2, "1.500 $", "3.000 $"}, 
			{"CP2", "Carnita", 3, "2.000 $", "6.000 $"},  
			{"CP3", "Pollo", 2.5, "2.500 $", "6.250 $"}, 
			{"CP4", "Pollito", 0.5, "3.500 $", "1.750 $"}, 
			{"CP5", "Posho", 1, "4.000 $", "4.000 $"}, 
			{"CP6", "Poshito", 2, "4.500 $", "9.000 $"}, 
			{"CP7", "Cerdo", 3, "5.000 $", "15.000 $"}, 
			{"CP8", "Cerdito", 0.5, "5.500 $", "2.750$"}, 
			{"CP9", "Pescado", 1, "6.000 $", "6.000 $"}, 
			{"CP10", "Pescadito", 1.5, "6.500 $", "9.750 $"}, 
			{"CP11", "Cabra", 2, "7.000 $", "14.000 $"}, 
			{"CP12", "Cabrita", 3.5, "7.500 $", "26.250 $"}, 
			{"CP13", "Cabrito", 0.5, "8.000 $", "4.000 $"}, 
			{"CP1", "Carne", 2, "1.500 $", "3.000 $"}, 
			{"CP2", "Carnita", 3, "2.000 $", "6.000 $"},  
			{"CP3", "Pollo", 2.5, "2.500 $", "6.250 $"}, 
			{"CP4", "Pollito", 0.5, "3.500 $", "1.750 $"}, 
			{"CP5", "Posho", 1, "4.000 $", "4.000 $"}, 
			{"CP6", "Poshito", 2, "4.500 $", "9.000 $"}, 
			{"CP7", "Cerdo", 3, "5.000 $", "15.000 $"}, 
			{"CP8", "Cerdito", 0.5, "5.500 $", "2.750$"}, 
			{"CP9", "Pescado", 1, "6.000 $", "6.000 $"}, 
			{"CP10", "Pescadito", 1.5, "6.500 $", "9.750 $"}, 
			{"CP11", "Cabra", 2, "7.000 $", "14.000 $"}, 
			{"CP12", "Cabrita", 3.5, "7.500 $", "26.250 $"}, 
			{"CP13", "Cabrito", 0.5, "8.000 $", "4.000 $"}, 
			
		}, new String[] {"ID", "Nombre", "Cantidad", "Precio", "Total"}, false);
		
		Tabla.setColumnEditable(2, true);
		
		Tabla.getColumn(3).setDefaultForeground(Fondo[2]);
		Tabla.getColumn(4).setDefaultForeground(Fondo[2]);
		
		Tabla.getColumn(3).setFocusCellBackground(Fondo[2]);
		Tabla.getColumn(4).setFocusCellBackground(Fondo[2]);
		
		for(int i=0; i<Tabla.getColumnCount(); i++){
			
			//Tabla.getColumn(i).setFocusColumnBackground(Color.GRAY);
			//Tabla.getColumn(i).setFocusColumnForeground(Color.WHITE);
			
			Tabla.getColumn(i).setHorizontalAlignment(SwingConstants.CENTER);
			
		}//*/
		
		Tabla.getHeader().setBackground(Fondo[0]);
		Tabla.getHeader().setForeground(Color.BLACK);
		
		Tabla.setBackground(Fondo[0]);
		
		Tabla.getHeader().showInnerBorder(true);
		
		Tabla.setShowHorizontalLines(false);
		Tabla.setShowVerticalLines(false);
		
		Tabla.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				/*System.out.print(
				
					"Fila Seleccionada: "+Tabla.getSelectedRow()+"\n"+
					"Columna Seleccionada: "+Tabla.getSelectedColumn()+"\n"+
					"Fila Focuseada: "+Tabla.getFocusedRow()+"\n"+
					"Columna Focuseada: "+Tabla.getFocusedColumn()+"\n"
					
				);//*/

			}

		});
		
		Boton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				//Tabla.removeRow(1);
				
				Vector<Object> v = new Vector<>();
				
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				v.add(3);
				
				Tabla.addColumn("Nueva Fila", v);//*/
				
			}
			
		});
		
		Boton1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				Tabla.addColumn(new ImageIcon(Test.class.getResource("/WindowPane_Textures/INFORMATION_MESSAGE.png")));
				Tabla.addColumn(new ImageIcon(Test.class.getResource("/WindowPane_Textures/ERROR_MESSAGE.png")));//*/
				
			}
			
		});
		
		ComponentBuilder cp = new ComponentBuilder();
		
		panel.setLayout(null);
		panel.setComponentZOrder(cp.buildJScrollPane(Tabla, new int[] {20, 20, 900, 300}), z);z++;
		panel.setComponentZOrder(Boton, z);z++;
		panel.setComponentZOrder(Boton1, z);z++;
		
		panel.setBackground(Fondo[0]);
		
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		contentPane.add(panel, JLayeredPane.PALETTE_LAYER);
		
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
	
	private static void vec(){
		
		coordinate A = new coordinate(3, -2, -2);
		coordinate B = new coordinate(6, 3, 1);
		coordinate C = new coordinate(0, 2, 5);
		
		vector a = new vector(C, A).doRedondear(3);
		vector b = new vector(A, B).doRedondear(3);
		
		WindowPane.showOutputMessage(
		
			" Datos vector a: "
			+"\n Magnitud: "+a.getMagnitude()
			+"\n Componente x: "+a.getComponentX()
			+"\n Componente y: "+a.getComponentY()
			+"\n Componente z: "+a.getComponentZ()
			+"\n Direccion   : "+a.getDirection()
			+"\n Direccion x : "+a.getDirectionX()
			+"\n Direccion y : "+a.getDirectionY()
			+"\n Direccion z : "+a.getDirectionZ()
			+"\n Octante: "+a.getOctant()
			+"\n Unidad: "+a.getUnity()+"\n\n"
			
			+" Datos vector b: "
			+"\n Magnitud: "+b.getMagnitude()
			+"\n Componente x: "+b.getComponentX()
			+"\n Componente y: "+b.getComponentY()
			+"\n Componente z: "+b.getComponentZ()
			+"\n Direccion   : "+b.getDirection()
			+"\n Direccion x : "+b.getDirectionX()
			+"\n Direccion y : "+b.getDirectionY()
			+"\n Direccion z : "+b.getDirectionZ()
			+"\n Octante: "+b.getOctant()
			+"\n Unidad: "+b.getUnity()+"\n\n"
			
			+" Angulo entre el vector a y b: "+a.doBetweenTwo(b).doRedondear(3)
			
		);
		
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
