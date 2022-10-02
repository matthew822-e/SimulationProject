import java.awt.Color;
/**
 * Water class for simulation.
 * @author Matthew Emo
 *
 */
public class Water extends Element{
	
	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public int getWeight() {
		return 50;
	}

	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		
		int choice = (int)(Math.random()*3);
		
		//go down
		if(grid.size()>row+1 && choice==0) {
			if(grid.get(row).get(col).compareTo(grid.get(row+1).get(col)) >0) {
				grid.get(row).set(col, new Empty());
				grid.get(row+1).set(col, new Water());
				
			}
		}
		
		//go left
		if(grid.size()>row+1 && col-1>=0 && choice==1) {
			if(grid.get(row).get(col).compareTo(grid.get(row).get(col-1)) >0) {
				grid.get(row).set(col, new Empty());
				grid.get(row).set(col-1, new Water());
	
			}
		}
		//go right
		if(grid.size()>row+1 && grid.get(row).size() >col+1 && choice==2) {
			if(grid.get(row).get(col).compareTo(grid.get(row).get(col+1)) >0) {
				grid.get(row).set(col, new Empty());
				grid.get(row).set(col+1, new Water());
			}
			
		}
		
		
		
	}

	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		if(pushUp(grid, row, col)){
			return true;
		} else if (pushLeft(grid, row, col)){
			return true;
		}
		return false;
		

	}

}
