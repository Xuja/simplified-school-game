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
	
	private boolean isOpen = false;
	
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
				JLabel spaceHelp = new JLabel("<html><font color = 'red'>Spacebar: Pick Up key</font></html>");
				spaceHelp.setBounds(100, 100, 400, 20);
				spaceHelp.setLocation(10, 280);
				
				backPanel.add(spaceHelp);
				
				JLabel upHelp = new JLabel("<html><font color = 'red'> Arrow UP:  Moves Player up </font></html>");
				upHelp.setBounds(100, 100, 400, 20);
				upHelp.setLocation(10, 200);
		
				backPanel.add(upHelp);

				JLabel downHelp = new JLabel("<html><font color = 'red'> Arrow DOWN:  Moves Player down </font></html>");
				downHelp.setBounds(220, 200, 400, 20);
				downHelp.setLocation(10, 220);
				backPanel.add(downHelp);

				JLabel leftHelp = new JLabel("<html><font color = 'red'> Arrow LEFT:  Moves Player left </font></html>");
				leftHelp.setBounds(100, 200, 400, 20);
				leftHelp.setLocation(10, 240);
				backPanel.add(leftHelp);

				JLabel rightHelp = new JLabel("<html><font color = 'red'> Arrow RIGHT:  Moves Player right </font></html>");
				rightHelp.setBounds(100, 250, 400, 20);
				rightHelp.setLocation(10, 260);
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
