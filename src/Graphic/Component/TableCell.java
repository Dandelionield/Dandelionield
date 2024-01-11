package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;

public class TableCell extends DefaultTableCellRenderer{
	
	private Color FocusBackground;
	private Color FocusForeground;
	
	private Color DefaultBackground;
	private Color DefaultForeground;
	
	private final Font Format;
	
	private int Row = -1;
	private int Column = -1;
	
	public TableCell(){
		
		this.FocusBackground = new Color(36, 39, 41);
		this.FocusForeground = Color.WHITE;
		this.DefaultBackground = null;
		this.DefaultForeground = Color.BLACK;
		this.Format = new Font( "Verdana",Font.PLAIN ,12 );
		
		Listener();
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		
		if (selected){
			
            this.setBackground(FocusBackground);
			this.setForeground(FocusForeground);
			
        }else{
			
			this.setBackground(DefaultBackground);
			this.setForeground(DefaultForeground);
			
		}
		
		this.setFont(Format);
		
		return this;
		
	}
	
	private void Listener(){
		
		this.addMouseListener(new MouseAdapter(){

            public void mouseEntered(MouseEvent e){
				
                JTable table = (JTable) getParent();
				
				Point point = e.getPoint();
                Row = table.rowAtPoint(point);
                Column = table.columnAtPoint(point);
				
				System.out.print("Fila: "+Row+" y Columna: "+Column+"\n\n");
				
            }
			
        });
		
	}
	
}