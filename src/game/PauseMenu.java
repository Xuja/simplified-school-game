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

	public void init(GameRoom room){

		pausePanel = new JPanel();
		pausePanel.setVisible(true);
		pausePanel.setSize(room.getSizeX(), room.getSizeY());
		pausePanel.setFocusable(false);

		ImageIcon resumeIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resumeButton.png"));
		resumeButton = new JButton(resumeIcon);
		resumeButton.setSize(200, 40);
		pausePanel.add(resumeButton, layout.CENTER);
		resumeButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				room.resumeGame();
			}

		});

		ImageIcon resetIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/resetButton.png"));

		resetButton = new JButton(resetIcon);
		resetButton.setSize(200, 50);
		pausePanel.add(resetButton, layout.CENTER);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				room.restartGame();
			}

		});

		ImageIcon quitIcon = new ImageIcon(getClass().getResource("/texture/buttonIMG/quitButton.png"));
		quitButton = new JButton(quitIcon);
		quitButton.setSize(200, 50);
		pausePanel.add(quitButton, layout.CENTER);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				room.quitGame();
			}
		});
	}

	public JPanel getPausePanel() {
		return pausePanel;
	}
	
	
	
	
	


}