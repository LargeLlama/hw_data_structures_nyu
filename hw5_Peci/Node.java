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
			if (n < 1)	//n can't be less than one, otherwise there is no List
				throw new IllegalArgumentException();
			//the head of the list is a random int
			Node head = new Node(rg.nextInt(range));
			if (n == 1)			//if we reached n==1, we return this head
				return head;
			head.next = gen(n - 1);	//recursively call this function, decrementing n by 1
			return head;		//return the head we previously declared 
		}//gen(n)

	static Node secondLast(Node u) {
		if(u == null || u.next == null)	//if list is length 0 or 1, return null
			return null;
		Node tmp = u;					//assign a tmp variable as to not disturb the actual input node
		while(tmp.next.next != null) {	//loop thru until you reach the second to last node
			tmp = tmp.next;
		}
		return tmp;						//return the second to last node
	}//secondLast(u)

	static Node genRhoList(int m, int n) {
		if (m < n) {
			Node head = new Node();
			Node tmp = head;

			int count = 0;
			while(count < m - 1) {
				tmp.next = new Node();
				tmp = tmp.next;
				count++;
			}
			Node loopClose = tmp;

			//head.show("Tail:");
			//System.out.println("Value of count: " + count);

			while(count < n) {
				tmp.next = new Node();
				tmp = tmp.next;
				count++;
			}

			//System.out.println("Value of count: " + count);
			//loopClose.show("Starting from LoopClose:");
			tmp.next = loopClose;
			return head;
		} else {
			Node head = new Node();
			Node tmp = head;
			for(int i = 0; i < n; i++) {
				tmp.next = new Node();
				tmp = tmp.next;
			}
			return head;
		}
	}

//////////////////////////////// MAIN METHOD:
	public static void main(String[] args){
		Node u = Node.gen(7);
		u.show("Random List:");
		//Node.secondLast(u).show("Second to last:"); testing secondLast method
		//Rho list testing
		Node m = Node.genRhoList(5, 10);
		m.show("Rho-list:");

	}//main
}//class

