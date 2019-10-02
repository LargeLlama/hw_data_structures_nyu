public class TestPerson {
	public static void main(String[] args) {
		Person father = new Person("dad");
		Person mother = new Person("mom");
		System.out.println("Dad's wife: " + father.getSpouse());
		System.out.println("Mom's hubby: " + mother.getSpouse());
		System.out.println("Setting's dad's wife to be mom, status: " + father.marry(mother));
		System.out.println("Dad's  spouse: " + father.getSpouse() + "\nMom's spouse: " + mother.getSpouse());
		System.out.println("Mommy and daddy are having problems.....");
		System.out.println("INITIATING DIVORCE!");
		mother.divorce();
		System.out.println("Dad's spouse : " + father.getSpouse() + "\nMom's spouse: " + mother.getSpouse());
	}
}
