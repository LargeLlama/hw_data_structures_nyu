/* SList.java
 *		List with head and tail Sentinels
 *
 *			-- Please study carefully the design!
 *
 *
 * Chee Yap, CS 102
 * Fall 2019
 *
 **************************************************/

import java.util.Random;

class SList {
	// INNER CLASS: ========================================
		class Node {
			// MEMBERS: ====================================
				int data;
				Node next;
			// CONSTRUCTORS: ===============================
				Node(int d) {data=d;}
				Node(int d, Node n) {data=d; next=n;}
			// METHODS: ====================================
		}//inner class
	// MEMBERS: ========================================
		Node head, tail;
	// CONSTRUCTORS: ===================================
		SList(){
			tail = new Node(Integer.MAX_VALUE);		 // tail sentinel 
			head = new Node(Integer.MIN_VALUE,tail); // head sentinel 
			tail.next = head; // tail.next points to the "last node"
		}
	// METHODS: =========================================
		void add(int d){
			addTail(d); }
		void addTail(int d){
			tail.next.next = new Node(d,tail);
			tail.next = tail.next.next;
		}
		void addHead(int d){
			head.next= new Node(d,head.next);
			if (tail.next==head) tail.next = head.next;
		}
		void show(String msg){
			for (Node u=head.next; u != tail; u=u.next)
				System.out.printf(" %d", u.data);
		 	System.out.println(msg);
		}
	// MAIN METHOD: ====================================
	public static void main (String[] args) {
		 int s = (args.length > 0) ?  Integer.valueOf(args[0]): 111;
		 int n = (args.length > 1) ?  Integer.valueOf(args[1]) : 9;
	
		 Random gen = (s==0)? new Random() : new Random(s);
		
		 SList tailList = new SList();
		 SList headList = new SList();
		 for (int i =0; i<n; i++) {
			int d = gen.nextInt(100);
		 	tailList.addTail(d);
		 	headList.addHead(d);
		 }
		 
		 System.out.println("List constructed with addTail:");
		 tailList.show("");
		 System.out.println("List constructed with addHead:");
		 headList.show("");
	 }// main
}//class SList

