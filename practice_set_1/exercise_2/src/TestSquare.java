public class TestSquare {

	public static void main(String[] args) {
		Square sq1 = new Square();
		System.out.println("sq1: " + sq1);
		sq1.setSpans(2.0, 2.0);

		Square sq2 = new Square(3.0);
		System.out.println("sq2: " + sq2);
		System.out.println("sq2 old side: " + sq2.setSide(4.0) + "\nsq2 new side: " + sq2.getSide());

		LocatedSquare lsq1 = new LocatedSquare(2.0, 4.0, 4.0);
		System.out.println("lsq1: " + lsq1);
		System.out.println("moved bottom left to (3,5)");
		lsq1.setCorner(3.0, 5.0);
		System.out.println("lsq1: " + lsq1);
	}
}
