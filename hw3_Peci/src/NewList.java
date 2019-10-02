import java.util.Random;

class NewList {
	// INNER CLASS: ========================================
		class Node {
			// MEMBERS: ====================================
				int data;
				Node next;
			// CONSTRUCTORS: ===============================
				Node(int d) {data=d;}
			// METHODS: ====================================
		}//inner class
	// MEMBERS: ========================================
		static Node head, tail;
	// CONSTRUCTORS: ===================================
		NewList(){
			head = new Node(-1); // dummy node
			tail = head;	
		}
	// METHODS: =========================================
		void add(int d){
			addTail(d); }
		void addTail(int d){
			tail.next = new Node(d);
			tail = tail.next;
		}
		void addHead(int d){
			Node u = new Node(d);
			u.next = head;
			head = u;
		}
	// MAIN METHOD: ====================================
	public static void main (String[] args) {
		 int s = (args.length > 0) ?  Integer.valueOf(args[0]): 111;
		 int n = (args.length > 1) ?  Integer.valueOf(args[1]) : 10;
	
		 Random gen = (s==0)? new Random() : new Random(s);
		
		 NewList myList = new NewList();
		/*
		 for (int i =1; i<n; i++){
		 	lastNode.next = new Node(gen.nextInt(100));
		 	lastNode = lastNode.next;
		 }
		 lastNode = myList;
		 for (int i = 0; i<n; i++) {
		   System.out.printf("%d\n", lastNode.data);
		 	lastNode = lastNode.next;
		 }
		 */
	 }// main
}//class NewList

