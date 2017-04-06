package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import room.GameRoom;

public class PauseMenu{

	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 300;
	
	private JPanel pausePanel;
	
	public PauseMenu(){}

	public void init(GameRoom pauseRoom, int sizeX, int sizeY){
		
		pausePanel = new JPanel();
		pausePanel.setVisible(true);
		pausePanel.setLocation((sizeX - PANEL_WIDTH) / 2, (sizeY - PANEL_HEIGHT) / 2);
		pausePanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		pausePanel.setBackground(new Color(0.1F, 0.1F, 0.1F, 0.75F));
		pausePanel.setBorder(BorderFactory.createEtchedBorder());
		pausePanel.setFocusable(false);
		pausePanel.setLayout(null);
		
		JLabel pauseLabel = new JLabel("Paused");
		Font font = new Font("Verdana", Font.BOLD, 24);
		pauseLabel.setFont(font);
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setSize(200, 40);
		pauseLabel.setLocation(100, 20);
		pauseLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JButton resumeButton = new JButton("RESUME");
		resumeButton.setSize(200, 50);
		resumeButton.setLocation(100, 100);
		resumeButton.setFocusable(false);
		resumeButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				pauseRoom.resumeGame();
			}

		});

		JButton resetButton = new JButton("RESET");
		resetButton.setSize(200, 50);
		resetButton.setLocation(100, 160);
		resetButton.setFocusable(false);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.restartGame();
			}

		});

		JButton quitButton = new JButton("QUIT");
		quitButton.setSize(200, 50);
		quitButton.setLocation(100, 220);
		quitButton.setFocusable(false);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.quitGame();
			}
		});
		
		pausePanel.add(resumeButton);
		pausePanel.add(quitButton);
		pausePanel.add(resetButton);
		pausePanel.add(pauseLabel);
	}

	public JPanel getPausePanel() {
		return pausePanel;
	}
	
}