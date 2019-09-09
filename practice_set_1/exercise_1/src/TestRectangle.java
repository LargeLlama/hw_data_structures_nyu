// TestRectangle.java

// TestRectangle demonstrates the use of Rectangle.

public class TestRectangle {
public static void main(String args[]) {
    Rectangle rect1 = new Rectangle(1.0, 2.0);
    Rectangle rect2 = new Rectangle(4.0,5.0);
    System.out.println(rect1.toString());
    System.out.println(rect2.toString());
	System.out.println("NonDestRotate: " + rect1.NonDestRotate() + "\nrect1: " + rect1);
	System.out.println("DestRotate: " + rect2.DestRotate() + "\nrect2: " + rect2);
}  // end main
}  // end TestRectangle
