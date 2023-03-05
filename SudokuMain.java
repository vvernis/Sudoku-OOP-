package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;

import javax.swing.*;


/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
	
	// private variables
	GameBoard board = new GameBoard();
	JMenuBar MenuBar = new JMenuBar(); // the menu-bar
	JMenu SubMenu;
	JMenuItem MenuItem;
	JPanel SubPanel = new JPanel();

	// Constructor
	public SudokuMain() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		setJMenuBar(MenuBar);

		cp.add(board, BorderLayout.CENTER);
		
		cp.add(SubPanel, BorderLayout.SOUTH);

		// Add a button to the south to re-start the game
		JButton btnNewGame = new JButton("Reset Game");
		SubPanel.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		          
		          ImageIcon squidIcon = new ImageIcon("howtoplay.jpg");
		          
				Object[] options = { "Confirm", "Noooooooo welps" }; // jokes only
				int n = JOptionPane.showOptionDialog(null,
						"Are you sure you want to start a new game? \nYou will lose all progress of your current game!",
						"Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, squidIcon, options, options[0]);
				if (n == JOptionPane.YES_OPTION) {
					board.Reset();
				} else {
					setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
				}
			}
		});
		
		

		// File Menu
		JMenu Menu = new JMenu("File"); // each menu in the menu-bar
		MenuBar.add(Menu); // menu is added into menu button

		MenuItem = new JMenuItem("New Game");
		Menu.add(MenuItem); // the menu adds this item
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		          
				Object[] levels = { "Easy", "Advanced", "Expert", "Hell" };
				String difficulty = (String) JOptionPane.showInputDialog(null,
						"Current game progress will be lost! \nSelect Game Mode:", "New Game",
						JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);

				// If a string was returned, restart game to difficulty chosen.
				if ((difficulty != null) && (difficulty.length() > 0)) {
					if(difficulty == levels[0]) {
						board.init();
						}
					if(difficulty == levels[1]) {
						board.Advanced();
						}
					if(difficulty == levels[2]) {
						board.Expert();
						}
					if(difficulty == levels[3]) {
						board.Hell();
						}
					return;
				}
			}
		});
		

		Menu.addSeparator();
		
		MenuItem = new JMenuItem("Exit");
		Menu.add(MenuItem); // the menu adds this item
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		       
		          ImageIcon squidIcon = new ImageIcon("howtoplay.jpg");
		          
				Object[] options = { "oKEI", "Actually nah" }; // jokes only
				int n = JOptionPane.showOptionDialog(null, "You will exit the game noww byeee", "SEE YOU AGAIN?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, squidIcon, options, options[0]);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
				}
			}
		});
		
		Menu = new JMenu("Theme");
		MenuBar.add(Menu);

		MenuItem = new JMenuItem("Light Mode");
		Menu.add(MenuItem);
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		          
				board.lightcolor();
			}
		});
		
		MenuItem = new JMenuItem("Dark Mode");
		Menu.add(MenuItem);
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		          
				board.darkcolor();
			}
		});
			
		
		

		Menu = new JMenu("Hints");
		MenuBar.add(Menu);

		MenuItem = new JMenuItem("Reveal All");
		Menu.add(MenuItem);
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.RevealAll();
		          
				String filepath = "Lose.wav";
		          SoundEffects loseSoundEffects = new SoundEffects();
		          loseSoundEffects.playSound(filepath);
				ImageIcon LoseIcon = new ImageIcon("LL.gif");
		          JOptionPane.showMessageDialog(null, "Try Again!", null, JOptionPane.INFORMATION_MESSAGE, LoseIcon);
			}

		});
		

		MenuItem = new JMenuItem("Reveal One");
		Menu.add(MenuItem);
		MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.RevealOne();
				
				String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
			}

		});
		
		//menu for Help
		Menu = new JMenu("Help");
		MenuBar.add(Menu);
		
		MenuItem = new JMenuItem("How to Play");
		Menu.add(MenuItem);
	      MenuItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String filepath = "boink.wav";
		          SoundEffects btnSoundEffects = new SoundEffects();
		          btnSoundEffects.playSound(filepath);
		          
		          ImageIcon squidIcon = new ImageIcon("howtoplay.jpg");
			        JOptionPane.showMessageDialog(null, "Each row, column and 3 by 3 sub grids\n cannot have the same number.", null,JOptionPane.INFORMATION_MESSAGE, squidIcon); 
	        }
	      });
	      
		Menu.add(MenuItem);

		board.init();

		pack(); // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
		setTitle("Sudoku");
		setVisible(true);
		
		//BGM
		String filepath = "BGM.wav";
		BGM soundtrack = new BGM();
		soundtrack.playmusic(filepath);
	}



	/** The entry main() entry method */
	public static void main(String[] args) {
		// [TODO 1] Check Swing program template on how to run the constructor
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SudokuMain(); // Let the constructor do the job

			}
		});
	}
}