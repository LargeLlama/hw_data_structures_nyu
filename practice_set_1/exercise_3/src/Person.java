public class Person {
// Constant
private static final int maxChildren = 20;
// Fields
    private String name;
    private Person parent1; // Biological parents
    private Person parent2;
    private int numChildren = 0;
    private Person[] children;
	private Person spouse;

// Constructor
    public Person(String n) {
        name = n;
        numChildren = 0;
        children = new Person[maxChildren];
      }

// Methods
     public String getName() { return name; }
     public Person getParent1() { return parent1;}
     public Person getParent2() { return parent2;}
     public Person[] getChildren() { return children;}
     public int getNumChildren() { return numChildren;}
	 public Person getSpouse() { return spouse; }
	 private void setSpouse(Person q) { this.spouse = q; }

// Setter. When you call setParent(q), q gets marked as a parent of this and
// this gets added to q's children. This returns true if successful and false
// if unsuccessful.

     public boolean setParent(Person q) {
         boolean succeed = false;
         if (q == null) System.out.println("Parent is null");
            else if (q == this) 
                    System.out.println("A person cannot be their own parent");
            else if (parent2 != null) 
                    System.out.println(name + " already has two parents.");
            else if (q.numChildren >= maxChildren)
                    System.out.println(q.name + " already has " +
                          maxChildren + " children.");
            else  {
             q.children[q.numChildren] = this;
             q.numChildren = q.numChildren + 1;
             if (parent1 == null) parent1 = q;
               else parent2 = q;
            succeed = true;
               }
        return succeed;
      } // end SetParent

	 public boolean marry(Person q) {
		 if (q == null) {
			 System.out.println("Spouse is null");
			 return false;
		 } else if (this.getSpouse() != null || q.getSpouse() != null) {
			 System.out.println("One person is already married!");
			 return false;
		 } else if (this.getParent1() == q || this.getParent2() == q || q.getParent1() == this || q.getParent2() == this) {
			 System.out.println("What is wrong with you...");
			 return false;
		 }	 
		 this.setSpouse(q);
		 q.setSpouse(this);
		 return true; 
	 }

	 public void divorce() {
		 if (this.getSpouse() != null)
		 	this.getSpouse().setSpouse(null);
		 this.setSpouse(null);
	 }

	 public String toString() {
		 return name;
	 }
} // end Person
