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

public class FinishMenu {
	
	private static final int PANEL_WIDTH = 400;
	private static final int PANEL_HEIGHT = 200;
	
	private JPanel finishPanel;
	
	public FinishMenu(){}
	
	public void init(GameRoom finishRoom, int sizeX, int sizeY){

		finishPanel = new JPanel();
		finishPanel.setVisible(true);
		finishPanel.setLocation((sizeX  - PANEL_WIDTH) / 2, (sizeY - PANEL_HEIGHT) / 2);
		finishPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		finishPanel.setBackground(new Color(0.1F, 0.1F, 0.1F, 0.75F));
		finishPanel.setBorder(BorderFactory.createEtchedBorder());
		finishPanel.setFocusable(false);
		finishPanel.setLayout(null);
		
		JButton backButton = new JButton("MAIN MENU");
		backButton.setSize(200, 50);
		backButton.setLocation((PANEL_WIDTH - 200) / 2, PANEL_HEIGHT / 2);
		backButton.setFocusable(false);
		backButton.addActionListener(new ActionListener(){   
			public void actionPerformed(ActionEvent e){
				finishRoom.quitGame();
			}
		});		
		
		Font font = new Font("Verdana", Font.BOLD, 24);
		
		JLabel finishLabel = new JLabel("You've finished the level!");
		finishLabel.setFont(font);
		finishLabel.setForeground(Color.WHITE);
		finishLabel.setSize(400, 40);
		finishLabel.setLocation(0, 20);
		finishLabel.setHorizontalAlignment(JLabel.CENTER);
		
		finishPanel.add(backButton);
		finishPanel.add(finishLabel);
	}
	
	public JPanel getFinishPanel() {
		return finishPanel;
	}
	
	
}
