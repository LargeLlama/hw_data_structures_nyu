import java.util.Random;

public class TestBday {
	public static void main(String[] args) {
		Random rand = new Random();
		Bday bd = new Bday(rand);
		System.out.println("Value of day: " + bd.day);
		System.out.println("List pair: " + bd.convert(bd.day)[0] + ", " + bd.convert(bd.day)[1]);
		System.out.println(bd);
	}
}
