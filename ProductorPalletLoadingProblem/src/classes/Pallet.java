package classes;

import java.util.ArrayList;
import java.util.List;

public class Pallet {
	private int length;
	private int width;
	private int boxLength;
	private int boxWidth;
	private List<Box> boxesPlacement;
	public Pallet(int length, int width, int boxLength, int boxWidth){
		this.length = (length >= width) ? length : width;
		this.width = (width < length) ? width : length;
		this.boxLength = (boxLength >= boxWidth) ? boxLength : boxWidth;
		this.boxWidth = (boxWidth < boxLength) ? boxWidth : boxLength;
		this.boxesPlacement = new ArrayList<Box>();
		populateBoxes(new Point(0, 0), true);
	}
	private void populateBoxes(Point currentPoint, boolean hasSpace){
		if(hasSpace){
			Box box = new Box(currentPoint, new Point(currentPoint.getX() + this.boxLength,
				currentPoint.getY() + this.boxWidth));
			if(fits(box)){
				this.boxesPlacement.add(box);
				if(box.getUpRightPoint().getX() + this.boxLength > this.length &&
					box.getUpRightPoint().getX() + this.boxWidth > this.length){
					currentPoint = new Point(currentPoint.getX() - box.getBottomLeftPoint().getX(),
						currentPoint.getY() + box.getUpRightPoint().getY()- box.getBottomLeftPoint().getY());
					populateBoxes(currentPoint, hasSpace);
					if(this.boxesPlacement.size() < this.optimalSolution()){
						this.boxesPlacement.remove(box);
						box.switchOrientation();
						if(fits(box)){
							this.boxesPlacement.add(box);
							if(box.getUpRightPoint().getX() + this.boxLength > this.length ||
									box.getUpRightPoint().getX() + this.boxWidth > this.length){
									currentPoint = new Point(currentPoint.getX() - this.boxesPlacement.get(
										this.boxesPlacement.size()-1).getUpRightPoint().getX(),
										currentPoint.getY() + (this.boxesPlacement.get(this.boxesPlacement.size()-1)
											.getBottomLeftPoint().getY()- this.boxesPlacement.get(
											this.boxesPlacement.size()-1).getUpRightPoint().getY()));
									populateBoxes(currentPoint, hasSpace);
							}
							else{
								currentPoint = new Point(box.getUpRightPoint().getX()
									, box.getBottomLeftPoint().getY());
								populateBoxes(currentPoint, hasSpace);
							}
						}
					}
				}
				else{
					currentPoint = new Point(box.getUpRightPoint().getX()
						, box.getBottomLeftPoint().getY());
					populateBoxes(currentPoint, hasSpace);
					if(this.boxesPlacement.size() < this.optimalSolution()){
						this.boxesPlacement.remove(box);
						box.switchOrientation();
						if(fits(box)){
							this.boxesPlacement.add(box);
							if(box.getUpRightPoint().getX() + this.boxLength > this.length ||
									box.getUpRightPoint().getX() + this.boxWidth > this.length){
									currentPoint = new Point(currentPoint.getX() - this.boxesPlacement.get(
										this.boxesPlacement.size()-1).getUpRightPoint().getX(),
										currentPoint.getY() + (this.boxesPlacement.get(this.boxesPlacement.size()-1)
											.getBottomLeftPoint().getY()- this.boxesPlacement.get(
											this.boxesPlacement.size()-1).getUpRightPoint().getY()));
									populateBoxes(currentPoint, hasSpace);
							}
							else{
								currentPoint = new Point(box.getUpRightPoint().getX()
									, box.getBottomLeftPoint().getY());
								populateBoxes(currentPoint, hasSpace);
							}
						}
					}
				}
			}
			else {
				box.switchOrientation();
				if(fits(box)){
					this.boxesPlacement.add(box);
					if(box.getUpRightPoint().getX() + this.boxLength > this.length ||
						box.getUpRightPoint().getX() + this.boxWidth > this.length){
						currentPoint = new Point(currentPoint.getX() - this.boxesPlacement.get(
							this.boxesPlacement.size()-1).getUpRightPoint().getX(),
							currentPoint.getY() + (this.boxesPlacement.get(this.boxesPlacement.size()-1)
								.getBottomLeftPoint().getY()- this.boxesPlacement.get(
								this.boxesPlacement.size()-1).getUpRightPoint().getY()));
						populateBoxes(currentPoint, hasSpace);
					}
					else{
						currentPoint = new Point(box.getUpRightPoint().getX()
							, box.getBottomLeftPoint().getY());
						populateBoxes(currentPoint, hasSpace);
					}
				}
				else
					hasSpace = false;
			}
		}
	}
	private boolean fits(Box box){
		if((box.getBottomLeftPoint().getX() <= this.length - 
				(box.getUpRightPoint().getX() - box.getBottomLeftPoint().getX())) &&
			(box.getBottomLeftPoint().getY() <= this.width - 
				(box.getUpRightPoint().getY() - box.getBottomLeftPoint().getY())))
			return true;
		return false;
	}
	public List<Box> getBoxesPlacement(){
		return this.boxesPlacement;
	}
	public int foundSolution(){
		return this.boxesPlacement.size();
	}
	public int optimalSolution(){
		return new Double(((double)this.length/this.boxLength) 
				* ((double)this.width/this.boxWidth)).intValue();
	}
}