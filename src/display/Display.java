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
	
	private boolean closeWindow = false;
	
	public Display(String title){
		this.setTitle(title);
		createDisplay();
	}

	public void createDisplay(){
		this.setResizable(false);

		this.resizeDisplay();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(this);
	}
	
	public boolean isWindowClosing(){
		return closeWindow;
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {	closeWindow = true;		}
	
	public void setPanel(JLayeredPane panel){
		this.getContentPane().removeAll();
		Dimension dim = panel.getSize();
		this.width = dim.width;
		this.height = dim.height;
		resizeDisplay();
		this.getContentPane().add(panel);
		this.repaint();
	}
	
	private void resizeDisplay(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorWidth = gd.getDisplayMode().getWidth();
		int monitorHeight = gd.getDisplayMode().getHeight();
		this.setBounds((monitorWidth - width) / 2, (monitorHeight - height) / 2, width, height);
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}
