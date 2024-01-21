package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

public class ScrollBar extends BasicScrollBarUI {
	
	private Color ButtonColor;
	
	public ScrollBar(){
		
		this.ButtonColor = Color.WHITE;
		
	}
	
	public ScrollBar(Color ButtonColor){
		
		this.ButtonColor = ButtonColor;
		
	}
	
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
		
        Graphics2D g2 = (Graphics2D) g;
		
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new Color(150, 150, 150));
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
		
    }//*/

    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds){
		
        Graphics2D g2 = (Graphics2D) g;
		
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new Color(220, 220, 220));
        g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 10, 10);
		
    }//*/
	
	protected JButton createIncreaseButton(int orientation){
		
		JButton Arrow = new JButton(new Icon(){
            
            public void paintIcon(Component c, Graphics g, int x, int y){
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHints(Render);
			
                g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(x, y, x + 5, y + 5);
				g2d.drawLine(x + 10, y, x + 5, y + 5);
                g2d.dispose();
			
            }

            public int getIconWidth(){
		
               return 10;
				
            }

            public int getIconHeight(){
			
				return 5;
				
            }
			
        });
		
		Arrow.setBackground(ButtonColor);
		Arrow.setFocusable(false);
		Arrow.setBorderPainted(false);
		
		return Arrow;
	
    }
	
	protected JButton createDecreaseButton(int orientation){
		
		JButton Arrow = new JButton(new Icon(){
            
            public void paintIcon(Component c, Graphics g, int x, int y){
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHints(Render);
			
                g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(x, y + 3, x + 5, y - 2);
				g2d.drawLine(x + 10, y + 3, x + 5, y - 2);
                g2d.dispose();
			
            }

            public int getIconWidth(){
		
               return 10;
				
            }

            public int getIconHeight(){
			
				return 5;
				
            }
			
        });
		
		Arrow.setBackground(ButtonColor);
		Arrow.setFocusable(false);
		Arrow.setBorderPainted(false);
		
		return Arrow;
	
    }
	
}
	