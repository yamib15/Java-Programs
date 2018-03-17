package Lecture13;

public class LinkedList {
	public class Node {
		int data;
		Node next;

		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	Node head;
	Node tail;
	int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addFirst(int data) {
		Node node = new Node(data, this.head);
		this.head = node;
		if (this.isEmpty()) {
			this.tail = node;
		}

		this.size++;
	}

	public void addLast(int data) {
		Node node = new Node(data, null);
		if (this.isEmpty()) {
			this.head = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = this.tail.next;
		}
		this.size++;
	}

	private Node getNodeAt(int index) throws Exception {
		if (index < 0 || index >= this.size()) {
			throw new Exception("Invalid Index");
		}
		int counter = 0;
		Node temp = this.head;
		while (counter < index) {
			temp = temp.next;
			counter++;
		}
		return temp;
	}

	public void addAt(int data, int index) throws Exception {
		if (index < 0 || index > this.size()) {
			throw new Exception("Invalid Index");
		}

		if (index == 0) {
			this.addFirst(data);
		}
		if (index == this.size()) {
			this.addLast(data);
		} else {
			Node temp = getNodeAt(index - 1);
			Node node = new Node(data, temp.next);
			temp.next = node;
			this.size++;
		}
	}

	public void display() {
		Node temp = this.head;
		while (temp != null) {

			System.out.print(temp.data + "=>");
			temp = temp.next;
		}
		System.out.println("END");
	}

	public int getFirst() throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		} else {
			return this.head.data;
		}
	}

	public int getLast() throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		} else {
			return this.getNodeAt(this.size() - 1).data;
		}
	}

	public int getAt(int index) throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		}
		if (index < 0 || index > this.size()) {
			throw new Exception("Invalid Index");
		}
		if (index == 0) {
			return this.getFirst();
		} else {
			if (index == this.size() - 1) {
				return this.getLast();
			} else {
				Node nm = this.getNodeAt(index);
				return nm.data;
			}
		}
	}

	public void removeFirst() throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		}
		this.head = this.head.next;
		this.size--;
	}

	public void removeLast() throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		}
		Node nm = this.getNodeAt(this.size() - 2);
		nm.next = null;
		this.tail = nm;
		this.size--;
	}

	public void removeAt(int index) throws Exception {
		if (this.size() == 0) {
			throw new Exception("List is Empty");
		}
		if (index < 0 || index > this.size()) {
			throw new Exception("Invalid Index");
		}
		if (index == 0) {
			this.removeFirst();
		} else if (index == this.size() - 1) {
			this.removeLast();
		} else {
			Node nm1 = this.getNodeAt(index - 1);
			Node nm2 = this.getNodeAt(index + 1);
			nm1.next = nm2;
			this.size--;
		}
	}

	public void reverseDataI() throws Exception {
		Node first = this.getNodeAt(0);
		Node last = this.getNodeAt(this.size() - 1);

		for (int i = 0; i <= this.size() / 2; i++) {
			int temp = last.data;
			last.data = first.data;
			first.data = temp;

			first = this.getNodeAt(i + 1);
			last = this.getNodeAt(this.size() - 2 - i);
		}
	}

	public void reversePointerI() throws Exception {
		int counter = 0;
		Node first = this.getNodeAt(this.size() - 1);
		Node temp = first;
		while (counter < this.size() - 1) {
			first.next = this.getNodeAt(this.size() - 2 - counter);
			counter++;
			first = this.getNodeAt(this.size() - 1 - counter);

		}
		this.head = temp;
		this.tail = first;
		this.tail.next = null;

		// other method
		/*
		 * Node previous = this.head; Node current = prev.next;
		 * while(current!=null){ Node tempprev = previous; Node tempCurr =
		 * current; previous = current; current = current.next; tempCurr.next=
		 * temp.prev }
		 * 
		 */

	}

	public void reversePointerR() {
		reversePointerR(this.head);
		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
	}

	private void reversePointerR(Node curr) {
		if (curr == this.tail) {
			return;
		}
		reversePointerR(curr.next);
		curr.next.next = curr;
	}

	public void reverseDR() {
		HeapMover obj = new HeapMover(this.head);
		reverseDR(obj, this.head, 0);
	}

	private void reverseDR(HeapMover left, Node right, int level) {
		if (right == this.tail) {
			return;
		}

		reverseDR(left, right.next, level + 1);
		if (level >= this.size / 2) {
			int temp = left.node.data;
			left.node.data = right.data;
			right.data = temp;

			left.node = left.node.next;
		}
	}

	private class HeapMover {
		Node node;

		HeapMover(Node node) {
			this.node = node;
		}
	}

	public int mid() {
		return getMidNode().data;
	}
	private Node getMidNode(){
		Node fast = this.head;
		Node slow = this.head;
		while(fast.next!=null && fast.next.next!=null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public int kthfromLast(int index)throws Exception{
		if(index==0 || index>this.size){
			throw new Exception("Invalid Index");
		}
		Node fast = this.head;
		Node slow = this.head;
		for(int i=0; i<index;i++){
			fast = fast.next;
		}
		while(fast!=null){
			fast = fast.next;
			slow = slow.next;
		}
		return slow.data;
	}
	
	public LinkedList merge(LinkedList first, LinkedList second){
		LinkedList result = new LinkedList();
		Node temp1 = first.head;
		Node temp2 = second.head;
		while(temp1!=null && temp2!=null){
			if(temp1.data>temp2.data){
				result.addLast(temp2.data);
				temp2=temp2.next;
			}else{
				result.addLast(temp1.data);
				temp1= temp1.next;
			}
		}
		while(temp1!=null){
			result.addLast(temp1.data);
			temp1= temp1.next;
		}
		while(temp2!=null){
			result.addLast(temp2.data);
			temp2=temp2.next;
		}
		return result;
	}
	
	public void MergeSort(){
		LinkedList sorted = mergeSortHelper(this.head,this.tail);
		this.head=sorted.head;
		this.tail=sorted.tail;
		this.size=sorted.size;
	}
	private LinkedList mergeSortHelper(Node low, Node high){
		if(this.size==1){
			return this;
		}
		Node mid = getMidNode();
		Node temp = mid.next;
		mid.next=null;
		
		LinkedList first = new LinkedList(low,mid,(this.size()+1)/2);	
		LinkedList second = new LinkedList(temp,high,this.size()/2);
		first.MergeSort();
		second.MergeSort();
		return this.merge(first, second);
	}
	private LinkedList(Node head, Node tail, int size){
		this.head=head;
		this.tail=tail;
		this.size=size;
	}

	public void swap(int index1, int index2)throws Exception{
		Node one = this.getNodeAt(index1);
		Node other = this.getNodeAt(index2);
		int temp = one.data;
		one.data=other.data;
		other.data=temp;
	}
	
}
