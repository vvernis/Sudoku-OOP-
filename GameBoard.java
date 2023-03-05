package sudoku;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoard extends JPanel {
   // Name-constants for the game board properties
   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

   // Name-constants for UI sizes
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;

   // The game board composes of 9x9 "Customized" JTextFields,
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];

   // It also contains a Puzzle
   private Puzzle puzzle = new Puzzle();
   
   // Constructor
   public GameBoard() {
      super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // JPanel

      
      // Allocate the 2D array of Cell, and added into JPanel.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);   // JPanel
          
         }
      }
     
           

      
      // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
      //  Cells (JTextFields)
      CellInputListener listener = new CellInputListener();
      // [TODO 4] Every editable cell adds this common listener
      for (int row= 0; row < GRID_SIZE; ++row) {
    	   for (int col= 0; col < GRID_SIZE; ++col) {
    	      if (cells[row][col].isEditable()) {
    	         cells[row][col].addActionListener(listener);   // For all editable rows and cols
    	      }
    	   }
    	} 
      

      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }
   
   public void lightcolor() {
	   for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	        	 cells[row][col].color = false;
	        	 cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
	        	 
	         }
	   }
   }
   
   public void darkcolor() {
	   for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	        	cells[row][col].color = true;
	        	cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
	         }
	   }
   }



/**
    * Initialize the puzzle number, status, background/foreground color,
    *   of all the cells from puzzle[][] and isRevealed[][].
    * Call to start a new game.
    */
   public void init() {
      // Get a new puzzle
      puzzle.newPuzzle(60);

      // Based on the puzzle, initialize all the cells.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
         }
      }
   }
   
   public void Reset() {
	   for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	        	 cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
						
					}
				}
			}
	   
   
  // Difficulty levels
   public void Advanced() {
	   //Get a new Puzzle
	   puzzle.newPuzzle(45);

	      // Based on the puzzle, initialize all the cells.
	      for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	            cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
	         }
	      } 
   }
   
   public void Expert() {
	   //Get a new Puzzle
	   puzzle.newPuzzle(35);

	      // Based on the puzzle, initialize all the cells.
	      for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	            cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
	         }
	      } 
   }
   
   public void Hell() {
	   //Get a new Puzzle
	   puzzle.newPuzzle(25);

	      // Based on the puzzle, initialize all the cells.
	      for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	            cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
	         }
	      } 
   }
   
 //Hints
   public void RevealAll() {
	      for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	        	 if(puzzle.isShown[row][col] == false) {
	        		 cells[row][col].setText(puzzle.numbers[row][col] + "");
	        		 cells[row][col].setEditable(false);
	        		 cells[row][col].setBackground(new Color(207, 207, 196));
	        		 cells[row][col].setForeground(Color.BLACK);
	        		 
	        	 }
	        	 
	         }
	      }
	   }
   
   public void RevealOne() {
	   for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	        	 if(puzzle.isShown[row][col] == false) {
	        		 puzzle.isShown[row][col] = true;
	        		 cells[row][col].setText(puzzle.numbers[row][col] + "");
	        		 cells[row][col].setEditable(false);
	        		 cells[row][col].setBackground(new Color(207, 207, 196));
	        		 cells[row][col].setForeground(Color.BLACK);
	        		 
	        		 return;
	        	 }	 
	        	 
	         }
	      }
   }
   

   

   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of NO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.NO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   // [TODO 2] Define a Listener Inner Class
   private class CellInputListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // Get a reference of the JTextField that triggers this action event
         Cell sourceCell = (Cell)e.getSource();

      // Retrieve the int entered
         int numberIn = Integer.parseInt(sourceCell.getText());
         // For debugging
         System.out.println("You entered " + numberIn);

         /*
          * [TODO 5]
          * Check the numberIn against sourceCell.number.
          * Update the cell status sourceCell.status,
          * and re-paint the cell via sourceCell.paint().
          */
          if (numberIn == sourceCell.number) {
            sourceCell.status = CellStatus.CORRECT_GUESS;
            String filepath = "correct.wav";
            SoundEffects correctSoundEffects = new SoundEffects();
            correctSoundEffects.playSound(filepath);
          } else {
        	 sourceCell.status = CellStatus.WRONG_GUESS;
        	 String filepath = "wrong.wav";
             SoundEffects wrongSoundEffects = new SoundEffects();
             wrongSoundEffects.playSound(filepath);
          }
          sourceCell.paint();

          /*
           * [TODO 6][Later] Check if the player has solved the puzzle after this move,
           *   by call isSolved(). Put up a congratulation JOptionPane, if so.
           */
          if(isSolved()) {
          
          //SoundEffects.WON.play();
          String filepath = "won.wav";
          SoundEffects congratsSoundEffects = new SoundEffects();
          congratsSoundEffects.playSound(filepath);
          
          ImageIcon WinIcon = new ImageIcon("ss.gif");
          JOptionPane.showMessageDialog(null, "Congratulations!", null, JOptionPane.INFORMATION_MESSAGE, WinIcon);

          }
          
      }
   }
}