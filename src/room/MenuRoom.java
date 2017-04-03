package room;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import game.Game;

public class MenuRoom extends Room{

	private JLayeredPane backPanel;
	private JPanel mainPanel;
	private JButton startButton, exitButton, helpButton;
		
	private int width = 600;
	private int height = 600;
	
	public MenuRoom(Game game) {
		super(game);
	}

	@Override
	public void init() {
		
		backPanel = new JLayeredPane();
		backPanel.setLayout(null);
		backPanel.setSize(width, height);
		backPanel.setVisible(true);
		backPanel.setFocusable(false);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(width, height);
		mainPanel.setVisible(true);
		mainPanel.setFocusable(false);		
		
		startButton = new JButton("Start");
		startButton.setLocation(220, 150);
		startButton.setSize(200, 40);
		startButton.setFocusable(false);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				theGame.setRoom(new GameRoom(theGame, 10));
			}
			
		});
		
		exitButton = new JButton("Exit");
		exitButton.setLocation(220, 250);
		exitButton.setSize(200, 40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
		
		helpButton = new JButton("Help");
		helpButton.setLocation(220, 350);
		helpButton.setSize(200, 40);
		helpButton.setFocusable(false);
		helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel upHelp = new JLabel("Arrow UP:  Moves Player upwards");
				upHelp.setBounds(100, 100, 400, 20);
				upHelp.setLocation(220, 400);
		
				backPanel.add(upHelp);

				JLabel downHelp = new JLabel("Arrow DOWN:  Moves Player downwards");
				downHelp.setBounds(220, 200, 400, 20);
				downHelp.setLocation(220,420);
				backPanel.add(downHelp);

				JLabel leftHelp = new JLabel("Arrow LEFT:  Moves Player left");
				leftHelp.setBounds(100, 200, 400, 20);
				leftHelp.setLocation(220, 440);
				backPanel.add(leftHelp);

				JLabel rightHelp = new JLabel("Arrow RIGHT:  Moves Player right");
				rightHelp.setBounds(100, 250, 400, 20);
				rightHelp.setLocation(220, 460);
				backPanel.add(rightHelp);
			}
		});			
		
		JRadioButton easyMode = new JRadioButton("Easy", false);
		easyMode.setSize(100, 20);
		easyMode.setLocation(0, 20);
		JRadioButton normalMode = new JRadioButton("Normal", true);
		normalMode.setSize(100, 20);
		normalMode.setLocation(0, 40);
		JRadioButton hardMode = new JRadioButton("Hard", false);
		hardMode.setSize(100, 20);
		hardMode.setLocation(0, 60);
		
		easyMode.setForeground(Color.BLACK);
		easyMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		normalMode.setForeground(Color.BLACK);
		normalMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		hardMode.setForeground(Color.BLACK);
		hardMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(easyMode);
		bg.add(normalMode);
		bg.add(hardMode);
		
		if(easyMode.isSelected()){
			//code
		}
		
		if(normalMode.isSelected()){
			
		}
		
		if(hardMode.isSelected()){
			//code
		}
		
		mainPanel.add(easyMode);
		mainPanel.add(normalMode);
		mainPanel.add(hardMode);		
		backPanel.add(easyMode);
		backPanel.add(normalMode);
		backPanel.add(hardMode);	
		
		
		mainPanel.add(startButton);
		mainPanel.add(exitButton);
		mainPanel.add(helpButton);

		backPanel.add(startButton);
		backPanel.add(exitButton);
		backPanel.add(helpButton);
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public JLayeredPane getPanel() {
		return backPanel;
	}

}
