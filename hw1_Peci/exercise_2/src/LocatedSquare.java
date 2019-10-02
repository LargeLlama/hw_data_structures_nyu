public class LocatedSquare extends Square {
	private double xL, yL;

	public LocatedSquare() {
		setCorner(0.0, 0.0);
	}

	public LocatedSquare(double x1, double y1, double side) {
		super(side);
		xL = x1;
		yL = y1;
	}

    public void setCorner(double xa, double ya) {
        xL=xa;  yL=ya; 
	}

	public double right() { return xL + xSpan; }
	public double left() { return xL; }
	public double top() { return yL + ySpan; }
	public double bottom() { return yL; }


    public String toString() {            
         return "LS[" + left() + ", " + right() + ". " +
                            bottom() + ", " + top() + "]"; }
         // toString() gives a printable format. Note this overrides
         // the toString() method in Square.
		 

}
