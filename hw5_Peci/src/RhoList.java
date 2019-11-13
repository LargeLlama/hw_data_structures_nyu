import java.util.Random;
public class RhoList {
	static Random rg;

	class Node {
		int val;
		int range = 100;
		Node next;

		Node() {
			val = rg.nextInt(range);
		}
	}

	Node genRhoList(int m, int n) {
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
	int sizeRhoList(Node u) {
		Node slow = u;
		Node fast = u;
		int count = 0;
		while(slow != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				break;
			count++;
		} 
		return count;
	}
	void show(Node u, String msg, int n) {
		if (msg != null)
			System.out.println(msg);
		Node tmp = u;
		for (int i = 0; i < n; i++) {
			System.out.print(tmp.val + " ");
			tmp = tmp.next;
		}
	}
	void show(Node u, int n) {
		show(u, null, n);
	}

	public static void main(String[] args) {
		int ss = args.length > 0 ? Integer.parseInt(args[0]) : 111;
		int nn = args.length > 1 ? Integer.parseInt(args[1]) : 10;
		int mm = args.length > 2 ? Integer.parseInt(args[2]) : 4;
		rg = ss == 111 ? new Random(0) : new Random(ss);

		RhoList test = new RhoList();
		Node head = test.genRhoList(mm, nn);
		System.out.println("Estimated size of generated list/rho-list: " + test.sizeRhoList(head));
		System.out.println("Contents of the list/rho-list:");
		test.show(head, nn);
	}
}
