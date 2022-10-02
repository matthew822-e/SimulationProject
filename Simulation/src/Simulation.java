//TO DO: Add your name (as an author), complete the required methods.

/**
 *  The simulator. This tracks the elements in a grid
 *  and coordinates that with the display.
 *  
 *  @author Dave Feinberg, K. Raven Russell, and [Your Name Here]
 */
public class Simulation {
	
	//******************************************************
	//*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
	//*******        But do read this section!       *******
	//******************************************************
	
	/**
	 *  The default number of rows the grid should have.
	 */
	public static final int INIT_ROWS = 120;
	
	/**
	 *  The default number of columns the grid should have.
	 */
	public static final int INIT_COLS = 80;

	/**
	 *  The grid that represents the location of each element.
	 */
	private final DynamicArray<DynamicArray<Element>> grid;
	
	/**
	 *  The GUI display.
	 */
	private final Display display;
	
	//******************************************************
	//* END DO-NOT-EDIT SECTION -- DO NOT ADD MORE FIELDS! *
	//******************************************************
	
	/**
	 *  Sets up the instance variables (grid and display).
	 *  
	 *  @param withDisplay whether or not the display should be created (for testing purposes)
	 */
	public Simulation(boolean withDisplay) {
		// Initialize the grid (see above) to the default size (see above).
		// Fill the grid with empty void.
		grid = new DynamicArray<DynamicArray<Element>>();
		for (int i=0; i<INIT_ROWS; i++) 
		{
			
			DynamicArray<Element> ire = new DynamicArray<>(INIT_COLS);
			grid.add(ire);
			for(int k=0; k<INIT_COLS; k++)
			{
				grid.get(i).add(new Empty());
			}
		}
		// If the simulation should be created with a display, then set display
		// (see above) to a new display. Use the title "Project 1 Simulation",
		// the default number of rows and columns (see above), and the display
		// constructor (see Display.java) to do this.
		if (withDisplay) 
		{
			display = new Display("Project 1 Simulation",INIT_ROWS,INIT_COLS);
		} else {
			display = null;
		}
		// If the simulation should be created without a display, then initialize
		// the display to null (or Java will yell at you).
		
		
		
		
	}

	/**
	 *  This is called when the user clicks on a location using the given tool.
	 *  
	 *  @param row the row where the action happened
	 *  @param col the column where the actio happened
	 *  @param newElem the element the user has created to put there
	 */
	public void locationClicked(int row, int col, Element newElem) {
		// Put the new element in the grid at the row and column indicated.
		grid.get(row).set(col, newElem);
	}

	/**
	 *  Copies each element of grid's color into the display.
	 */
	public void updateDisplay() {
		// Update each cell of the display (see above) with
		// the correct color for that cell. The display has one
		// cell per grid element (one-to-one) and the color of
		// the cell is just the color of the element from the grid.
		
		for(int i =0; i<grid.size(); i++)
		{
			for(int k =0; k<grid.get(i).size(); k++)
			{
				display.setColor(i, k, grid.get(i).get(k).getColor());
			}
		}
		
		// Remember: Display has a setColor(row, col, color) method,
		// and elements have a getColor() method.
	}
	
