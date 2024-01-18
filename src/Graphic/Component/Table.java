package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;

import javax.swing.border.MatteBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellEditor;

import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;

import java.util.EventObject;
import java.util.ArrayList;
import java.util.Vector;

public class Table extends JTable{
	
	private TableHeader Header;
	private ArrayList<TableCell> Cell = new ArrayList<>();
	
	private Object[][] Data;
	private String[] ColumnName;
	private final DefaultTableModel Tablita;
	
	private Color Background;
	private Color Foreground;
	
	private ArrayList<Boolean> CellEditable = new ArrayList<>();
	
	public Table(Object[][] Data, String[] ColumnName){
		
		this.Data = Data;
		this.ColumnName = ColumnName;
		this.Tablita = new DefaultTableModel(this.Data, this.ColumnName);
		this.Background = this.getBackground();
		this.Foreground = this.getForeground();
		
		this.Header = new TableHeader();
		
		for (int i=0; i<this.ColumnName.length; i++){
			
			this.CellEditable.add(true);
			this.Cell.add(new TableCell());
			
		}
		
		this.setModel(Tablita);
		
		Listener();
		
	}
	
	public DefaultTableModel getDefaultTableModel(){
		
		return this.Tablita;
		
	}
	
	public TableHeader getHeader(){
		
		return this.Header;
		
	}
	
	public String getColumnName(int Column){
		
		return this.Tablita.getColumnName(Column);
		
	}
	
	public Vector<Vector> getDataVector(){
		
		return this.Tablita.getDataVector();
		
	}
	
	public Object getValueAt(int Row, int Column){
		
		return this.Tablita.getValueAt(Row, Column);
		
	}
	
	public void setValueAt(Object value, int Row, int Column){
		
		this.Tablita.setValueAt(value, Row, Column);
		
	}
	
	public void setHeader(TableHeader Header){
		
		this.Header = Header;
		
	}
	
	public void setCellEditable(boolean value){
		
		ArrayList<Boolean> bup = new ArrayList<>();
		
		for (int i=0; i<this.CellEditable.size(); i++){
			
			bup.add(value);
			
		}
		
		this.CellEditable = bup;
		
	}
	
	public void setColumnEditable(int Column, boolean value){
		
		this.CellEditable.remove(Column);
		
		this.CellEditable.add(Column, value);
		
	}
	
	public void setHorizontalAlignment(int SwingConstant){
		
		for (int i=0; i<this.Cell.size(); i++){
			
			Cell.get(i).setHorizontalAlignment(SwingConstant);
			
		}
		
	}
	
	public void setHorizontalAlignment(int indice, int SwingConstant){
		
		Cell.get(indice).setHorizontalAlignment(SwingConstant);
		
	}
	
	public void setForeground(int indice, Color Foreground){
		
		Cell.get(indice).setDefaultForeground(Foreground);
		
	}
	
	public void setColumnCount(int ColumnCount){
		
		this.Tablita.setColumnCount(ColumnCount);
		
	}
	
	public void setRowCount​(int RowCount){
		
		this.Tablita.setRowCount(RowCount);
		
	}
	
	public void setColumnIdentifiers​(Object[] newIdentifiers){
		
		this.Tablita.setColumnIdentifiers(newIdentifiers);
		
	}
	
	public void setColumnIdentifiers​(Vector<?> newIdentifiers){
		
		this.Tablita.setColumnIdentifiers(newIdentifiers);
		
	}
	
	public void setDataVector​(Object[][] dataVector, Object[] columnIdentifiers){
		
		this.Tablita.setDataVector(dataVector, columnIdentifiers);
		
	}
	
	public void setDataVector​(Vector<? extends Vector> dataVector, Vector<?> columnIdentifiers){
		
		this.Tablita.setDataVector(dataVector, columnIdentifiers);
		
	}
	
	public void addRow(Object[] rowData){
		
		this.Tablita.addRow(rowData);
		
	}
	
	public void addRow(Vector<?> rowData){
		
		this.Tablita.addRow(rowData);
		
	}
	
