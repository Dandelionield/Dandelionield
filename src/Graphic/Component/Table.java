package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;

import java.util.EventObject;

public class Table extends JTable{
	
	private Object[][] Data;
	private String[] ColumnName;
	private DefaultTableModel Tablita;
	
	private Color Background;
	private Color Foreground;
	
	private boolean[] CellEditable;
	
	public Table(Object[][] Data, String[] ColumnName){
		
		this.Data = Data;
		this.ColumnName = ColumnName;
		this.Tablita = new DefaultTableModel(this.Data, this.ColumnName);
		this.Background = null;
		this.Foreground = Color.BLACK;
		this.CellEditable = new boolean[this.ColumnName.length];
		
		for (int i=0; i<this.CellEditable.length; i++){
			
			CellEditable[i] = false;
			
		}
		
		this.setModel(Tablita);
		
		innit();
		
	}
	
	private void innit(){
		
		for(int i=0; i<this.getColumnCount(); i++){
			
			final int indice = i;
			
			this.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()){
				
				public boolean isCellEditable(EventObject e) {

					return CellEditable[indice];
					
				}
				
			});
			
		}
		
		for (int i=0; i<this.getColumnCount(); i++) {

			this.getColumnModel().getColumn(i).setCellRenderer(new TableCell());
			
		}
		
		
		JTableHeader Header = this.getTableHeader();
	    Header.setDefaultRenderer(new TableHeader());
	    this.setTableHeader(Header);
		
	}

}