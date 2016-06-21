package classes;

public class Box {
	private Point bottomLeftPoint;
	private Point upRigthPoint;
	public Box(Point bottomLeftPoint, Point upRightPoint){
		this.bottomLeftPoint = bottomLeftPoint;
		this.upRigthPoint = upRightPoint;
	}
	public Point getBottomLeftPoint() {
		return this.bottomLeftPoint;
	}
	public Point getUpRightPoint() {
		return this.upRigthPoint;
	}
	public void switchOrientation(){
		this.upRigthPoint.setX(this.bottomLeftPoint.getX()+this.upRigthPoint.getY());
		this.upRigthPoint.setY(this.bottomLeftPoint.getY()+this.upRigthPoint.getX());
	}
	public boolean equals(Box box){
		return (this.bottomLeftPoint.getX() == box.getBottomLeftPoint().getX() &&
				this.bottomLeftPoint.getY() == box.getBottomLeftPoint().getY()) &&
			   (this.upRigthPoint.getX() == box.getUpRightPoint().getX() &&
				this.upRigthPoint.getY() == box.getUpRightPoint().getY());
	}
	public String toString(){
		return "["+this.bottomLeftPoint+", "+this.upRigthPoint+"]\n";
	}
}
