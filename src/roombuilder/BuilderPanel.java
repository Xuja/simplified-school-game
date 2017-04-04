package roombuilder;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BuilderPanel {
	
	public static final int TILE_SIZE = 64;
	
	private JFrame frame;
	private JPanel panel;
	private int width;
	private int height;
	private BuilderButton[][] buttons;
	
	public BuilderPanel(int w, int h){
		this.width = w;
		this.height = h;
		init();
	}
	
	public void init(){
		frame = new JFrame();
		frame.setTitle("Room Builder");
		frame.setResizable(false);
		this.resizeDisplay(width * TILE_SIZE + 6, height * TILE_SIZE + 69);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setSize(width * TILE_SIZE + 6, height * TILE_SIZE + 69);
		
		buttons = new BuilderButton[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				buttons[x][y] = new BuilderButton(x, y);
				panel.add(buttons[x][y]);
			}
		}
		
		JButton exportButton = new JButton("Export");
		exportButton.setLocation(0, height * TILE_SIZE);
		exportButton.setSize(width * TILE_SIZE, 40);
		exportButton.setVisible(true);
		exportButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					export(file);
				}
			}
			
		});
		panel.add(exportButton);
		
		frame.getContentPane().add(panel);
	}
	
	private void resizeDisplay(int width, int height){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorWidth = gd.getDisplayMode().getWidth();
		int monitorHeight = gd.getDisplayMode().getHeight();
		frame.setBounds((monitorWidth - width) / 2, (monitorHeight - height) / 2, width, height);
	}
	
	private void export(File file){
		try {
			PrintWriter writer = new PrintWriter(file);
			for(int i = 0; i < height; i++){
				writer.write(getTileString(i) + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String getTileString(int height){
		String s = "";
		for(int i = 0; i < width; i++){
			if(i == 0){
				s = String.valueOf(buttons[i][height].tileIndex);
			}
			else{
				s = s + " " + buttons[i][height].tileIndex;
			}
		}
		return s;
	}
}
