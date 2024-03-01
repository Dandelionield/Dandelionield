package Graphic.User.Interface;

/*
 *
 * @author Dandelion
 * 
 */

import java.awt.geom.RoundRectangle2D;

import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import javax.swing.text.Caret;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.Box;

import Graphic.Component.ComponentBuilder;
import Graphic.Component.ScrollBar;

public class WindowPane{
	
	private int X = 0, Y = 0;
	private Object Input = "";
	
	private Color Background;
	private Color BorderBackground;
	
	private Color Foreground;
	private Color BorderForeground;
	
	private Font Format;
	private Font BorderFormat;
	
	private int BorderLength;
	private int CornerRadio;
	
	/*
	
		All Images used here were made by https://www.flaticon.es/autores/riajulislam
		From the Image Package https://www.flaticon.es/packs/essential-actions-3
		
	*/
	
	public static final ImageIcon[] TYPE_MESSAGE = new ImageIcon[] {
		
		new ImageIcon(WindowPane.class.getResource("/WindowPane_Textures/INFORMATION_MESSAGE.png")), 
		new ImageIcon(WindowPane.class.getResource("/WindowPane_Textures/QUESTION_MESSAGE.png")), 
		new ImageIcon(WindowPane.class.getResource("/WindowPane_Textures/ERROR_MESSAGE.png")), 
		new ImageIcon(WindowPane.class.getResource("/WindowPane_Textures/WARNING_MESSAGE.png"))
		
	};
	
	public static final Color INFORMATION_COLOR = new Color(66, 224, 245);
	public static final Color QUESTION_COLOR = new Color(66, 135, 245);
	public static final Color ERROR_COLOR = Color.RED;
	public static final Color WARNING_COLOR = Color.YELLOW;
	
	public static final int INFORMATION_MESSAGE = 0;
	public static final int QUESTION_MESSAGE = 1;
	public static final int ERROR_MESSAGE = 2;
	public static final int WARNING_MESSAGE = 3;
	
