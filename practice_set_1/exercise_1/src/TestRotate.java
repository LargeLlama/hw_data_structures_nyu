public class TestRotate {

	public static void main (String args[]) {
		//Create a 2 by 5 Rectangle
		Rectangle rect1 = new Rectangle(2.0, 5.0);
		System.out.println("Rectangle 1: " + rect1);
		//Testing out NonDestRotate()
		System.out.println("Testing NonDestRotate()");
		System.out.println("Rotated rect1: " + rect1.NonDestRotate());
		System.out.println("Rectangle 1: " + rect1);
		//Testing out DestRotate()
		System.out.println("Testing out DestRotate()");
		System.out.println("Rotated (destructively) rect1: " + rect1.DestRotate());
		System.out.println("Rectangle 1: " + rect1);

		//Located Rectangle Testing
		LocatedRect lrect1 = new LocatedRect(1.0, 3.0, 2.0, 5.0);
		System.out.println("LocatedRectangle 1: " + lrect1);
		//Testing out NonDestRotate()
		System.out.println("Testing NonDestRotate()");
		System.out.println("Rotated lrect1: " + lrect1.NonDestRotate());
		System.out.println("Rectangle 1: " + lrect1);
		//Testing out DestRotate()
		System.out.println("Testing out DestRotate()");
		System.out.println("Rotated (destructively) lrect1: " + lrect1.DestRotate());
		System.out.println("Rectangle 1: " + lrect1);

	}
}
