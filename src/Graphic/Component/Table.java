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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;

import java.util.EventObject;

public class Table extends JTable{
	
	private TableHeader Header;
	
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
		this.Background = this.getBackground();
		this.Foreground = this.getForeground();
		this.CellEditable = new boolean[this.ColumnName.length];
		
		this.Header = new TableHeader();
		
		for (int i=0; i<this.CellEditable.length; i++){
			
			CellEditable[i] = true;
			
		}
		
		this.setModel(Tablita);
		
		innit();
		Listener();
		
	}
	
	public TableHeader getHeader(){
		
		return this.Header;
		
	}
	
	public void setHeader(TableHeader Header){
		
		this.Header = Header;
		
	}
	
	public void addRow(Object[] rowData){
		
		this.Tablita.addRow(rowData);
		
	}
	
	public void addColumn(Object ColumnName){
		
		this.Tablita.addColumn(ColumnName);
		
	}
	
	public void addColumn(Object ColumnName, Object[] ColumnData){
		
		this.Tablita.addColumn(ColumnName, ColumnData);
		
	}
	
	public void insertRow(int indice, Object[] rowData){
		
		this.Tablita.insertRow(indice, rowData);
		
	}
	
	public void removeRow(int indice){
		
		this.Tablita.removeRow(indice);
		
	}
	
	private void innit(){
		
		for(int i=0; i<this.getColumnCount(); i++){
			
			if (CellEditable[i]){
				
				continue;
				
			}
			
			final int indice = i;
			
			this.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()){
				
				public boolean isCellEditable(EventObject e) {

					return false;
					
				}
				
			});
			
		}
		
		for (int i=0; i<this.getColumnCount(); i++) {

			this.getColumnModel().getColumn(i).setCellRenderer(new TableCell());
			
		}
		
		
		JTableHeader Header = this.getTableHeader();
	    Header.setDefaultRenderer(this.Header);
	    this.setTableHeader(Header);
		
	}
	
	private void Listener(){
		
		final JTable DTable = this;
		
		this.addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent e){
				
                int Row = DTable.rowAtPoint(e.getPoint());
                int Column = DTable.columnAtPoint(e.getPoint());
				
                if (Row!=-1 && Column!=-1){
                    
                    DTable.changeSelection(Row, Column, false, false);
                    DTable.setRowSelectionInterval(Row, Row);
					
                }
            }
			
        });
		
	}

}