	public WindowPane(){
		
		this.Background = Color.WHITE;
		this.BorderBackground = Color.BLACK;
		this.Foreground = Color.BLACK;
		this.BorderForeground = Color.WHITE;
		this.Format = new Font("Helvetica", Font.PLAIN, 14);
		this.BorderFormat = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(Color Background, Color BorderBackground, Color Foreground, Color BorderForeground, Font Format, Font BorderFormat){
		
		this.Background = Background;
		this.BorderBackground = BorderBackground;
		this.Foreground = Foreground;
		this.BorderForeground = BorderForeground;
		this.Format = Format;
		this.BorderFormat = BorderFormat;
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(Color Background, Color BorderBackground, Color Foreground, Color BorderForeground, Font Format, Font BorderFormat, int BorderLength, int CornerRadio){
		
		this.Background = Background;
		this.BorderBackground = BorderBackground;
		this.Foreground = Foreground;
		this.BorderForeground = BorderForeground;
		this.Format = Format;
		this.BorderFormat = BorderFormat;
		this.BorderLength = BorderLength;
		this.CornerRadio = CornerRadio;
		
	}
	
	public WindowPane(Color Background, Color BorderBackground, Color Foreground, Color BorderForeground){
		
		this.Background = Background;
		this.BorderBackground = BorderBackground;
		this.Foreground = Foreground;
		this.BorderForeground = BorderForeground;
		this.Format = new Font("Helvetica", Font.PLAIN, 14);
		this.BorderFormat = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(Color BorderBackground){
		
		this.Background = Color.WHITE;
		this.BorderBackground = BorderBackground;
		this.Foreground = Color.BLACK;
		this.BorderForeground = Color.WHITE;
		this.Format = new Font("Helvetica", Font.PLAIN, 14);
		this.BorderFormat = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(Color BorderBackground, Color BorderForeground){
		
		this.Background = Color.WHITE;
		this.BorderBackground = BorderBackground;
		this.Foreground = Color.BLACK;
		this.BorderForeground = BorderForeground;
		this.Format = new Font("Helvetica", Font.PLAIN, 14);
		this.BorderFormat = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(Font Format, Font BorderFormat){
		
		this.Background = Color.WHITE;
		this.BorderBackground = Color.BLACK;
		this.Foreground = Color.BLACK;
		this.BorderForeground = Color.WHITE;
		this.Format = Format;
		this.BorderFormat = BorderFormat;
		this.BorderLength = 4;
		this.CornerRadio = 20;
		
	}
	
	public WindowPane(int BorderLength, int CornerRadio){
		
		this.Background = Color.WHITE;
		this.BorderBackground = Color.BLACK;
		this.Foreground = Color.BLACK;
		this.BorderForeground = Color.WHITE;
		this.Format = new Font("Helvetica", Font.PLAIN, 14);
		this.BorderFormat = new Font("Clarendon Blk BT", Font.BOLD, 15);
		this.BorderLength = BorderLength;
		this.CornerRadio = CornerRadio;
		
	}
	
	public Color getBackground(){
		
		return this.Background;
		
	}
	
	public Color getBorderBackground(){
		
		return this.BorderBackground;
		
	}
	
	public Color getForeground(){
		
		return this.Foreground;
		
	}
	
	public Color getBorderForeground(){
		
		return this.BorderForeground;
		
	}
	
	public Font getFormat(){
		
		return this.Format;
		
	}
	
	public Font getBorderFormat(){
		
		return this.BorderFormat;
		
	}
	
	public int getBorderLength(){
		
		return this.BorderLength;
		
	}
	
	public int getCornerRadio(){
		
		return this.CornerRadio;
		
	}
	
	public void setBackground(Color Background){
		
		this.Background = Background;
		
	}
	
	public void setBorderBackground(Color BorderBackground){
		
		this.BorderBackground = BorderBackground;
		
	}
	
	public void setForeground(Color Foreground){
		
		this.Foreground = Foreground;
		
	}
	
	public void setBorderForeground(Color BorderForeground){
		
		this.BorderForeground = BorderForeground;
		
	}
	
	public void setFormat(Font Format){
		
		this.Format = Format;
		
	}
	
	public void setBorderFormat(Font BorderFormat){
		
		this.BorderFormat = BorderFormat;
		
	}
	
	public void setBorderLength(int BorderLength){
		
		this.BorderLength = BorderLength;
		
	}
	
	public void setCornerRadio(int CornerRadio){
		
		this.CornerRadio = CornerRadio;
		
	}
	
	public static void showOutputMessage(Object Message){
		
		showOutputMessage(null, Message, "Output", null, new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, WindowPane wp){
		
		showOutputMessage(null, Message, "Output", null, wp);
		
	}
	
	public static void showOutputMessage(Object Message, ImageIcon icono){
		
		showOutputMessage(null, Message, "Output", icono, new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, int icono){
		
		showOutputMessage(null, Message, "Output", TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, ImageIcon icono, WindowPane wp){
		
		showOutputMessage(null, Message, "Output", icono, wp);
		
	}
	
	public static void showOutputMessage(Object Message, int icono, WindowPane wp){
		
		showOutputMessage(null, Message, "Output", TYPE_MESSAGE[icono], wp);
		
	}
	
	public static void showOutputMessage(Object Message, Object Title){
		
		showOutputMessage(null, Message, Title, null, new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, Object Title, WindowPane wp){
		
		showOutputMessage(null, Message, Title, null, wp);
		
	}
	
	public static void showOutputMessage(Object Message, Object Title, ImageIcon icono){
		
		showOutputMessage(null, Message, Title, icono, new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, Object Title, int icono){
		
		showOutputMessage(null, Message, Title, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static void showOutputMessage(Object Message, Object Title, ImageIcon icono, WindowPane wp){
		
		showOutputMessage(null, Message, Title, icono, wp);
		
	}
	
	public static void showOutputMessage(Object Message, Object Title, int icono, WindowPane wp){
		
		showOutputMessage(null, Message, Title, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message){
		
		showOutputMessage(parentComponent, Message, "Output", null, new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, "Output", null, wp);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, ImageIcon icono){
		
		showOutputMessage(parentComponent, Message, "Output", icono, new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, int icono){
		
		showOutputMessage(parentComponent, Message, "Output", TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, ImageIcon icono, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, "Output", icono, wp);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, int icono, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, "Output", TYPE_MESSAGE[icono], wp);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar){
		
		showOutputMessage(parentComponent, Message, Titlebar, null, new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, Titlebar, null, wp);
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, ImageIcon icono){
		
		showOutputMessage(parentComponent, Message, Titlebar, icono, new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, int icono){
		
		showOutputMessage(parentComponent, Message, Titlebar, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, int icono, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, Titlebar, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getInputMessage(Object Message){
		
		return getInputMessage(null, Message, "Input", null, new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, WindowPane wp){
		
		return getInputMessage(null, Message, "Input", null, wp);
		
	}
	
	public static Object getInputMessage(Object Message, ImageIcon icono){
		
		return getInputMessage(null, Message, "Input", icono, new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, int icono){
		
		return getInputMessage(null, Message, "Input", TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, ImageIcon icono, WindowPane wp){
		
		return getInputMessage(null, Message, "Input", icono, wp);
		
	}
	
	public static Object getInputMessage(Object Message, int icono, WindowPane wp){
		
		return getInputMessage(null, Message, "Input", TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getInputMessage(Object Message, Object Title){
		
		return getInputMessage(null, Message, Title, null, new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, Object Title, WindowPane wp){
		
		return getInputMessage(null, Message, Title, null, wp);
		
	}
	
	public static Object getInputMessage(Object Message, Object Title, ImageIcon icono){
		
		return getInputMessage(null, Message, Title, icono, new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, Object Title, int icono){
		
		return getInputMessage(null, Message, Title, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getInputMessage(Object Message, Object Title, ImageIcon icono, WindowPane wp){
		
		return getInputMessage(null, Message, Title, icono, wp);
		
	}
	
	public static Object getInputMessage(Object Message, Object Title, int icono, WindowPane wp){
		
		return getInputMessage(null, Message, Title, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message){
		
		return getInputMessage(parentComponent, Message, "Input", null, new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, WindowPane wp){
		
		return getInputMessage(parentComponent, Message, "Input", null, wp);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, ImageIcon icono){
		
		return getInputMessage(parentComponent, Message, "Input", icono, new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, int icono){
		
		return getInputMessage(parentComponent, Message, "Input", TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, ImageIcon icono, WindowPane wp){
		
		return getInputMessage(parentComponent, Message, "Input", icono, wp);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, int icono, WindowPane wp){
		
		return getInputMessage(parentComponent, Message, "Input", TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title){
		
		return getInputMessage(parentComponent, Message, Title, null, new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title, WindowPane wp){
		
		return getInputMessage(parentComponent, Message, Title, null, wp);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title, ImageIcon icono){
		
		return getInputMessage(parentComponent, Message, Title, icono, new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title, int icono){
		
		return getInputMessage(parentComponent, Message, Title, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Title, int icono, WindowPane wp){
		
		return getInputMessage(parentComponent, Message, Title, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options){
		
		return getOptionMessage(null, Message, "Input", Options, null, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, null, wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options, ImageIcon icono){
		
		return getOptionMessage(null, Message, "Input", Options, icono, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options, int icono){
		
		return getOptionMessage(null, Message, "Input", Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, icono, wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options){
		
		return getOptionMessage(null, Message, Title, Options, null, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, null, wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options, ImageIcon icono){
		
		return getOptionMessage(null, Message, Title, Options, icono, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options, int icono){
		
		return getOptionMessage(null, Message, Title, Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, icono, wp);
		
	}
	
	public static Object getOptionMessage(Object Message, Object Title, Object[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, null, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, null, wp);
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options, ImageIcon icono){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, icono, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options, int icono){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, icono, wp);
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Title, Object[] Options){
		
		return getOptionMessage(parentComponent, Message, Title, Options, null, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Title, Object[] Options, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, Title, Options, null, wp);
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Title, Object[] Options, ImageIcon icono){
		
		return getOptionMessage(parentComponent, Message, Title, Options, icono, new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Title, Object[] Options, int icono){
		
		return getOptionMessage(parentComponent, Message, Title, Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Title, Object[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, Title, Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options){
		
		return getOptionMessage(null, Message, "Input", Options, null, new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, null, wp);
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options, ImageIcon icono){
		
		return getOptionMessage(null, Message, "Input", Options, icono, new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options, int icono){
		
		return getOptionMessage(null, Message, "Input", Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, icono, wp);
		
	}
	
	public static int getOptionMessage(Object Message, String[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(null, Message, "Input", Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options){
		
		return getOptionMessage(null, Message, Title, Options, null, new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, null, wp);
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options, ImageIcon icono){
		
		return getOptionMessage(null, Message, Title, Options, icono, new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options, int icono){
		
		return getOptionMessage(null, Message, Title, Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, icono, wp);
		
	}
	
	public static int getOptionMessage(Object Message, Object Title, String[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(null, Message, Title, Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, null, new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, null, wp);
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options, ImageIcon icono){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, icono, new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options, int icono){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options, ImageIcon icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, icono, wp);
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, String[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, "Input", Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Title, String[] Options){
		
		return getOptionMessage(parentComponent, Message, Title, Options, null, new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Title, String[] Options, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, Title, Options, null, wp);
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Title, String[] Options, ImageIcon icono){
		
		return getOptionMessage(parentComponent, Message, Title, Options, icono, new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Title, String[] Options, int icono){
		
		return getOptionMessage(parentComponent, Message, Title, Options, TYPE_MESSAGE[icono], new WindowPane());
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Title, String[] Options, int icono, WindowPane wp){
		
		return getOptionMessage(parentComponent, Message, Title, Options, TYPE_MESSAGE[icono], wp);
		
	}
	
	public static void showInformationMessage(Object Message){
		
		showInformationMessage(null, Message, "Information", new WindowPane(INFORMATION_COLOR, Color.BLACK));
		
	}
	
	public static void showInformationMessage(Object Message, Object Title){
		
		showInformationMessage(null, Message, Title, new WindowPane(INFORMATION_COLOR, Color.BLACK));
		
	}
	
	public static void showInformationMessage(Component parentComponent, Object Message){
		
		showInformationMessage(parentComponent, Message, "Information", new WindowPane(INFORMATION_COLOR, Color.BLACK));
		
	}
	
	public static void showInformationMessage(Component parentComponent, Object Message, Object Title){
		
		showInformationMessage(parentComponent, Message, Title, new WindowPane(INFORMATION_COLOR, Color.BLACK));
		
	}
	
	public static void showErrorMessage(Object Message){
		
		showErrorMessage(null, Message, "ERROR", new WindowPane(ERROR_COLOR, Color.BLACK));
		
	}
	
	public static void showErrorMessage(Object Message, Object Title){
		
		showErrorMessage(null, Message, Title, new WindowPane(ERROR_COLOR, Color.BLACK));
		
	}
	
	public static void showErrorMessage(Component parentComponent, Object Message){
		
		showErrorMessage(parentComponent, Message, "ERROR", new WindowPane(ERROR_COLOR, Color.BLACK));
		
	}
	
	public static void showErrorMessage(Component parentComponent, Object Message, Object Title){
		
		showErrorMessage(parentComponent, Message, Title, new WindowPane(ERROR_COLOR, Color.BLACK));
		
	}
	
	public static void showWarningMessage(Object Message){
		
		showWarningMessage(null, Message, "WARNING", new WindowPane(WARNING_COLOR, Color.BLACK));
		
	}
	
	public static void showWarningMessage(Object Message, Object Title){
		
		showWarningMessage(null, Message, Title, new WindowPane(WARNING_COLOR, Color.BLACK));
		
	}
	
	public static void showWarningMessage(Component parentComponent, Object Message){
		
		showWarningMessage(parentComponent, Message, "WARNING", new WindowPane(WARNING_COLOR, Color.BLACK));
		
	}
	
	public static void showWarningMessage(Component parentComponent, Object Message, Object Title){
		
		showWarningMessage(parentComponent, Message, Title, new WindowPane(WARNING_COLOR, Color.BLACK));
		
	}
	
	public static boolean getYesNoMessage(Object Message){
		
		return getYesNoMessage(null, Message, "Question", new WindowPane(QUESTION_COLOR));
		
	}
	
	public static boolean getYesNoMessage(Object Message, Object Title){
		
		return getYesNoMessage(null, Message, Title, new WindowPane(QUESTION_COLOR));
		
	}
	
	public static boolean getYesNoMessage(Component parentComponent, Object Message){
		
		return getYesNoMessage(parentComponent, Message, "Question", new WindowPane(QUESTION_COLOR));
		
	}
	
	public static boolean getYesNoMessage(Component parentComponent, Object Message, Object Title){
		
		return getYesNoMessage(parentComponent, Message, Title, new WindowPane(QUESTION_COLOR));
		
	}
	
	public static void showInformationMessage(Component parentComponent, Object Message, Object Titlebar, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, Titlebar, TYPE_MESSAGE[INFORMATION_MESSAGE], wp);
		
	}
	
	public static void showErrorMessage(Component parentComponent, Object Message, Object Titlebar, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, Titlebar, TYPE_MESSAGE[ERROR_MESSAGE], wp);
		
	}
	
	public static void showWarningMessage(Component parentComponent, Object Message, Object Titlebar, WindowPane wp){
		
		showOutputMessage(parentComponent, Message, Titlebar, TYPE_MESSAGE[WARNING_MESSAGE], wp);
		
	}
	
	public static boolean getYesNoMessage(Component parentComponent, Object Message, Object Title, WindowPane wp){
		
		return !Taylor.Arithmetic.Parser.Parse((int) new Taylor.Math.Mayth().abs(getOptionMessage(parentComponent, Message, Title, new String[] {"Yes", "No"}, TYPE_MESSAGE[QUESTION_MESSAGE], wp)));
		
	}
	
	public static void showOutputMessage(Component parentComponent, Object Message, Object Titlebar, ImageIcon Icono, WindowPane wp){
		
		final ComponentBuilder cp = new ComponentBuilder();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		
		cp.setBackground(wp.Background);
		cp.setForeground(wp.Foreground);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), wp.Format, wp.Background, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			Width+= Sum;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>1036){
			
			Height = 1036;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<936){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.getHorizontalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(wp.BorderForeground);
		cp.setBackground(wp.BorderBackground);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, wp.CornerRadio, wp.CornerRadio}, wp.Background);
		contentPane.setBorder(wp.getAbstractBorder());
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, wp.BorderFormat);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, wp.BorderFormat, cp.getBackground(), true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		contentPane.setComponentZOrder(Scroll, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, wp.CornerRadio, wp.CornerRadio));
		
		wp.addListeners(Title, Close, null, Window);
		
		Window.add(contentPane);
		Window.setVisible(true);
		
	}
	
	public static Object getInputMessage(Component parentComponent, Object Message, Object Titlebar, ImageIcon Icono, WindowPane wp){
		
		final ComponentBuilder cp = new ComponentBuilder();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		
		cp.setBackground(wp.Background);
		cp.setForeground(wp.Foreground);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), wp.Format, wp.Background, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			Width+= Sum;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>936){
			
			Height = 936;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<836){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Height+= 50;
		
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.getHorizontalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(wp.BorderForeground);
		cp.setBackground(wp.BorderBackground);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, wp.CornerRadio, wp.CornerRadio}, wp.Background);
		contentPane.setBorder(wp.getAbstractBorder());
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, wp.BorderFormat);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, wp.BorderFormat, cp.getBackground(), true, true);
		
		cp.setBackground(wp.Background);
		cp.setForeground(wp.Foreground);
		
		JTextField Input = cp.buildTextField("", new int[] {Width/15, Height-50, (int) (Width*0.85), 20}, SwingConstants.LEFT, wp.Format, wp.BorderBackground, wp.BorderBackground, true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		contentPane.setComponentZOrder(Input, z);z++;
		contentPane.setComponentZOrder(Scroll, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		Input.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, wp.CornerRadio, wp.CornerRadio));
		
		Input.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					wp.Input = Input.getText();
					Window.dispose();

				}

            }
			
		});
		
		wp.addListeners(Title, Close, null, Window);
		
		Window.add(contentPane);
		Window.setVisible(true);
		
		return wp.Input;
		
	}
	
	public static Object getOptionMessage(Component parentComponent, Object Message, Object Titlebar, Object[] Options, ImageIcon Icono, WindowPane wp){
		
		final ComponentBuilder cp = new ComponentBuilder();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		
		cp.setBackground(wp.Background);
		cp.setForeground(wp.Foreground);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), wp.Format, wp.Background, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			Width+= Sum;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>936){
			
			Height = 936;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<836){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Height+= 50;
		
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.getHorizontalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(wp.BorderForeground);
		cp.setBackground(wp.BorderBackground);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, wp.CornerRadio, wp.CornerRadio}, wp.Background);
		contentPane.setBorder(wp.getAbstractBorder());
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, wp.BorderFormat);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, wp.BorderFormat, cp.getBackground(), true, true);
		
		JComboBox<Object> Input = new JComboBox<>(Options);
		Input.setBounds(Width/15, Height-50, (int) (Width*0.85), 25);
		Input.setBackground(wp.Background);
		Input.setForeground(wp.Foreground);
		Input.setFont(wp.Format);
		Input.setBorder(new MatteBorder(1, 1, 1, 0, wp.BorderBackground));
		Input.setUI(new BasicComboBoxUI(){
			
            protected JButton createArrowButton(){
				
                JButton Arrow = new JButton(new Icon(){
                    
                    public void paintIcon(Component c, Graphics g, int x, int y){
						
                        Graphics2D g2d = (Graphics2D) g.create();
						
						RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2d.setRenderingHints(Render);
						
                        g2d.setColor(wp.Background);
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
				
				Arrow.setBackground(wp.BorderBackground);
				Arrow.setFocusable(false);
				Arrow.setBorderPainted(false);
				
				return Arrow;
				
            }
			
			protected ComboPopup createPopup(){
				
				return new BasicComboPopup(comboBox){
					
					protected JScrollPane createScroller(){
						
						JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scroller.setBorder(null);
						scroller.getVerticalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
						scroller.getHorizontalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
						
						return scroller;
						
					}
					
				};
				
			}
			
        });
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		contentPane.setComponentZOrder(Input, z);z++;
		contentPane.setComponentZOrder(Scroll, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		Input.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, wp.CornerRadio, wp.CornerRadio));
		
		Input.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					wp.Input = Input.getSelectedItem();
					Window.dispose();

				}

            }
			
		});
		
		wp.addListeners(Title, Close, null, Window);
		
		Window.add(contentPane);
		Window.setVisible(true);
		
		return wp.Input;
		
	}
	
	public static int getOptionMessage(Component parentComponent, Object Message, Object Titlebar, String[] Options, ImageIcon Icono, WindowPane wp){
		
		final ComponentBuilder cp = new ComponentBuilder();
		
		int Width = 0;
		int Height = 0;
		int z = 0;
		int Sum = 0;
		int bupY = 0;
		int bupWidth = 0;
		
		cp.setBackground(wp.Background);
		cp.setForeground(wp.Foreground);
		
		JTextArea Text = cp.buildTextArea(Message.toString(), wp.Format, wp.Background, false, true);
		Dimension PreferredSize = Text.getPreferredSize();
		Width = (int) (1.2*PreferredSize.getWidth());
		Height = (int) (PreferredSize.getHeight());
		
		if (Width<400){
			
			Width = 400;
			
		}else if (Width>1900){
			
			Width = 1900;
			
		}
		
		JLabel icon = new JLabel();
		if (Icono!=null){
			
			icon = new JLabel(new ImageIcon(Icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			icon.setBounds(Width/25, 60, 40, 40);
			Sum = 40;
			Width+= Sum;
			
		}
		
		if (Height<50){
			
			Height = 50;
			
		}else if (Height>936){
			
			Height = 936;
			
		}
		
		JButton[] input = new JButton[Options.length];
		
		for (int i=0; i<input.length; i++){
			
			input[i] = new JButton(Options[i]);
			
			input[i].setSize(50 + Options[i].length()*10, 25);
			input[i].setFont(wp.Format);
			input[i].setBackground(wp.Background);
			input[i].setForeground(wp.Foreground);
			input[i].setBorder(new LineBorder(wp.BorderBackground, 1, true));
			input[i].setHorizontalTextPosition(SwingConstants.LEFT);
			input[i].setVisible(true);
			
			bupWidth+= input[i].getWidth() + 10;
			
		}
		
		if (bupWidth>Width){
			
			Width+= (bupWidth - Width) + 20;
			
		}
		
		JScrollPane Scroll = wp.Scroll(Sum, Width, Height);
		
		if (Scroll.getVerticalScrollBar().isVisible()==true){
			
			if (Height<836){
			
				Height+= 100;
				
			}
			
			Scroll = wp.Scroll(Sum, Width, Height);
			
		}
		
		Height+= 50;
		
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.getHorizontalScrollBar().setUI(new ScrollBar(wp.Background, wp.BorderBackground, wp.BorderBackground));
		Scroll.setViewportView(Text);
		Scroll.setBorder(null);
		
		cp.setForeground(wp.BorderForeground);
		cp.setBackground(wp.BorderBackground);
		
		JPanel contentPane = cp.buildPanel(new int[] {0, 0, Width, Height}, new int[] {0, 0, Width, Height, wp.CornerRadio, wp.CornerRadio}, wp.Background);
		contentPane.setBorder(wp.getAbstractBorder());
		
		JLabel Title = cp.buildLabel("    "+Titlebar.toString(), new int[] {0, 0, Width, 30}, SwingConstants.TOP, SwingConstants.LEFT, wp.BorderFormat);
		JButton Close = cp.buildButton("X", new int[] {Width-50, 5, 30, 24}, new int [] {0, 0, 30, Height/6, 0, 0}, SwingConstants.CENTER, SwingConstants.CENTER, wp.BorderFormat, cp.getBackground(), true, true);
		
		contentPane.setLayout(null);
		contentPane.setComponentZOrder(icon, z);z++;
		contentPane.setComponentZOrder(Close, z);z++;
		contentPane.setComponentZOrder(Title, z);z++;
		contentPane.setComponentZOrder(Scroll, z);z++;
		
		Title.setVisible(true);
		Close.setVisible(true);
		Scroll.setVisible(true);
		
		JDialog Window = new JDialog((JFrame) parentComponent, true);
		Window.setSize(Width, Height+5);
        Window.setLocationRelativeTo(parentComponent);
        Window.setUndecorated(true);
        Window.setShape(new RoundRectangle2D.Double(0, 0, Width, Height+5, wp.CornerRadio, wp.CornerRadio));
		
		wp.addListeners(Title, Close, -1, Window);
		
		bupY = (Width - bupWidth)/2 + wp.BorderLength;
		
		for (int i=0; i<input.length; i++){
			
			input[i].setLocation(bupY, Height-50);
			
			final int indice = i;
			
			input[i].addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e){
					
					wp.Input = indice;
					Window.dispose();
					
				}
				
			});
			
			contentPane.setComponentZOrder(input[i], z);z++;
			
			bupY+= input[i].getWidth() + 10;
			
		}
		
		Window.add(contentPane);
		Window.setVisible(true);
		
		return Integer.parseInt(wp.Input.toString());
		
	}
	
	private AbstractBorder getAbstractBorder(){
		
		WindowPane wp = this;
		
		return new AbstractBorder(){
            
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
				
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(wp.BorderBackground);
				g2d.setStroke(new BasicStroke(wp.BorderLength));
                g2d.drawRoundRect(x+1, y, (width-3), (height-2), wp.CornerRadio, wp.CornerRadio);

                g2d.dispose();
				
            }
			
        };
		
	}
	
	private void addListeners(JLabel Title, JButton Close, Object value, JDialog Window){
		
		WindowPane wp = this;
		
		Close.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				wp.Input = value;
				Window.dispose();
				
			}
			
		});
		
		Title.addMouseListener(new MouseAdapter(){
			
            public void mousePressed(MouseEvent e){
				
                wp.X = e.getX();
                wp.Y = e.getY();
				
            }
			
        });
		
		Title.addMouseMotionListener(new MouseAdapter(){
			
            public void mouseDragged(MouseEvent e){
                
                int x = e.getXOnScreen() - wp.X;
                int y = e.getYOnScreen() - wp.Y;

                Window.setLocation(x, y);
				
            }
			
        });
		
	}
	
	private JScrollPane Scroll(int Sum, int PanelWidth, int PanelHeight){
		
		int w = 0;
		
		if (Sum!=0){w = 10;}
		
		JScrollPane Scroll = new JScrollPane();
		
		Scroll.setBounds(PanelWidth/15 + Sum, 70, (int) (PanelWidth*0.85) - w, (int) PanelHeight-70);
		Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		return Scroll;
		
	}
	
}