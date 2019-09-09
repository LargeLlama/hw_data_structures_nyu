public class Square extends Rectangle {
	public Square(double side) {
		xSpan = side;
		ySpan = side;
	}

	public Square() {
		this(1.0);
	}

	public double getSide() {
		return xSpan;
	}

	public double setSide(double side) {
		double tmp = xSpan;
		xSpan = side;
		return tmp;
	}

	public void setSpans(double x, double y) {
		System.out.println("You may not use setSpans to set the sides of a square!");
	}

	public String toString() {                   // Printable form
         return "Square["+ xSpan + ", " + ySpan + "]";
    }
}
