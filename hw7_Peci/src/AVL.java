/* AVL.java
 * 		PURPOSE: an AVL implementation
 *				based on BST
 *
 * 	Data Structure Class
 * 	Chee Yap (Mar7 2012; Nov 2019)
 *
 * **************************************************/

public class AVL extends BST {
/***************************************************
* Members and Constructors
***************************************************/
	AVL(){}
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
	void rebalanceAdd(Bnode2 v){
		while(true){	//keeps going until it hits a return condition
				if (v == null || v.parent==null) return;	//doesn't do anything if it's an empty tree
				if (isShorter(v)) {
					v.parent.balance=0;
					return;
				} 
				if (isTaller(v)){
					fix(v);
					return;
				}
				if (v == v.parent.left)
					v.parent.balance=+1;
				else
					v.parent.balance=-1;
				v = v.parent;
			}
			// See Lecture Notes for the algorithm
	}
	void rebalanceRemove(Bnode2 u){
		// YOU DO NOT HAVE TO IMPLEMENT THIS.
	}
	boolean isShorter(Bnode2 u) {
		return (u == u.parent.right && u.parent.balance == 1) || (u == u.parent.left && u.parent.balance == -1);
	}//isShorter

	boolean isTaller(Bnode2 u) {
		return (u == u.parent.left && u.parent.balance == 1) || (u == u.parent.right && u.parent.balance == -1);
	}//isTaller

	boolean isOuter(Bnode2 u) {
		return (u.parent.balance == 1 && u.parent.parent.balance == 1) || (u.parent.balance == 1 && u.parent.parent.balance == -1);
	}//isOuter
	void rotate(Bnode2 u) {
		//4 cases for rotate 
		if(u.right != null && u == u.parent.right) {	//if it has a right node and it is a right node
			rightRotate(u);
		}
		else if(u.left != null && u == u.parent.left) {	//if it has a left node and it is a left node
			leftRotate(u);
		}
		else if(u.right != null && u == u.parent.left) {	//if it has a right node and it is a left node
			leftRotate(u.left);
			rightRotate(u);
		}
		else if(u.left != null && u == u.parent.right) {	//if it has a left node and it is a right node
			rightRotate(u.right);
			leftRotate(u);
		}
	}

	void doubleRotate(Bnode2 u) {	//just calls rotate twice
		rotate(u);
		rotate(u);
	}

	void rightRotate(Bnode2 u) {	//rotate around the right
		if (u == null)
			return;
		Bnode2 leftChild = u.left;
		Bnode2 leftSubtree = leftChild == null ? null : leftChild.right;
		if(leftChild != null)
			leftChild.right = u;
		u.left = leftSubtree;
	}

	void leftRotate(Bnode2 u) {		//rotate around the left
		if (u == null)	//null pointer check
			return;
		Bnode2 rightChild = u.right;
		Bnode2 rightSubtree = rightChild == null ? null : rightChild.left;	//null pointer check
		if(rightChild != null)	//null pointer check
			rightChild.left = u;
		u.right = rightSubtree;
	}

	void fix(Bnode2 v) {
		Bnode2 w;
		if (v.balance == 1) 
			w = v.left;
		else 
			w = v.right;
		if (isOuter(w)) {
			v.parent.balance = 0;
			v.balance = 0;
			rotate(v);
		}
		else {
			int b = w.balance;
			Bnode2 u = v.parent;
			if (b==1) {
				u.balance = 0;
				v.balance = -b;
			} else {
				u.balance = -b;
				v.balance = 0;
			}
			doubleRotate(w);
			w.balance = 0;
		}
	}//fix
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
		int nums[] = {1,2,3,4,5,6,7};
		for (int i=0; i<nums.length; i++){
			x = nums[i];
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
