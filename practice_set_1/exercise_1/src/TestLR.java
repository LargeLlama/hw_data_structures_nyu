// TestLR.java

// TestLR demonstrates the use of LocatedRect.

public class TestLR {
public static void main(String args[]) {
    LocatedRect LR1= new LocatedRect();
    LocatedRect LR2= new LocatedRect(1.0, 3.0, 1.0, 2.0);
    Rectangle Rect3 = new Rectangle(10.0, 15.0);
    System.out.println(LR1.toString());    // LR[0.0, 1.0, 0.0, 1.0]
    System.out.println(LR2.toString());    // LR[1.0, 5.0, 2.0, 3.0]
    System.out.println(Rect3.toString());  // Rect[10.0, 15.0]
    Rect3=LR2;
    System.out.println(Rect3.toString());  // Rect[10.0, 15.0]
	System.out.println("NonDestRotate of LR2, which is: " + LR2 + "\n" + LR2.NonDestRotate());
	System.out.println("LR2: " + LR2);
	LocatedRect LR3 = new LocatedRect(1.0, 3.0, 1.0, 2.0);
	System.out.println("DestRotate of LR3, which is: " + LR3 + "\n" + LR3.DestRotate());
//  LR1 = Rect3;   /// Note: This is  not allowed.
}  // end main
}  // end TestLR
