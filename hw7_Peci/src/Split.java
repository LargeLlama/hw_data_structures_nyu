/* Split.java
 * 		PURPOSE: implement a method to split a list into two
 *			roughly equal lists
 *
 * 	Data Structure Class
 * 	Chee Yap (Fall 2019)
 * **************************************************/
import java.util.Random;

class Split {
// INNER CLASS:==============================
	class Node {
		int val;
		Node next;
		Node(){ val = rg.nextInt(100); }
		Node(Node u){ this(); next=u; }
	}
// MEMBERS:==============================
	static Random rg;
// CONSTRUCTOR:==========================
	Split(Random ran){ rg=ran; }
// METHODS:==============================
	Node split(Node u){
		if (u == null || u.next == null) {	//if u is a list of length 1 or 0, return u
			return u;
		} else {
			Node tmp = u;		//create pointer to u as to not modify it
			int length = 0;		//length variable to keep track

			while(tmp.next != null) {	//loop thru list to get the length
				tmp = tmp.next;
				length++;
			}

			Node newTmp = u;					//reset tmp to the start of the list
			for(int i = 0; i < Math.floor(length / 2) + 1; i++) {
				newTmp = newTmp.next;
			}
			tmp = u;
			while(tmp != null) {
				if (tmp.next == newTmp)
					tmp.next = null;
				tmp = tmp.next;
			}
			return newTmp;
		}
	}
	Node genList(int n){
		if (n==0) return null;
		Node u = new Node(); // head
		for (int i=1; i<n; i++)
			u = new Node(u);
		return u;
	}
	void show(Node u, String msg){
		System.out.println(msg);
		while (u!=null){
			System.out.printf("%d ", u.val);
			u=u.next;
		}
		System.out.println("");
	}
// MAIN:=================================
    public static void main(String[] args){
		int seed= (args.length > 0)? Integer.valueOf(args[0]) : 111;
		int nn= (args.length > 1)? Integer.valueOf(args[1]) : 6;
		java.util.Random ran = (seed==0)?
			new java.util.Random() : new java.util.Random(seed);
		Split sp = new Split(ran);
		System.out.println("==================== TEST ONE:");
			Node u = sp.genList(nn);
			sp.show(u, "list u:");
			Node v = sp.split(u);
			sp.show(u, "list u after split:");
			sp.show(v, "list v after split:");
		System.out.println("==================== TEST TWO:");
			u = sp.genList(nn+1);
			sp.show(u, "list u:");
			v = sp.split(u);
			sp.show(u, "list u after split:");
			sp.show(v, "list v after split:");
    }//main
}//class
