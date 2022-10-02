import java.awt.Color;
/**
 * This is the sand class.
 * @author Matthew Emo
 *
 */
public class Sand extends Element{
	/**
	 * The color is yellow.
	 */
	@Override
	public Color getColor() {
		return Color.yellow;
	}
	/**
	 * The weight is heavier then water and empty but less then metal.  
	 */
	@Override
	public int getWeight() {
		return 100;
	}
	/**
	 * This first tries to fall down, left, right.
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		
		if(grid.size()>row+1) {
			if(grid.get(row).get(col).compareTo(grid.get(row+1).get(col)) >0) {
				grid.get(row).set(col, grid.get(row+1).get(col));
				grid.get(row+1).set(col, new Sand());
					
			}
		}
		
		//move left
		if(grid.size()>row+1 && col-1>=0) {
			
			if(grid.get(row).get(col).compareTo(grid.get(row+1).get(col-1)) >0) {
				grid.get(row).set(col, grid.get(row+1).get(col-1));
				grid.get(row+1).set(col-1, new Sand());
			}
		}
		//move right
		if(grid.size()>row+1 && grid.get(row).size() >col+1) {
			if(grid.get(row).get(col).compareTo(grid.get(row+1).get(col+1)) >0) {
				grid.get(row).set(col, grid.get(row+1).get(col+1));
				grid.get(row+1).set(col+1, new Sand());
			}
		}

		
	}
	/**
	 * Class pushup then pushleft and returns the results. 
	 */
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		if(pushUp(grid, row, col)){
			return true;
		} else if (pushLeft(grid, row, col)){
			return true;
		}
		return false;
		
	}
	
	/**
	 * main method.
	 * @param args main method
	 */
	public static void main(String args[]){
		//This is an outline of how to test that an element
		//is doing what you want it to (without using the
		//simulator. You should definitely write similar tests
		//for Sand and Water.
		
		//All tests work like this:
		//    (1) setup a scenario
		//    (2) call some method(s) that will change (or not change) the scenario
		//    (3) check that what should happen did happen
		//Example:
		
		
		//(1) setup a scenario
		
		//create a grid and a piece of Metal
		DynamicArray<DynamicArray<Element>> grid = new DynamicArray<>();
		//todo: initialize the grid to a 3x3 area
		DynamicArray<Element> row1 = new DynamicArray<>(3);
		DynamicArray<Element> row2 = new DynamicArray<>(3);
		DynamicArray<Element> row3 = new DynamicArray<>(3);
		grid.add(row1);
		grid.add(row2);
		grid.add(row3);
		System.out.println(grid.get(1).get(2));
		
		Sand m = new Sand();
		
		//put the element in the middle
		grid.get(0).set(0, m);
		System.out.println(grid.get(0).get(0));
		
		//(2) call some method(s) that will change (or not change) the scenario
		m.fall(grid, 1, 1);
		
		
		//(3) check that what should happen did happen
		
		//in this case, the metal should not have moved
		//if it was sand, it would be at grid.get(2).get(1)
		//if it was water, it might be at grid.get(1).get(0), grid.get(1).get(2), or grid.get(2).get(1)
		
		//if(grid.get(1).get(1) == m) {
		//^ carful about == here, that's checking for the same memory location...
		//	System.out.println("Yay");
		//}
		
		//make more scenarios or continue this one!
	}
	
}
