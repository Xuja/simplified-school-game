package room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import game.Game;

public class MenuRoom extends Room{

	private JLayeredPane backPanel;
	private JPanel mainPanel;
	
	public MenuRoom(Game game) {
		super(game);
	}

	@Override
	public void init() {
		
		backPanel = new JLayeredPane();
		backPanel.setLayout(null);
		backPanel.setSize(600, 600);
		backPanel.setVisible(true);
		backPanel.setFocusable(false);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(600, 600);
		mainPanel.setVisible(true);
		mainPanel.setFocusable(false);
		
		JButton button = new JButton("Start");
		button.setLocation(50, 50);
		button.setSize(200, 40);
		button.setFocusable(false);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				theGame.setRoom(new GameRoom(theGame, 10));
			}
			
		});
		mainPanel.add(button);
		backPanel.add(button);
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public JLayeredPane getPanel() {
		return backPanel;
	}

}
