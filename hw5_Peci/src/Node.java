/*
 *  Node Class -- for singly linked list
 *
 *		-- randomness is built in (with Random rg)
 *		-- gen(n) will generate a random list of length n
 *
 *  Fall 2019, CS102
 *  Chee Yap
 **************************************************/ 
import java.util.Random;

class Node {
//////////////////////////////// MEMBERS:
		int val;
		Node next;
		static Random rg = new Random(0);
		static int range = 100;		// range for generating values
//////////////////////////////// CONSTRUCTORS:
		Node(){
			val = rg.nextInt(range);
		}
		Node(int v){
			val = v;
		}
		Node(int v, Node u){
			this(v); next=u;
		}
//////////////////////////////// METHODS:
		// Show the list, preceded by a message:
		void show (String msg){	
			if (msg!=null)
				System.out.println(msg);
			Node u=this;
			do {
				System.out.printf("%d ", u.val);
				u=u.next;
			} while (u!=null);
			System.out.println("");
		}//show
		void show (){
			show(null);
		}
		// Generate a list of random int's of size n.
		// Fixed using Tianqi's post in Piazza :)
		static Node gen(int n) {
			if (n < 1)
				throw new IllegalArgumentException();
			Node head = new Node(rg.nextInt(range));
			if (n == 1)
				return head;
			head.next = gen(n - 1);
			return head;
		}//gen(n)
//////////////////////////////// MAIN METHOD:
	public static void main(String[] args){
		Node u = Node.gen(7);
		u.show("Random List:");
	}//main
}//class

