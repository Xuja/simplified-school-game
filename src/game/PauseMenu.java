package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import room.GameRoom;

public class PauseMenu{

	private JPanel pausePanel;
	private JButton resumeButton, quitButton, resetButton;
	//private BorderLayout layout;
	
	public PauseMenu(){}

	public void init(GameRoom pauseRoom, int sizeX, int sizeY){

		pausePanel = new JPanel();
		pausePanel.setVisible(true);
		pausePanel.setSize(sizeX, sizeY);
		pausePanel.setBackground(new Color(0.1F, 0.1F, 0.1F, 0.1F));
		pausePanel.setFocusable(false);
		pausePanel.setLayout(null);

		//ImageIcon resumeIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resumeButton.png"));
		resumeButton = new JButton("resume");
		resumeButton.setSize(200, 40);
		resumeButton.setFocusable(false);
		resumeButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				pauseRoom.resumeGame();
			}

		});

		//ImageIcon resetIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resetButton.png"));

		resetButton = new JButton("");
		resetButton.setSize(200, 50);
		resetButton.setFocusable(false);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.restartGame();
			}

		});

		//ImageIcon quitIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/quitButton.png"));
		quitButton = new JButton("");
		quitButton.setSize(200, 50);
		quitButton.setFocusable(false);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.quitGame();
			}
		});
		
		pausePanel.add(resumeButton);
		pausePanel.add(quitButton);
		pausePanel.add(resetButton);
	}

	public JPanel getPausePanel() {
		return pausePanel;
	}
	
	
	
	
	


}