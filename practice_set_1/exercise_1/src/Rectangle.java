// Rectangle.java
// A ``floating'' rectangle has a width xSpan and a height ySpan

public class Rectangle {
    protected double xSpan, ySpan;
    public Rectangle(double x, double y) { // Contructor with two args
        if (x < 0.0) x = 0.0;  // Allow degenerate rectangles
        if (y < 0.0) y = 0.0;  // but not inverted rectangles
        xSpan = x; ySpan = y; 
         }
    public Rectangle() {this(1.0,1.0);} // Constructor with no args
    public void setSpans(double x, double y) {   // Setter
        if (x < 0.0) x = 0.0;  // Allow degenerate rectangles
        if (y < 0.0) y = 0.0;  // but not inverted rectangles
        xSpan=x; ySpan=y; }    

    public double getXSpan() {return xSpan;}     // Getters
    public double getYSpan() {return ySpan;}

    public String toString() {                   // Printable form
         return "Rect["+ xSpan + ", " + ySpan + "]";
    }

	//NonDestRotate returns an entirely new instance of Rectangle 
	//With swapped x and y spans, which creates a new Rectangle that is rotated 90 degrees
    public Rectangle NonDestRotate() {
		return new Rectangle(getYSpan(), getXSpan());
	}

	//DestRotate returns the exact same Rectangle
	//It modifies the spans of this rectangle, and it doesn't create a copy
	public Rectangle DestRotate() {
		this.setSpans(getYSpan(), getXSpan());
		return this;
	}

} // end Rectangle

