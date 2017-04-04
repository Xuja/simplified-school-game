package roombuilder;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RoomBuilder {
	
	private JFrame frame;
	private JTextArea widthText;
	private JTextArea heightText;
	
	public RoomBuilder(){
		frame = new JFrame();
		frame.setTitle("Room Builder");
		frame.setResizable(false);
		this.resizeDisplay(400, 129);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		createStartupPanel();
	}
	
	private void createStartupPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(400, 200);
		
		widthText = new JTextArea("width");
		widthText.setLocation(20, 20);
		widthText.setSize(160, 24);
		widthText.setVisible(true);
		widthText.setBorder(BorderFactory.createEtchedBorder());
		
		heightText = new JTextArea("height");
		heightText.setLocation(220, 20);
		heightText.setSize(160, 24);
		heightText.setVisible(true);
		heightText.setBorder(BorderFactory.createEtchedBorder());
		
		JButton button = new JButton("Create");
		button.setLocation(20, 60);
		button.setSize(360, 24);
		button.setVisible(true);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				int width = Integer.parseInt(widthText.getText());
				int height = Integer.parseInt(heightText.getText());
				BuilderPanel panel = new BuilderPanel(width, height);
				frame.dispose();
			}
			
		});
		
		panel.add(button);
		panel.add(widthText);
		panel.add(heightText);
		
		panel.setVisible(true);
		
		frame.getContentPane().add(panel);
		panel.repaint();
	}
	
	private void resizeDisplay(int width, int height){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorWidth = gd.getDisplayMode().getWidth();
		int monitorHeight = gd.getDisplayMode().getHeight();
		frame.setBounds((monitorWidth - width) / 2, (monitorHeight - height) / 2, width, height);
	}
	
	public static void main(String[] args){
		RoomBuilder builder = new RoomBuilder();
	}
}
