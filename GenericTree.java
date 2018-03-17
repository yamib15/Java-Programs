package Lecture15;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class GenericTree {

	public class Node {
		int data;
		ArrayList<Node> children;

		Node(int data) {
			this.data = data;
			this.children = new ArrayList<>();
		}
	}

	private Node root;
	private int size = 0;

	GenericTree() {
		Scanner s = new Scanner(System.in);
		this.root = takeInput(s, null, 0);

	}

	private Node takeInput(Scanner s, Node parent, int ithchild) {
		if (parent == null) {
			System.out.println("Enter data for your root node");
		} else {
			System.out.println("Enter the data for " + ithchild + "th child of " + parent.data);
		}
		int nodeData = s.nextInt();
		Node node = new Node(nodeData);

		this.size++;
		System.out.println("Enter the no of children of " + node.data);
		int numChild = s.nextInt();
		for (int i = 0; i < numChild; i++) {
			Node child = takeInput(s, node, i);
			node.children.add(child);
		}
		return node;
	}

	public void display() {
		this.display(this.root);
	}

	private void display(Node node) {
		System.out.print(node.data + "=>");
		for (int i = 0; i < node.children.size(); i++) {
			System.out.print(node.children.get(i).data + ", ");
		}
		System.out.println("End");

		for (int i = 0; i < node.children.size(); i++) {
			this.display(node.children.get(i));
		}
	}

	public int size2() {
		return this.size2(this.root);
	}

	private int size2(Node node) {
		int rv = 0;
		for (int i = 0; i < node.children.size(); i++) {
			rv += this.size2(node.children.get(i));
		}
		return rv + 1;
	}

	public int maxValue() {
		return this.maxValue(this.root);
	}

	private int maxValue(Node node) {
		int max = node.data;
		for (int i = 0; i < node.children.size(); i++) {
			int maxinchild =  this.maxValue(node.children.get(i));
			if(maxinchild>max){
				max = maxinchild;
			}
		}
		
		return max;
	}
	
	public int height(){
		return this.height(this.root);
	}
	private int height(Node node){
		int height = -1; 
		for(int i = 0; i<node.children.size();i++){
			
			int hofchild = this.height(node.children.get(i));
			 
			if(height<hofchild){
				height = hofchild;
			}
		}
		return height+1;
	}

	public boolean find(int data){
		return find(data,this.root);
	}
	private boolean find(int data, Node node){		
		int temp = node.data;
		if(temp==data){
			 return true;
		   }
		for(int i =0; i< node.children.size();i++){	
		   boolean flag = this.find(data, node.children.get(i));	
		   if(flag==true){
			   return true;
		   }
		} 
		return false;
	}

	public void mirror(){
		this.mirror(this.root);
	}
	private void mirror(Node node){

		for(int i = 0;i< node.children.size()/2;i++){
		
				Node temp = node.children.get(i);
				node.children.set(i, node.children.get(node.children.size()-1-i));
				node.children.set(node.children.size()-1-i, temp);
			
		}
		for(int i =0; i<node.children.size();i++){
			this.mirror(node.children.get(i));
		}
	}
	public void printAtLevel(int level){
		this.printAtLevel(level, this.root);
	}
	private void printAtLevel(int level, Node node){
		if(level==0){
			System.out.println(node.data);
		}	
		for(int i =0; i<node.children.size();i++){		
			this.printAtLevel(level-1, node.children.get(i));		
		}
	}
	
	public void preorder(){
		this.preorder(this.root);
	}
	private void preorder(Node node){
		System.out.print(node.data+", ");
		for(int i =0; i<node.children.size();i++){
			this.preorder(node.children.get(i));
		}
	}
	
	public void postorder(){
		this.postorder(this.root);
	}
	private void postorder(Node node){
		
		for(int i =0; i<node.children.size();i++){
			this.postorder(node.children.get(i));
			
		}
		System.out.print(node.data+", ");
	}

	public void levelOrder(){
		Queue<Node> queue = new LinkedList<GenericTree.Node>();
		queue.add(this.root);
		
		while(!queue.isEmpty()){
			Node node = queue.remove();
			System.out.println(node.data);
			for(int i = 0; i<node.children.size();i++){
				queue.add(node.children.get(i));
			}
		}
		
	}

	public int sumOfNodes(){
		return this.sumOfNodes(this.root);
	}
	private int sumOfNodes(Node node){
		int sum = node.data;
		for(int i =0; i< node.children.size();i++){
			sum = sum + sumOfNodes(node.children.get(i));
		}
		return sum;
	}

	public int noOfLeaf(){
		return this.noOfLeaf(this.root);
	}
	private int noOfLeaf(Node node){
		int count = 0;
		for(int i=0; i<node.children.size(); i++){
			if(node.children.get(i).children.size()!=0){
			 count += this.noOfLeaf(node.children.get(i));
			}else{
				count++;
			}
		}
		return count;
	}
	
	public int maxValue2() {
		return this.maxValue2(this.root);
	}

	private int maxValue2(Node node) {
		
		int max = this.maxValue();
		ArrayList<Integer> list1 = this.maxValue2(this.root, max)	;
		int smax= Integer.MIN_VALUE;
		for(int i=0;i<list1.size();i++){
			if(smax<list1.get(i)){
				smax=list1.get(i);
			}
		}
		return smax;
	
	}
	private ArrayList<Integer> maxValue2(Node node,int max){
		int temp = node.data;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0;i<node.children.size();i++){
			list.add(node.children.get(i).data);
		}
		for(int i=0; i<node.children.size();i++){
			ArrayList<Integer> list1 = this.maxValue2(node.children.get(i),max);
		}
		System.out.println(list);
		return list;
	}

	public int greaterThanN(int n){
		return this.greaterThanN(this.root,n);
	}
	private int greaterThanN(Node node,int n){
		int max = node.data;
		
		return max;
				
	}
	
}

