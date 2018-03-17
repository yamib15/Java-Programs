package Lecture16;

import java.util.LinkedList;
import java.util.Scanner;

public class BinaryTree {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}

	private Node root;
	private int size = 0;

	BinaryTree() {
		Scanner s = new Scanner(System.in);
		this.root = takeInput(s, null, false);
	}

	public Node takeInput(Scanner s, Node parent, Boolean isleftorRight) {
		if (parent == null) {
			System.out.println("Enter the data for the root node");
		} else {
			if (isleftorRight) {
				System.out.println("Enter the data for the left child of " + parent.data);

			} else {
				System.out.println("Enter the data for the right child of" + parent.data);
			}
		}
		int nodeData = s.nextInt();
		Node node = new Node(nodeData);
		this.size++;

		Boolean choice = false;
		System.out.println("Does " + nodeData + " has left child?");
		choice = s.nextBoolean();
		if (choice) {
			node.left = takeInput(s, node, true);
		}

		choice = false;
		System.out.println("Does " + nodeData + " has right child?");
		choice = s.nextBoolean();
		if (choice) {
			node.right = takeInput(s, node, false);
		}

		return node;
	}

	public void display() {
		this.display(this.root);
	}

	private void display(Node node) {
		if (node.left != null) {
			System.out.print(node.left.data + " => ");
		} else {
			System.out.print("END => ");
		}

		System.out.print(node.data);

		if (node.right != null) {
			System.out.print(" <= " + node.right.data);
		} else {
			System.out.print(" <= END");
		}

		System.out.println();

		if (node.left != null) {
			this.display(node.left);
		}
		if (node.right != null) {
			this.display(node.right);
		}
	}

	public int size2() {
		return this.size2(this.root);
	}

	private int size2(Node node) {
		int rv = 0;
		if (node.left != null) {
			rv += size2(node.left);
		}
		if (node.right != null) {
			rv += size2(node.right);
		}
		return rv + 1;
	}

	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		int max = node.data;
		if (node.left != null) {
			int leftmax = this.max(node.left);
			if (max < leftmax) {
				max = leftmax;
			}
		}
		if (node.right != null) {
			int rightmax = this.max(node.right);
			if (max < rightmax) {
				max = rightmax;
			}
		}
		return max;
	}

	public int min() {
		return this.min(this.root);
	}

	private int min(Node node) {
		int min = node.data;
		if (node.left != null) {
			int leftmin = this.min(node.left);
			if (min > leftmin) {
				min = leftmin;
			}
		}
		if (node.right != null) {
			int rightmin = this.min(node.right);
			if (min > rightmin) {
				min = rightmin;
			}
		}
		return min;
	}

	public int height() {
		return this.height(this.root);
	}

	private int height(Node node) {
		int height = -1;
		if (node.left != null) {
			int lh = this.height(node.left);
			height = lh;
		}
		if (node.right != null) {
			int rh = this.height(node.right);
			if (rh > height) {
				height = rh;
			}
		}
		return height + 1;
	}

	public boolean find(int item) {
		return this.find(this.root, item);
	}

	private boolean find(Node node, int item) {
		int temp = node.data;
		if (temp == item) {
			return true;
		}

		if (node.left != null) {
			boolean lf = this.find(node.left, item);
			if (lf == true) {
				return true;
			}
		}
		if (node.right != null) {
			boolean rf = this.find(node.right, item);
			if (rf == true) {
				return true;
			}
		}
		return false;
	}

	public void mirror() {
		this.mirror(this.root);
	}

	private void mirror(Node node) {
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		if (node.left != null) {
			this.mirror(node.left);
		}
		if (node.right != null) {
			this.mirror(node.right);
		}
	}

	public void preOrder() {
		this.preOrder(this.root);
	}

	private void preOrder(Node node) {
		System.out.println(node.data);
		if (node.left != null) {
			this.preOrder(node.left);
		}
		if (node.right != null) {
			this.preOrder(node.right);
		}
	}

	public void postOrder() {
		this.postOrder(this.root);
	}

	private void postOrder(Node node) {

		if (node.left != null) {
			this.postOrder(node.left);
		}
		if (node.right != null) {
			this.postOrder(node.right);
		}
		System.out.println(node.data);
	}

	public void inOrder() {
		this.inOrder(this.root);
	}

	private void inOrder(Node node) {

		if (node.left != null) {
			this.inOrder(node.left);
		}

		System.out.println(node.data);

		if (node.right != null) {
			this.inOrder(node.right);
		}

	}

	public int sumOfNodes() {
		return this.sumOfNodes(this.root);
	}

	private int sumOfNodes(Node node) {
		if (node == null) {
			return 0;
		}
		return node.data + this.sumOfNodes(node.right) + this.sumOfNodes(node.left);

	}

	public boolean identical(BinaryTree tree1) {
		return this.identical(this.root, tree1.root);
	}

	private boolean identical(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 == null || node2 == null) {
			return false;
		}
		if (node1.data != node2.data) {
			return false;
		}
		boolean check = this.identical(node1.left, node2.left);
		if (!check) {
			return false;
		} else {
			return this.identical(node1.right, node2.right);
		}
	}

	public boolean isBalanced() {
		return this.isBalanced(this.root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}
		if (node.left != null && node.right != null) {
			int lheight = this.height(node.left);
			int rheight = this.height(node.right);
			if ((Math.abs(lheight - rheight)) >= 1) {
				return false;
			}
		}else if(node.left==null){
			if(this.height(node)>1){
				return false;
			}else{
				return true;
			}
		}else if(node.right==null){
			if(this.height(node)>1){
				return false;
			}else{
				return true;
			}
		}
		boolean check = this.isBalanced(node.left);
		if (!check) {
			return false;
		} else {
			return this.isBalanced(node.right);
		}
	}

	public LinkedList[] eachLevel(){
		LinkedList[] arr = new LinkedList[this.height()+1];
		for(int i=0; i<=this.height();i++){
			arr[i] = this.atlevel(this.root, i, new LinkedList<>());
		}
		return arr;
	}
	
	private LinkedList atlevel(Node node, int level, LinkedList list){
		if(node==null){
			return null;
		}

		if(level==0){
			list.add(node.data);
			return list;
		}
		 this.atlevel(node.left, level-1,list);
		 this.atlevel(node.right, level-1,list);
			
		return list;
	}

	public void singlechild(){
		this.singlechild(this.root);
		System.out.println("END");
	}
	private void singlechild(Node node){
		if(node==null){
			return;
		}
		
		if(node.left==null && node.right!=null){
			System.out.print(node.right.data + ", ");
		}
		if(node.left!=null && node.right==null){
			System.out.print(node.left.data + ", ");
		}
		this.singlechild(node.left);
		this.singlechild(node.right);
	}

	

}
