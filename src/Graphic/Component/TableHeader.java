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
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.table.TableCellRenderer;

public class TableHeader implements TableCellRenderer{
	
	private final Color Background;
	private final Color Foreground;
	private final Font Format;
	
	private final String Title;
	
	public TableHeader(){
		
		this.Background = Color.BLACK;
		this.Foreground = Color.WHITE;
		this.Format = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.Title = "Data Table";
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JComponent Header = Header = new JLabel((String) value);
		
		((JLabel) Header).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) Header).setSize(30, Header.getWidth());   
        ((JLabel) Header).setPreferredSize(new Dimension(6, Header.getWidth()));	         
   
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        Header.setOpaque(true);
        Header.setBackground(this.Background);
        Header.setToolTipText(Title);
        Header.setForeground(this.Foreground);
		Header.setFont(this.Format);
        
        return Header;
    }
	
}