	public void addColumn(Object ColumnName){
		
		this.Tablita.addColumn(ColumnName);
		
		adder();
		
	}
	
	public void addColumn(ImageIcon icono){
		
		this.Tablita.addColumn(" ");
		
		TableCell Celda = new TableCell(Cell.get(0).getFocusCellBackground(), Cell.get(0).getFocusCellForeground(), Cell.get(0).getFocusRowBackground(), Cell.get(0).getFocusRowForeground(), Cell.get(0).getFocusColumnBackground(), Cell.get(0).getFocusColumnForeground(), this.getBackground(), this.getForeground(), Cell.get(0).getFont());
		Celda.setImage(icono);
		Celda.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.CellEditable.add(false);
		this.Cell.add(Celda);
		
	}
	
	public void addColumn(Object ColumnName, Object[] ColumnData){
		
		this.Tablita.addColumn(ColumnName, ColumnData);
		
		adder();
		
	}
	
	public void addColumn(Object ColumnName, Vector<?> ColumnData){
		
		this.Tablita.addColumn(ColumnName, ColumnData);
		
		adder();
		
	}
	
	private void adder(){
		
		this.CellEditable.add(true);
		this.Cell.add(new TableCell(Cell.get(0).getFocusCellBackground(), Cell.get(0).getFocusCellForeground(), Cell.get(0).getFocusRowBackground(), Cell.get(0).getFocusRowForeground(), Cell.get(0).getFocusColumnBackground(), Cell.get(0).getFocusColumnForeground(), this.getBackground(), this.getForeground(), Cell.get(0).getFont()));
		
	}
	
	public void insertRow(int indice, Object[] rowData){
		
		this.Tablita.insertRow(indice, rowData);
		
	}
	
	public void insertRow(int indice, Vector<?> rowData){
		
		this.Tablita.insertRow(indice, rowData);
		
	}
	
	public void removeRow(int indice){
		
		this.Tablita.removeRow(indice);
		
	}
	
	public boolean isCellEditable(int Row, int Column){
		
		return this.Tablita.isCellEditable(Row, Column);
		
	}
	
	public void moveRow(int start, int end, int to){
		
		this.Tablita.moveRow(start, end, to);
		
	}
	
	public void newDataAvailable​(TableModelEvent event){
		
		this.Tablita.newDataAvailable(event);
		
	}
	
	public void newRowsAdded​​(TableModelEvent e){
		
		this.Tablita.newRowsAdded(e);
		
	}
	
	public void rowsRemoved​(TableModelEvent event){
		
		this.Tablita.rowsRemoved(event);
		
	}
	
	protected void paintComponent(Graphics g) {
		
        Graphics2D g2 = (Graphics2D) g.create();
        
		innit();
		
		super.paintComponent(g);

        g2.dispose();
		
    }
	
	private void innit(){
		
		int c = 0;
		
		for(int i=0; i<this.getColumnCount(); i++){
			
			this.getColumnModel().getColumn(i).setCellRenderer(this.Cell.get(i));
			
			if (this.Cell.get(i).hasImage()){
				
				this.getColumnModel().getColumn(i).setWidth(30);
				
				c++;
				
			}
			
			if (this.CellEditable.get(i)){
				
				continue;
				
			}
			
			final int indice = i;
			
			this.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()){
				
				public boolean isCellEditable(EventObject e) {

					return false;
					
				}
				
			});
			
		}
		
		int width = (int) Math.ceil((this.getWidth() - 30.00*c) / ((double) (this.getColumnCount() - c)));
		
		if (this.getWidth()!=0 && c!=0){
		
			for(int i=0; i<this.getColumnCount(); i++){
				
				if (this.Cell.get(i).hasImage()){continue;}
				
				this.getColumnModel().getColumn(i).setWidth(width);
				
			}
			
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
					
                    for (int i=0; i<Cell.size(); i++){
					
						Cell.get(i).setFocusedRow(Row);
						Cell.get(i).setFocusedColumn(Column);
						
					}
					
					repaint();
					
                }
				
            }
			
        });
		
	}

}