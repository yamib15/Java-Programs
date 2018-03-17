package Lecture19;

import java.util.ArrayList;

import Lecture19.Client.Student;

public class Heap<T extends Comparable<T>> {
	private ArrayList<T> data;
	private boolean isMin;

	Heap() {
		this(false);
	}

	Heap(boolean isMin) {
		this.data = new ArrayList<>();
		this.isMin = isMin;
	}

	Heap(T[] arr, boolean isMin){
		this(isMin);
		for(T item :arr){
			this.data.add(item);
		}
		for(int i=this.data.size()/2-1;i>=0;i--){
			this.downheapify(i);
		}
	}
	public int size() {
		return this.data.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void add(T item) {
		this.data.add(item);
		this.upheapify(this.data.size() - 1);
	}

	private void upheapify(int ci) {
		if (ci == 0) {
			return;
		}
		int pi = (ci - 1) / 2;
		if (!this.isLarger(pi, ci)) {
			this.swap(pi, ci);
			this.upheapify(pi);
		} 

	}

	private void swap(int i, int j) {
		T temp = this.data.get(i);
		this.data.set(i, this.data.get(j));
		this.data.set(j, temp);
	}

	private boolean isLarger(int i, int j) {
		T ithterm = this.data.get(i);
		T jthterm = this.data.get(j);
		if (isMin) {
			return ithterm.compareTo(jthterm) < 0;
		} else {
			return ithterm.compareTo(jthterm) > 0;
		}
	}


	public T remove() {
		T hp = this.data.get(0);
		this.swap(0, this.data.size() - 1);
		this.data.remove(this.data.size() - 1);
		this.downheapify(0);
		return hp;
	}

	private void downheapify(int pi) {

		int lci = pi * 2 + 1;
		int rci = pi * 2 + 2;
		
		int mi=pi;
		if(lci<this.data.size()&&this.isLarger(lci, mi)){
			mi = lci;
		}
		if(rci<this.data.size()&&this.isLarger(rci, mi)){
			mi = rci;
		}
		if(mi!=pi){
			this.swap(mi, pi);
			this.downheapify(mi);
		}
	}
	
	public void display(){
		this.display(0);
	}
	private void display(int idx){
		T node = this.data.get(idx);
		int lci = idx*2+1;
		int rci = idx*2+2;
		String str = "";
		if(lci<this.data.size()){
			T lc = this.data.get(lci);
			str = str+lc;
			str = str+"=>";
		}else{			
			str = str+"END=>";
		}
		str = str+node;
		if(rci<this.data.size()){
			T rc = this.data.get(rci);
			str = str+"<=";
			str = str+rc;
		}else{
			
			str = str+"<=END";
			
		}
		System.out.println(str);
		if(lci<this.data.size()){
			this.display(lci);
		}
		if(rci<this.data.size()){
			this.display(rci);
		}
	}
}
