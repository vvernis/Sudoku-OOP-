package sudoku;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;

/**
 * Customize (subclass) the JTextField to include
 * row/column numbers and status of each cell.
 */
public class Cell extends JTextField {
   // Name-constants for JTextField's colors and fonts
	// light mode
   public static final Color BG_SHOWN0 = new Color(188, 211, 213); // blue
   public static final Color FG_SHOWN0 = Color.BLACK;
   public static final Color FG_NOT_SHOWN0 = new Color(103, 108, 112);
   public static final Color BG_NO_GUESS0 = new Color(198, 176, 188); //purple
   public static final Color BG_CORRECT_GUESS0 = new Color(208, 240, 192); //mint
   public static final Color BG_WRONG_GUESS0 = new Color(241,145,155); //pink
   	
   //dark mode
   public static final Color BG_SHOWN1 = new Color(00, 00, 80); // blue
   public static final Color FG_SHOWN1 = new Color(211, 211, 211);
   public static final Color FG_NOT_SHOWN1 = new Color(253, 238, 0);
   public static final Color BG_NO_GUESS1 = new Color(198, 176, 188); //purple
   public static final Color BG_CORRECT_GUESS1 = new Color(23, 74, 67); //mint
   public static final Color BG_WRONG_GUESS1 = new Color(241,145,155); //pink
   
   public static final Font FONT_NUMBERS = new Font("Courier", Font.BOLD, 24);
	

   // All variables have package access
   int row, col;  // The row and column number [0-8] of the cell
   int number;    // The puzzle number [1-9] for this cell
   CellStatus status; // An enumeration defined in CellStatus
   boolean color = true;
   
   // Constructor
   public Cell(int row, int col) {
      super();   // JTextField
      this.row = row;
      this.col = col;
      // Inherited from JTextField: Beautify all the cells once for all
      super.setHorizontalAlignment(SwingConstants.CENTER);
      super.setFont(FONT_NUMBERS);  // default font
   }

   // Initialize the cell for a new game, given the puzzle number and isShown
   public void init(int number, boolean isShown) {
      this.number = number;
      status = isShown ? CellStatus.SHOWN : CellStatus.NO_GUESS;
      paint();
   }
   
   //border & grid colours    
   Border rowupGrid = BorderFactory.createMatteBorder(0,0,5,0,new Color(112,128,144)); //slate grey
   Border rowdownGrid = BorderFactory.createMatteBorder(5,0,0,0, new Color(112,128,144));
   Border colleftGrid = BorderFactory.createMatteBorder(0,0,0,5, new Color(112,128,144));
   Border colrightGrid = BorderFactory.createMatteBorder(0,5,0,0, new Color(112,128,144));
   Border downright = BorderFactory.createMatteBorder(0,0,5,5, new Color(112,128,144)); 
   Border upright = BorderFactory.createMatteBorder(5,0,0,5, new Color(112,128,144));
   Border upleft = BorderFactory.createMatteBorder(5,5,0,0, new Color(112,128,144));
   Border downleft = BorderFactory.createMatteBorder(0,5,5,0, new Color(112,128,144));
   Border inner = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
 

   // This Cell (JTextField) paints itself based on the status
   public void paint() {
		   	super.setBorder(inner);
		   	//borders and grid
		   	if ((row == 2 && col == 2) || (row == 2 && col == 5) || (row == 5 && col == 2) || (row == 5 && col == 5) || (row == 8 && col == 2) || (row == 8 && col ==5) || (row == 8 && col == 8) || (row == 2 && col == 8) || (row == 5 && col == 8)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(downright ,inner));
		   	}
		   	if((row == 0 && col == 2 || row == 0 && col == 5) || (row == 0 && col == 8)) { 
		   		super.setBorder(BorderFactory.createCompoundBorder(upright ,inner));
		   	}   	
		   	if(row == 0 && col == 0) {
		   		super.setBorder(BorderFactory.createCompoundBorder(upleft ,inner));
		   	}   	
		   	if((row == 8 && col == 0) || (row == 2 && col == 0) || (row == 5 && col == 0)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(downleft ,inner));
		   	}
		   	if ((row == 8 && col == 1) || (row == 8 && col == 3)  || (row == 8 && col == 4) || (row == 8 && col == 6) || (row == 8 && col == 7) || (row == 2 && col == 1) || (row == 2 && col == 3)  || (row == 2 && col == 4) || (row == 2 && col == 6) || (row == 2 && col == 7) || (row == 5 && col == 1) || (row == 5 && col == 3) ||  (row == 5 && col == 4) || (row == 5 && col == 6) || (row == 5 && col == 7)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(rowupGrid ,inner));
		   	}
		   	if ((row == 1 && col == 2) || (row == 3 && col == 2) || (row == 4 && col == 2) || (row == 6 && col == 2) || (row == 7 && col == 2) || (row == 1 && col == 5) || (row == 3 && col == 5) || (row == 4 && col == 5) || (row == 6 && col == 5) || (row == 7 && col == 5) || (row == 1 && col == 8) || (row == 3 && col == 8) || (row == 4 && col == 8) || (row == 6 && col == 8) || (row == 7 && col == 8)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(colleftGrid ,inner));
		   	}
		   	if ((row == 0 && col == 1) || (row == 0 && col == 3)  || (row == 0 && col == 4) || (row == 0 && col == 6) || (row == 0 && col == 7)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(rowdownGrid ,inner));
		   	}
		   	if ((row == 1 && col == 0) || (row == 3 && col == 0)  || (row == 4 && col == 0) || (row == 6 && col == 0) || (row == 7 && col == 0)) {
		   		super.setBorder(BorderFactory.createCompoundBorder(colrightGrid ,inner));
		   	}
			 
		//display
      if (status == CellStatus.SHOWN) {
         // Inherited from JTextField: Set display properties
         if (color == true) {
         super.setBackground(BG_SHOWN1);
         super.setForeground(FG_SHOWN1);
         super.setText(number + "");
         super.setEditable(false);
         }
         if (color == false) {
        	 super.setBackground(BG_SHOWN0);
        	 super.setForeground(FG_SHOWN0);
        	 super.setText(number + "");
             super.setEditable(false);
         }
      } else if (status == CellStatus.NO_GUESS) {
         // Inherited from JTextField: Set display properties
    	 if (color == true) {
         super.setText("");
         super.setEditable(true);
         super.setBackground(BG_NO_GUESS1);
         super.setForeground(FG_NOT_SHOWN1);
    	 }
    	 else {
    	 super.setText("");
         super.setEditable(true);
         super.setBackground(BG_NO_GUESS0);
         super.setForeground(FG_NOT_SHOWN0);
    		 
    	 }
      } else if (status == CellStatus.CORRECT_GUESS) {
    	 if (color == true) {
         super.setEditable(true);
         super.setBackground(BG_CORRECT_GUESS1);
    	 }
    	 else {
    	 super.setEditable(true);
         super.setBackground(BG_CORRECT_GUESS0);
    	 }
      } else if (status == CellStatus.WRONG_GUESS) {
    	 if (color == true) {
         super.setEditable(true);
         super.setBackground(BG_WRONG_GUESS1);
    	  }
    	 else {
    	 super.setEditable(true);
         super.setBackground(BG_WRONG_GUESS0);
    	  }
      }
   }
}