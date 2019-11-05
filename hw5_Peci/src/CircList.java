/* CircList.java
 *  An implementation of Circular Lists.
 *  
 *		Please implement those methods that says
 *					"IMPLEMENT ME"
 *		This class depends on
 *					--InterfaceCircList.java,
 *					--Node.java
 *		DO NOT MODIFY THE MAIN METHOD.
 *
 *  Fall 2019, CS102
 *  Chee Yap
 **************************************************/ 

import java.util.Random;

class CircList
		implements InterfaceCircList {
////////////////////////////////MEMBERS:
		Node head;	// arbitrary node in circular list
////////////////////////////////CONSTRUCTORS:
		CircList(){
			head=null;
		}
		CircList(int v){
			head=new Node(v);
			head.next=head; 
		}
////////////////////////////////METHODS:
	public void add(Node u){	// Notice that we add a Node, not an int.
		if (head == null) {	//if it's an empty list, you add the element as the head
			head = u;
			u.next = u;
		}
		else {				//the list has at least one element, and you can insert it between the head and whatever is next
			Node tmp = head.next;	//(even if head.next points back to head){
			head.next = u;
			u.next = tmp;
		}
	}
	public void remove(){
		if (head == null) {
			return;
		}
		else if (head.next == head) {		//if it's only size one, just remove the head
			head = null;
		} else {						//otherwise, skip over the head.next, essentially removing it from the list as nothing points to it
			head.next = head.next.next;
		}
	}
	public int size(){
		if(head == null)		//empty lists are size 0
			return 0;
		Node tmp = head.next;	//set tmp to head.next
		int count = 1;			//we already accounted for the head, so count starts @ 1
		while(tmp != head) {	//as long as we don't reach back around to the head, keep incrementing count
			count++;
			tmp = tmp.next;
		}
		return count;
	}
	public static CircList gen(int n){
		CircList cl = new CircList();
		for (int i=0; i<n; i++)
			cl.add(new Node());		// Adding Node with random int values
		return cl;
	}
	public static void show(CircList cl, String msg){
		if (cl==null) return;
		cl.show(msg);
	}
	public void show(String msg){
		System.out.println(msg);
		if (head==null) return;
		Node u = head;
		do{
			System.out.printf("%d ", u.val);
			u = u.next;
		} while (u!=head);
		System.out.println("");
	}
	public void rot(){
		head = head.next;
	}
	public void rot(int n){ // note that n may be negative!
		if (n == 0 || head.next == head) {	//if the list is size 1 or 0, do nothing
			return;
		} else if (n > 0) {					//if n is positive
			for(int i = 0; i < n; i++) {	//keep rotating n number of times
				rot();
			}
		} else {							//if n is negative
			for(int i = 0; i < size() + n; i++) {	//keep rotating size + n times
				rot();
			}
		}
	}
	public void rev(){ // reverse list
		if (head == null) return;
		rev(head); 	// call helper method
	}
	public void rev(Node u){ // TRICKY!
		Node prev = null;		//set the prev node to null
		Node current = u;		//current node to u (head)
		Node next;				//declare, but don't initialize, the next node

		//as long as we aren't back at the head of the list, do the following code
		do {
			next = current.next;		//set next to the current node's next node
			current.next = prev;		//set the current node's next to prev,
			prev = current;				//set the prev node to be the current node
			current = next;				//set the current node to be the next node (which is just current.next)
		}while ( current != u );

		u.next = prev;					//to wrap things up and close the circle, we set the head's next to the prev
	}
////////////////////////////////MAIN:
// DO NOT MODIFY THE MAIN METHOD !!!
	public static void main(String[] args){
		int ss = (args.length>0)? Integer.valueOf(args[0]) : 111;
		int nn = (args.length>1)? Integer.valueOf(args[1]) : 8;
		Node.rg = (ss==0)? new Random() : new Random(ss);
	
		System.out.printf("==> Testing CircList\n");
		CircList cl = gen(nn);
		if (cl!=null) {
			cl.show("==> Random Circular list:");
				System.out.printf("==> Size = %d \n", cl.size());
			cl.rot(1);
				cl.show("==> Rot(1):");
			cl.rot(nn+1);
				cl.show("==> Rot("+ (nn+1) + "):");
			cl.rot(-2);
				cl.show("==> Rot(-2):");
			cl.rev();
				cl.show("==> Reversed list:");
			cl.remove(); cl.remove();
				cl.show("==> After two removes:");
		}
	}//main
}//class

