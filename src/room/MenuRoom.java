package room;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	private ButtonGroup modeSelectGroup;
	
	private int width = 600;
	private int height = 400;
	
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
	
		ImageIcon startIcon = new ImageIcon(getClass().getResource("/textures/button/playButton.png"));
		startButton = new JButton(startIcon);
		startButton.setLocation(220, 150);
		startButton.setSize(200, 50);
		startButton.setFocusable(false);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				theGame.setRoom(new GameRoom(theGame, modeSelectGroup.getSelection().getActionCommand()));
			}
			
		});
		
		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/textures/button/exitButton.png"));
		exitButton = new JButton(exitIcon);
		exitButton.setLocation(220, 210);
		exitButton.setSize(200, 50);
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
		
		ImageIcon helpIcon = new ImageIcon(getClass().getResource("/textures/button/helpButton.png"));
		helpButton = new JButton(helpIcon);
		helpButton.setLocation(220, 270);
		helpButton.setSize(200, 50);
		helpButton.setFocusable(false);
		helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel upHelp = new JLabel("Arrow UP:  Moves Player upwards");
				upHelp.setBounds(100, 100, 400, 20);
				upHelp.setLocation(220, 280);
		
				backPanel.add(upHelp);

				JLabel downHelp = new JLabel("Arrow DOWN:  Moves Player downwards");
				downHelp.setBounds(220, 200, 400, 20);
				downHelp.setLocation(220,290);
				backPanel.add(downHelp);

				JLabel leftHelp = new JLabel("Arrow LEFT:  Moves Player left");
				leftHelp.setBounds(100, 200, 400, 20);
				leftHelp.setLocation(220, 300);
				backPanel.add(leftHelp);

				JLabel rightHelp = new JLabel("Arrow RIGHT:  Moves Player right");
				rightHelp.setBounds(100, 250, 400, 20);
				rightHelp.setLocation(220, 310);
				backPanel.add(rightHelp);
			}
		});			
		
		JRadioButton easyMode = new JRadioButton("Easy", false);
		easyMode.setSize(100, 20);
		easyMode.setLocation(0, 20);
		easyMode.setActionCommand("easy");
		easyMode.setFocusable(false);
		JRadioButton normalMode = new JRadioButton("Normal", true);
		normalMode.setSize(100, 20);
		normalMode.setLocation(0, 40);
		normalMode.setActionCommand("normal");
		normalMode.setFocusable(false);
		JRadioButton hardMode = new JRadioButton("Hard", false);
		hardMode.setSize(100, 20);
		hardMode.setLocation(0, 60);
		hardMode.setActionCommand("hard");
		hardMode.setFocusable(false);
		
		easyMode.setForeground(Color.BLACK);
		easyMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		normalMode.setForeground(Color.BLACK);
		normalMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		hardMode.setForeground(Color.BLACK);
		hardMode.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		
		modeSelectGroup = new ButtonGroup();
		modeSelectGroup.add(easyMode);
		modeSelectGroup.add(normalMode);
		modeSelectGroup.add(hardMode);
		
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

	@Override
	public void closeRoom() {
		
	}

}
