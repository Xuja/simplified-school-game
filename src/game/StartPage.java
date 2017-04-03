package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage {

	/*public static JPanel createWindow(int width, int height){

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setSize(width,height);
		
		JButton startButton = new JButton("START");
		JButton quitButton = new JButton("QUIT");
		JButton helpButton = new JButton("HELP");

		startButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				Game game = new Game("Key Game"); 
				f.dispose();
			}
		});

		quitButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				f.dispose();
			}
		});

		helpButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				p.setVisible(false);

				JPanel p2 = new JPanel();
				p2.setLayout(null);
				p2.setSize(800, 1000);

				JLabel upHelp = new JLabel("Arrow UP:  Moves Player upwards");
				upHelp.setBounds(100, 100, 200, 20);
				p2.add(upHelp);

				JLabel downHelp = new JLabel("Arrow DOWN:  Moves Player downwards");
				downHelp.setBounds(100, 150, 250, 20);
				p2.add(downHelp);

				JLabel leftHelp = new JLabel("Arrow LEFT:  Moves Player left");
				leftHelp.setBounds(100, 200, 200, 20);
				p2.add(leftHelp);

				JLabel rightHelp = new JLabel("Arrow RIGHT:  Moves Player right");
				rightHelp.setBounds(100, 250, 200, 20);
				p2.add(rightHelp);
			}
		});

		startButton.setBounds(100,150, 80,30);
		p.add(startButton); 
		quitButton.setBounds(100, 210, 80, 30);
		p.add(quitButton);
		helpButton.setBounds(100, 270, 80, 30);
		p.add(helpButton);
	}*/
}
