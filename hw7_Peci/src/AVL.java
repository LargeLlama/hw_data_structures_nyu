/* AVL.java
 * 		PURPOSE: an AVL implementation
 *				based on BST
 *
 * 	Data Structure Class
 * 	Chee Yap (Mar7 2012; Nov 2019)
 *
 * **************************************************/

public class AVL
			extends BST {
/***************************************************
* Members and Constructors
***************************************************/
    Bnode2 root;
    int size;
    AVL(){ root=null; size=0; }
/***************************************************
* Methods
***************************************************/
	/***************************************************
     * Two KEY Methods -- add and remove for AVL:
     * **************************************************/
	Bnode2 add(int k) { // IMPLEMENTED FOR YOU ALREADY
		Bnode2 u = super.add(k);
		rebalanceAdd(u.parent);
		return u;
	}
	Bnode2 remove(int k) { // IMPLEMENTED FOR YOU ALREADY
		Bnode2 u = super.remove(k);
		if (u != null)
		    rebalanceRemove(u.parent);
		return u;
	}
	void rebalanceAdd(Bnode2 u){
		// IMPLEMENT THIS !!!
		// See Lecture Notes for the algorithm
	}
	void rebalanceRemove(Bnode2 u){
		// YOU DO NOT HAVE TO IMPLEMENT THIS.
	}
/***************************************************
* Main method
***************************************************/
    public static void main(String[] args){
		int seed= (args.length > 0)? Integer.valueOf(args[0]) : 111;
		int nn= (args.length > 1)? Integer.valueOf(args[1]) : 5;
		java.util.Random ran = (seed==0)?
			new java.util.Random() : new java.util.Random(seed);
	
		AVL bst = new AVL();
		int x;
		Bnode2 u;
		System.out.println("\n========================================");
		System.out.println("Attempt to insert " + nn + " random ints:");
		for (int i=0; i<nn; i++){
		   x = ran.nextInt(nn);
			System.out.printf("i=%d, x=%d\n", i, x);
		   if (bst.add(x) == null)
		       System.out.print("-" + x + ", ");
		   else
		       System.out.print("+" + x + ", ");
		}
		System.out.println("\n========================================\n");
		   if (bst.check())
			   System.out.println("Tree passes the AVL check!");
		   else System.out.println("Tree fails the AVL check!");
		System.out.println("\n========================================\n");
		   System.out.println("Post, In, Pre order of AVL:");
		   bst.post(); 	System.out.println();
		   bst.inorder();	System.out.println();
		   bst.pre();
		System.out.println("\n========================================\n");
    }//main
}//AVL
