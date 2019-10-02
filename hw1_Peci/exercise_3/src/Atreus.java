// Atreus.java

// Atreus demonstrates the use of Person by constructing a small part of
// the family tree for the House of Atreus

public class Atreus {
public static void listChildren(Person p) {
    System.out.println("Chidren of " + p.getName());
    for (int i=0; i < p.getNumChildren(); i++) {
       System.out.println("  " + p.getChildren()[i].getName());
     }
} // end listChildren


public static void main(String args[]) {
    Person atreus = new Person("Atreus");
    Person agamemnon = new Person("Agamemnon");
    Person clytemnestra = new Person("Clytemnestra");
    Person orestes = new Person("Orestes ");
    Person electra = new Person("Electra");
    Person iphigenia= new Person("Iphigenia");
    agamemnon.setParent(atreus);
    orestes.setParent(agamemnon);
    orestes.setParent(clytemnestra);
    electra.setParent(agamemnon);
    electra.setParent(clytemnestra);
    iphigenia.setParent(agamemnon);
    iphigenia.setParent(clytemnestra);
    listChildren(agamemnon);
}  // end main
}  // end Atreus
