//TO DO: Nothing required here.

//******************************************************
//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
//*******  But do read this code (it will help!) *******
//******************************************************

import java.awt.Color;

/**
 *  Representation of empty space.
 *  
 *  @author K. Raven Russell
 */
public class Empty extends Element {
	/**
	 *  The number of times color will return
	 *  the same value before changing.
	 */
	private final static int TIME = 2000;
	
	/**
	 *  How many times color has returned the
	 *  same value so far.
	 */
	private int counter;
	
	/**
	 *  The current color of this part of the void.
	 */
	private Color currentColor;
	
	/**
	 *  Initializes this element of empty void.
	 */
	public Empty() {
		counter = (int)(Math.random()*TIME);
		currentColor = getRandomColor();
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		if(counter == 0) currentColor = getRandomColor();
		counter = (counter+1) % TIME;
		return currentColor;
	}
	
	/**
	 *  Returns a random shade of dark grey.
	 *  
	 *  @return a shade of grey
	 */
	private Color getRandomColor() {
		int val = (int)(Math.random()*20);
		return new Color(val, val, val);
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int getWeight() {
		return 0;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		//void doesn't fall
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		//empty void is automatically compressed into nothingness
		return true;
	}
}