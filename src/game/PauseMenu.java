package game;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import room.GameRoom;

public class PauseMenu{

	private JPanel pausePanel;
	private JButton resumeButton, quitButton, resetButton;
	private BorderLayout layout;
	
	public PauseMenu(){
		
	}

	public void init(GameRoom pauseRoom){

		pausePanel = new JPanel();
		pausePanel.setVisible(true);
		pausePanel.setSize(pauseRoom.getSizeX(), pauseRoom.getSizeY());
		pausePanel.setFocusable(false);

		//ImageIcon resumeIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resumeButton.png"));
		resumeButton = new JButton("resume");
		resumeButton.setSize(200, 40);
		pausePanel.add(resumeButton, layout.CENTER);
		resumeButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				pauseRoom.resumeGame();
			}

		});

		//ImageIcon resetIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resetButton.png"));

		resetButton = new JButton("");
		resetButton.setSize(200, 50);
		pausePanel.add(resetButton, layout.CENTER);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.restartGame();
			}

		});

		//ImageIcon quitIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/quitButton.png"));
		quitButton = new JButton("");
		quitButton.setSize(200, 50);
		pausePanel.add(quitButton, layout.CENTER);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseRoom.quitGame();
			}
		});
	}

	public JPanel getPausePanel() {
		return pausePanel;
	}
	
	
	
	
	


}