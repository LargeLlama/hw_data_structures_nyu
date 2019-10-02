/* file: List.java
 *
 * Simple List Class
 *		-- Each list has a head, tail, size
 *		-- Uses an inner class "Node"
 * Exercise:
 *		-- add methods for removeHead() and removeTail()
 *
 *	Chee Yap (CS 102, Data Structure)
 *	Fall 2019
 *****************************************/

import java.util.Random;

class List {
	// INNER CLASS: ========================================
		static class Node{
	 		int value;
			Node next;
			// CONSTRUCTORS: ===============================
			Node(int v, Node n){
		    	value = v; next = n;}
			Node(int v){
		    	value = v; next = null;}
		}//Node class
	// MEMBERS: ========================================
		Node head = null;
		Node tail = null;
		int size = 0;
	// METHODS: ========================================
		void add(int n){	//calls the addHead method
			addHead(n); }
		void addHead(int n){
		    Node nn = new Node(n, head);
		    head = nn;
		    if (size==0) tail = head;
		    size++;
		}//addHead
		//Takes the integer input, and creates a new node with input value
		//Places it as the head
		void addTail(int n){
			if (size==0) {
		    	head = tail = new Node(n);
			} else {
		    	tail.next = new Node(n);
				tail = tail.next;
			}
		    size++;
		}//addTail
		int removeHead(){
			if (size == 0) return Integer.MIN_VALUE;
			int val = head.value;
			size--;
			if (size == 0) 
				head=tail=null;
			else if (size>0)
				head = head.next;
			return val;
		}
		void show(String s){
			System.out.println(s);
			Node u = head;
			while (u != null){
				System.out.printf("%d ", u.value);
				u = u.next;
			}
			System.out.printf("\n\n");
		}//show
	// MAIN: ========================================
		public static void main(String[] args){
			int ss = (args.length > 0) ?  Integer.valueOf(args[0]): 111;
		 	int nn = (args.length > 1) ?  Integer.valueOf(args[1]) : 9;
		 	Random rg = (ss==0)? new Random() : new Random(ss);
		
			List tailList = new List();
			List headList = new List();
			for (int i=0; i<nn; i++){
				int v = rg.nextInt(nn);
				tailList.addTail(v);
				headList.addHead(v);
			}
			tailList.show("tailList is:");
			headList.show("headList is:");
			for (int i=0; i<3; i++){
				tailList.removeHead();
				headList.removeHead();
			}
			tailList.show("tailList after 3 removeHead() is:");
			headList.show("headList after 3 removeHead() is:");
		}//main
}
