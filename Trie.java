package Lecture20;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Trie {

	private class Node {
		Character data;
		HashMap<Character, Node> children;
		boolean isTerminal;

		Node(Character data, boolean isTerminal) {
			this.data = data;
			this.children = new HashMap<>();
			this.isTerminal = isTerminal;
		}

	}

	private Node root;
	private int numWords;

	Trie() {
		this.root = new Node('\0', false);
		this.numWords = 0;
	}

	public int getNumWords() {
		return this.numWords;
	}

	public boolean isEmpty() {
		return this.getNumWords() == 0;
	}

	public void addWord(String word) {
		this.addWord(this.root, word);
	}

	private void addWord(Node parent, String word) {
		if (word.length() == 0) {
			if (parent.isTerminal) {

			} else {
				parent.isTerminal = true;
				this.numWords++;
			}
			return;
		}
		char cc = word.charAt(0);
		String ros = word.substring(1);
		Node child = parent.children.get(cc);
		if (child == null) {
			child = new Node(cc, false);
			parent.children.put(cc, child);
		}
		this.addWord(child, ros);
	}

	public void display() {
		this.display(this.root, "");
	}

	private void display(Node node, String osf) {
		if (node.isTerminal) {
			String str = osf.substring(1) + node.data;
			System.out.println(str);
		}

		Set<Map.Entry<Character, Node>> entries = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : entries) {
			this.display(entry.getValue(), osf + node.data);
		}
	}

	public boolean searchWord(String word) {
		return this.searchWord(this.root, word);
	}

	private boolean searchWord(Node parent, String word) {
		if (word.length() == 0) {
			if (parent.isTerminal) {
				return true;
			} else {
				return false;
			}
		}
		char cc = word.charAt(0);
		String ros = word.substring(1);
		Node child = parent.children.get(cc);
		if (child == null) {
			return false;
		}
		return this.searchWord(child, ros);
	}

	public void removeWord(String word) {
		this.removeWord(this.root, word);
	}

	private void removeWord(Node parent, String word) {
		if (word.length() == 0) {
			if (parent.isTerminal) {
				parent.isTerminal = false;
				return;
			}
		}
		char cc = word.charAt(0);
		String ros = word.substring(1);
		Node child = parent.children.get(cc);
		if(child==null){
			return;
		}
		this.removeWord(child, ros);		
//		if (parent.children == null){		
//			parent.children.remove(cc);			
//		}
		if(!parent.isTerminal&& child.children==null){
			parent.children.remove(cc);
		}
		
		
	}
	
	public void displayTree(){
		this.displayTree(this.root);
	}
	private void displayTree(Node parent){
		String str = "";
		str = str+parent.data+"=>";
		Set<Map.Entry<Character, Node>> entries = parent.children.entrySet();
		for(Map.Entry<Character, Node> entry: entries){
			str = str + entry.getKey()+", ";
		}
		
		System.out.print(str);
		System.out.println("End");
		for(Map.Entry<Character, Node> entry: entries){
			this.displayTree(entry.getValue());
		}
	}

}
