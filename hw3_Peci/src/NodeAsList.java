/* file: NodeAsList.java
 *
 * Simple List Class
 *		-- view every node as the head of a list!
 * Exercise:
 *		-- add methods for removeHead() and removeTail()
 *
 *	Chee Yap (CS 102, Data Structure)
 *	Fall 2019
 *****************************************/

import java.util.Random;

class Node {
	// MEMBERS: ========================================
		int data;
		Node next;
	// CONSTRUCTOR: ====================================
		Node(int d) {data=d;}
	// METHODS: ========================================
		void show(String s){
			System.out.println(s);
			Node u = this;
			while (u!=null){
				System.out.printf("%d ", u.data);
				u = u.next;
			}
			System.out.printf("\n\n");
		}//show
}//Node class
	
class NodeAsList {
	// MAIN METHOD: ====================================
	public static void main (String[] args) {
		 int ss = (args.length > 0) ?  Integer.valueOf(args[0]): 111;
		 int nn = (args.length > 1) ?  Integer.valueOf(args[1]) : 9;
	
		 Random gen = (ss==0)? new Random() : new Random(ss);
		
		 // "--MAINTAIN YOUR OWN LIST--"
		 Node myList = new Node(gen.nextInt(nn));
		 Node lastNode = myList;
		
		 for (int i =1; i<nn; i++){
		 	lastNode.next = new Node(gen.nextInt(nn));
		 	lastNode = lastNode.next;
		 }
		 myList.show("This is the list");
	 }// main
}//class RandomList

