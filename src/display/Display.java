package display;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Display extends JFrame implements WindowListener{
	
	private int width, height;
	public String title;

	private int rows;
	
	//is deze wel nodig?
	//private JLabel playerLabel;
	
	private boolean closeWindow = false;
	
	public Display(String title){
		this.title = title;
		this.rows = 10;
		createDisplay();
	}

	public void createDisplay(){
		this.setTitle(title);
		this.setResizable(false);

		this.resizeDisplay();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(this);
	}
	
	private void resizeDisplay(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorWidth = gd.getDisplayMode().getWidth();
		int monitorHeight = gd.getDisplayMode().getHeight();
		this.setBounds((monitorWidth - width) / 2, (monitorHeight - height) / 2, width, height);
	}
	
	public void setPanel(JLayeredPane panel){
		this.getContentPane().removeAll();
		Dimension dim = panel.getSize();
		this.width = dim.width;
		this.height = dim.height;
		resizeDisplay();
		this.getContentPane().add(panel);
		this.repaint();
	}
	
	public void replacePanel(JLayeredPane panel){
		
	}
	
	public boolean isWindowClosing(){
		return closeWindow;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {	closeWindow = true;		}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}
