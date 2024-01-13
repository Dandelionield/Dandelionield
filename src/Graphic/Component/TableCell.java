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
	
	public TableCell(){
		
		this.FocusBackground = new Color(46, 54, 59);
		this.FocusForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = new Font("Verdana", Font.PLAIN, 12);
		
	}
	
	public Color getFocusBackground(){
		
		return this.FocusBackground;
		
	}
	
	public Color getFocusForeground(){
		
		return this.FocusForeground;
		
	}
	
	public Font getFont(){
		
		return this.Format;
		
	}
	
	public void setFocusBackground(Color FocusBackground){
		
		this.FocusBackground = FocusBackground;
		
	}
	
	public void setFocusForeground(Color FocusForeground){
		
		this.FocusForeground = FocusForeground;
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocused, int Row, int Column) {
		
		Component cp = super.getTableCellRendererComponent(table, value, isSelected, hasFocused, Row, Column);
		
		boolean bd = false;
		
		if (isSelected){
			
			bd = true;
			
			this.setBackground(Color.GRAY);
			this.setForeground(FocusForeground);
			
		}else{
			
			bd = false;
			
			this.setBackground(DefaultBackground);
			this.setForeground(DefaultForeground);
			
		}
		
		if (hasFocused){
			
            this.setBackground(FocusBackground);
			this.setForeground(FocusForeground);
			
        }else{
			
			if (bd==false){
				
				this.setBackground(DefaultBackground);
				this.setForeground(DefaultForeground);
				
			}
			
		}
		
		this.setFont(Format);
		
		return cp;
		
	}
	
}