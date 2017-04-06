package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import room.GameRoom;

public class FinishMenu {
	
	private JPanel finishPanel;
	private JButton backButton;
	private JLabel finishLabel;
	
	public FinishMenu(){
		
	};
	
	public void init(GameRoom finishRoom, int sizeX, int sizeY){

		finishPanel = new JPanel();
		finishPanel.setVisible(true);
		finishPanel.setSize(sizeX, sizeY);
		finishPanel.setBackground(new Color(0.1F, 0.1F, 0.1F, 0.1F));
		finishPanel.setFocusable(false);
		finishPanel.setLayout(null);

		backButton = new JButton("MAIN MENU");
		backButton.setSize(200, 50);
		backButton.setLocation(sizeX/2, sizeY/2);
		backButton.setFocusable(false);
		backButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				finishRoom.quitGame();
			}
		});		
		
		finishLabel = new JLabel("You've finished the level!");
		finishLabel.setBounds(220, 200, 400, 20);
		finishLabel.setLocation(10, 220);
		
		finishPanel.add(backButton);
		finishPanel.add(finishLabel);
	}
	
	public JPanel getFinishPanel() {
		return finishPanel;
	}
	
	
}