	/**
	 *  Resizes the grid (if needed) to a bigger or smaller size.
	 *  
	 *  @param numRows the new number of rows the grid should have
	 *  @param numCols the new number of columns the grid should have
	 *  @return whether or not anything was changed
	 */
	public boolean resize(int numRows, int numCols) {
		// This method might be called when there is no change... careful!
		
		// Parameter values are validated by display, but you
		// should double check the numbers of rows/columns is
		// at least 1 here. Ignore the resize request if an
		// invalid value is given (don't crash the program...).
		

		// Add/remove all of _one_ column or row at a time until
		//    the correct size is achieved. For example, if you
		//    need to add 6 columns, DO NOT add six elements to
		//    row 1, then 6 elements to row 2. Instead, add one
		//    element to each row to complete the column, then
		//    start on the next column.
		// Multiple things might change! (Happens when dragging
		//    the corners of the window...). In that case,
		//    add/remove all rows before any columns
		
		// Rows are added to (and removed from) the top, while
		// columns are added to (and removed from) on the right.
		
		// Added space is just filled with empty void.
		
		
		if(numRows<1 && numCols<1) {
			return false;
		}
		
		int rowDifference = numRows - grid.size();
		pullDown(numRows ,numCols , rowDifference);
		rowDifference = grid.size() - numRows;	
		pullup(numRows, numCols, rowDifference);
		int colsDifference = numCols - grid.get(0).size();
		pullRight(numRows, numCols, colsDifference);
		pullleft(numRows, numCols);
		
		// Removed rows are just removed.
		
		// Removed columns work as follows:
		//    If the removed element is as heavy (or heavier)
		//        than the element to the left, it will try
		//        to push that element to the left. If it
		//        can push it, then it takes its place.
		//        Remember: the push() method will move the
		//        element if possible, don't reinvent the wheel!
		//    If the removed element is lighter or can't
		//        be pushed, the removed element is just lost.
		
		// It would be very, very wise to make yourself some private
		// helper methods to do this part of the project.
		//
		return true;
	}
	/**
	 * If you pull down on the GUI it will increase the size of the grid starting at the top. 
	 * @param numRows number if rows of the new array
	 * @param numCols number if cols of the new array
	 * @param rowDifference the difference in the old array vs the new array
	 * @return if it was successful 
	 */
	private boolean pullDown(int numRows, int numCols , int rowDifference) {	
		for(int i = 0; i<rowDifference; i++) {
			DynamicArray<Element> ire = new DynamicArray<>(numCols);;
			for(int j=0; j<grid.get(0).size(); j++) {
				ire.add(new Empty());
			}
			grid.add(0, ire);
		}
		return true;
	}
	/**
	 * If you pull right on the GUI it will increase the size of the grid starting at the right. 
	 * @param numRows number if rows of the new array
	 * @param numCols number if cols of the new array
	 * @param colsDifference the difference in the old array vs the new array
	 * @return if it was successful
	 */
	private boolean pullRight(int numRows, int numCols,int colsDifference) {
		for(int i = 0; i<colsDifference; i++) 
		{
			for(int j=0; j<numRows ; j++) {
				grid.get(j).add(new Empty());
			}
		}
		return true;
	}
	/**
	 * If you pull up on the GUI it will decrease the size of the grid starting at the top. 
	 * @param numRows number if rows of the new array
	 * @param numCols number if cols of the new array
	 * @param rowDifference the difference in the old array vs the new array
	 * @return if it was successful 
	 */
	private boolean pullup(int numRows, int numCols, int rowDifference) {	
		for(int i = 0; i<rowDifference; i++) {
			grid.remove(0);
		}
		return true;
	}
	// Removed columns work as follows:
	//    If the removed element is as heavy (or heavier)
	//        than the element to the left, it will try
	//        to push that element to the left. If it
	//        can push it, then it takes its place.
	//        Remember: the push() method will move the
	//        element if possible, don't reinvent the wheel!
	//    If the removed element is lighter or can't
	//        be pushed, the removed element is just lost.
	
	/**
	 * If you pull left on the GUI it will decrease the size of the grid starting at the right. 
	 * @param numRows number if rows of the new array
	 * @param numCols number if cols of the new array
	 * @return if it was successful 
	 */
	private boolean pullleft(int numRows, int numCols) {
		
		if (this.grid.get(numRows-1).get(numCols-1).push(this.grid,numRows-1, numCols-1)) {
			
					
		} else {
			int i=0;
			// if the grid did not move delete the 1 col from every row
			//col
			for (int k=grid.get(i).size(); k>0 ; k--) 
			{
				;
				//start at the top row
				for(i=0; i<grid.size(); i++) 
				{	

					grid.get(i).remove(k);
				}
			}		
		}
		
		return true;
	}
	
	
	@Override
	public String toString(){
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the list i for easy viewing.
		StringBuilder s = new StringBuilder("Dynamic array with " + grid.size()
			+ " items and a capacity of " + grid.capacity() + ":");
		/*
		for(int i= 0; i<grid.size(); i++) {
			s.append("row: "+ "[" + i + "]");
			for(int k=0; k<grid.get(i).size(); k++) {
				
				s.append(grid.get(i).get(k) + " ");
			}
			
				
		}
		*/
		return s.toString();
		
	}
	
	/**
	 *  Indicates the private post where you have shown off your
	 *  new element(s).
	 *  
	 *  @return the post where you describe your new element
	 */
	public static String newElementPost() {
		//[GUI:Advanced] change this to return the FULL URL of
		//the post where the grader can find your new element
		//demo, but ONLY if you completed the [GUI:Advanced] part
		//of the project.
		return null;
	}
	
	//******************************************************
	//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
	//*******        But do read this section!       *******
	//******************************************************

	/**
	 *  Causes one random particle to maybe do something. Called
	 *  repeatedly.
	 */
	public void step() {
		int row = (int)(Math.random()*grid.size());
		int col = (int)(Math.random()*grid.get(row).size()) ;
		
		grid.get(row).get(col).fall(grid, row, col);
	}
	
	/**
	 *  Game loop of the program (step, redraw, handlers, etc.).
	 */
	public void run() {
		while (true) {
			//step
			for (int i = 0; i < display.getSpeed(); i++) step();
			
			//redraw everything
			updateDisplay();
			display.repaint();
			
			//wait for redrawing and for mouse
			display.pause(1);
			
			//handle person actions (resize and tool usage)
			if(display.handle(this) && display.pauseMode()) {
				//for debugging
				updateDisplay();
				display.repaint();
				display.pause(5000);
			}
		}
	}
	
	/**
	 *  Convenience method for GTA testing. Do not use this in
	 *  your code... for testing purposes only.
	 *  
	 *  @return the private grid element
	 */
	public DynamicArray<DynamicArray<Element>> getGrid() {
		return grid;
	}
	
	/**
	 *  Main method that kicks off the simulator.
	 *  
	 *  @param args not used
	 */
	public static void main(String[] args) {
		(new Simulation(true)).run();
	}
}
