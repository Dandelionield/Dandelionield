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

public class Test {

    public static void main(String[] args){
		
		System.out.print("\n\n");
		
		Graph();
		
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
		
		JViewport View = new JViewport();
		View.setView(Tabla);
		View.setBackground(Tabla.getBackground());
		
		JScrollPane Scroll = new JScrollPane();
		Scroll.setViewport(View);
		Scroll.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(Tabla.getBackground()));
		Scroll.setBackground(Tabla.getBackground());
		
		Scroll.setBounds(20, 20, 900, 300);
		
		panel.setLayout(null);
		panel.setComponentZOrder(Scroll, z);z++;
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
	
	private static JFrame Frame(){
		
		JFrame frame = new JFrame("Plano Cartesiano");
		
		frame.setBounds(200, 200, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		
		return frame;
		
	}
    
}
