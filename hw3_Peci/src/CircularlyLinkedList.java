import java.util.Random;

class CircularlyLinkedList<E> {
	//inner Node class of generic type E
	class Node<E> {
		//MEMBERS
		E element;
		Node<E> prev;
		Node<E> next;

		//Constructor
		Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
	}//Node

	//MEMBERS:
	Node<E> trailer;
	Node<E> header;
	int size = 0;

	//Constructor
	CircularlyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.next = trailer;
	}
	boolean isEmpty() { return size == 0; }

	void addFirst(E e) {
		addBetween(e, header, header.next);
	}

	void addLast(E e) {
		addBetween(e, trailer.prev, trailer);
	}

	E removeFirst() {
		if(isEmpty()) return null;
		return remove(header.next);
	}

	E removeLast() {
		if(isEmpty()) return null;
		return remove(trailer.prev);
	}

	void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.next = newest;
		successor.prev = newest;
		size++;
	}

	E remove(Node<E> n) {
		Node<E> successor = n.next;
		Node<E> predecessor = n.prev;
		predecessor.next = successor;
		successor.prev = predecessor;
		size--;
		return n.element;
	}
	//Prints list in human-readable format
	void show(String s) {
		System.out.println(s);
		for(Node<E> u = header.next; u != trailer; u = u.next) {
			System.out.print(u.element + " ");
		}
		System.out.print("\n\n");
	}

	//MAIN
	public static void main(String[] args) {
		int ss = (args.length > 0) ?  Integer.valueOf(args[0]): 111;
	 	int nn = (args.length > 1) ?  Integer.valueOf(args[1]) : 9;
	 	Random rg = (ss==0)? new Random() : new Random(ss);

		CircularlyLinkedList<Integer> tailList = new CircularlyLinkedList<Integer>();
		CircularlyLinkedList<Integer> headList = new CircularlyLinkedList<Integer>();

		for(int i = 0; i < nn; i++) {
			int v = rg.nextInt(nn);
			tailList.addLast(v);
			headList.addFirst(v);
		}

		tailList.show("tailList is: ");
		headList.show("headList is: ");

		for(int i =0; i < 3; i++) {
			tailList.removeFirst();
			headList.removeFirst();
		}
		
		tailList.show("tailList after 3 removeHead() is: ");
		headList.show("headList after 3 removeHead() is: ");
	}//main
}
