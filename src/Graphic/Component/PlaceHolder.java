package Graphic.Component;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.awt.geom.RoundRectangle2D;

import javax.swing.border.EmptyBorder;

import javax.swing.text.Document;

import javax.swing.JTextField;

public class PlaceHolder extends JTextField{
	
	private String PlaceHolderText;
	
	private boolean isFocus = false;
	private int CornerRadio = 0;
	
	Color colorSelect = Color.GRAY;
    Color colorDeselect = Color.BLACK;
    Color colorPlaceholder = Color.GRAY;
	
	public PlaceHolder(){
		
		this.PlaceHolderText = "";
		
		detectFocus();
		
	}
	
	public PlaceHolder(String PlaceHolderText){
		
		this.PlaceHolderText = PlaceHolderText;
		
		detectFocus();
		
	}
	
	public PlaceHolder(String PlaceHolderText, String Text){
		
		super(Text);
		
		this.PlaceHolderText = PlaceHolderText;
		
		detectFocus();
		
	}
	
	public PlaceHolder(int Columns){
		
		super(Columns);
		
		this.PlaceHolderText = "";
		
		detectFocus();
		
	}
	
	public PlaceHolder(String Text, int Columns){
		
		super(Text, Columns);
		
		this.PlaceHolderText = "";
		
		detectFocus();
		
	}
	
	public PlaceHolder(String PlaceHolderText, String Text, int Columns){
		
		super(Text, Columns);
		
		this.PlaceHolderText = PlaceHolderText;
		
		detectFocus();
		
	}
	
	public PlaceHolder(Document doc, String Text, int Columns){
		
		super(doc, Text, Columns);
		
		this.PlaceHolderText = "";
		
		detectFocus();
		
	}
	
	public PlaceHolder(String PlaceHolderText, Document doc, String Text, int Columns){
		
		super(doc, Text, Columns);
		
		this.PlaceHolderText = PlaceHolderText;
		
		detectFocus();
		
	}
	
	public String getPlaceHolderText(){
		
		return this.PlaceHolderText;
		
	}
	
	public void setPlaceHolderText(String PlaceHolderText){
		
		this.PlaceHolderText = PlaceHolderText;
		
	}
	
	public void setCornerRadio(int CornerRadio){
		
		this.CornerRadio = CornerRadio;
		
	}
	
	protected void paintComponent(Graphics g) {
		
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth() - 2, getHeight() - 2, CornerRadio, CornerRadio);
		
		g2.setColor(getBackground());
		g2.fill(roundedRect);
		
        if (getText().isEmpty()){
			
			g2.setColor(isFocus ? getForeground() : colorPlaceholder);
			g2.setFont(getFont());

			g2.drawString(PlaceHolderText, 2, (getHeight() - 1 + getFont().getSize())/2);
			
		}
		
		super.paintComponent(g);

        g2.dispose();
		
    }
	
	private void detectFocus(){
		
        setOpaque(false);
		
		addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e){
				
                isFocus = true;

                repaint();
				
            }

            public void focusLost(FocusEvent e){
				
                isFocus = false;

                repaint();
				
            }
			
        });
		
	}
	
}