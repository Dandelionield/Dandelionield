package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.KeyboardFocusManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import javax.swing.table.DefaultTableCellRenderer;

public class TableCell extends DefaultTableCellRenderer{
	
	private Color FocusBackground;
	private Color FocusForeground;
	
	private Color DefaultBackground;
	private Color DefaultForeground;
	
	private Font Format;
	
	private int SwingConstant;
	private int focusedRow = -1;
	private int focusedColumn = -1;
	
	public TableCell(){
		
		this.FocusBackground = new Color(46, 54, 59);
		this.FocusForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = new Font("Verdana", Font.PLAIN, 12);
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground){
		
		this.FocusBackground = FocusBackground;
		this.FocusForeground = FocusForeground;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = new Font("Verdana", Font.PLAIN, 12);
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground, Font Format){
		
		this.FocusBackground = FocusBackground;
		this.FocusForeground = FocusForeground;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground, Font Format, int SwingConstant){
		
		this.FocusBackground = FocusBackground;
		this.FocusForeground = FocusForeground;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground, Color DefaultBackground, Color DefaultForeground, Font Format){
		
		this.FocusBackground = FocusBackground;
		this.FocusForeground = FocusForeground;
		this.setBackground(DefaultBackground);
		this.setForeground(DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Font Format, int SwingConstant){
		
		this.FocusBackground = new Color(46, 54, 59);
		this.FocusForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground, Color DefaultBackground, Color DefaultForeground, Font Format, int SwingConstant){
		
		this.FocusBackground = FocusBackground;
		this.FocusForeground = FocusForeground;
		this.setBackground(DefaultBackground);
		this.setForeground(DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
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
	
	public int getHorizontalAlignment(){
		
		return this.SwingConstant;
		
	}
	
	public void setFocusBackground(Color FocusBackground){
		
		this.FocusBackground = FocusBackground;
		
	}
	
	public void setFocusForeground(Color FocusForeground){
		
		this.FocusForeground = FocusForeground;
		
	}
	
	public void setDefaultForeground(Color DefaultForeground){
		
		this.DefaultForeground = DefaultForeground;
		
	}
	
	public void setHorizontalAlignment(int SwingConstant){
		
		this.SwingConstant = SwingConstant;
		
	}
	
	public void setFocusedRow(int Row){
		
		this.focusedRow = Row;
		
	}
	
	public void setFocusedColumn(int Column){
		
		this.focusedColumn = Column;
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocused, int Row, int Column) {
		
		Component cp = super.getTableCellRendererComponent(table, value, isSelected, hasFocused, Row, Column);
		
		boolean bd = false;
		
		if (Row==focusedRow){
			
			bd = true;
			
			this.setBackground(Color.GRAY);
			this.setForeground(FocusForeground);
			
		}else{
			
			bd = false;
			
			this.setBackground(DefaultBackground);
			this.setForeground(DefaultForeground);
			
		}
		
		if (Row==focusedRow && Column==focusedColumn){
			
            this.setBackground(FocusBackground);
			this.setForeground(FocusForeground);
			
        }else{
			
			if (bd==false){
				
				this.setBackground(DefaultBackground);
				this.setForeground(DefaultForeground);
				
			}
			
		}
		
		this.setFont(Format);
		this.setHorizontalAlignment(SwingConstant);
		
		return cp;
		
	}
	
}