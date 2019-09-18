// LocatedRect.java
//
// A LocatedRect is a rectangle with a location in
// space (unlike a Rectangle which has only a height and width)
//

public class LocatedRect extends Rectangle {
    private double xL, yL; // lower left hand corner.
    public LocatedRect() {    // Constructor
        setCorner(0.0, 0.0); }
    public LocatedRect(double x1, double x2, double y1, 
                             double y2) {  // Constructor
        super(x2-x1,y2-y1); // Calls constructor for Rectangle
        xL=x1;
        yL=y1;  }
    public void setCorner(double xa, double ya) {   // Setter
        xL=xa;  yL=ya; }
    public double right() { return xL+xSpan; }      // Getters
    public double left() { return xL; }
    public double top() { return yL+ySpan; }
    public double bottom() { return yL; }
    public String toString() {            
         return "LR[" + left() + ", " + right() + ". " +
                            bottom() + ", " + top() + "]"; }
         // toString() gives a printable format. Note this overrides
         // the toString() method in Rectangle.

	public LocatedRect NonDestRotate() {
		return new LocatedRect(right(), right() + ySpan, yL, yL + xSpan);
	}

	public LocatedRect DestRotate() {
		setCorner(right(), yL);
		double tmp = super.xSpan;
		super.xSpan = ySpan;
		super.ySpan = tmp;
		return this;
	}

} // end LocatedRect
            
