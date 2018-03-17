package Lecture18;

public class HashTable<K, V> {

	private class HTPair {
		K key;
		V value;

		HTPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public boolean equals(Object oj) {
			HTPair ob = (HTPair) oj;
			return this.key.equals(ob.key);
		}

		@Override
		public String toString() {
			return "{" + this.key + "->" + this.value + "}";
		}
	}

	private LinkedList<HTPair>[] bucketArray;
	private int size;

	public static final int DEFAULT_CAPACITY = 10;

	public HashTable() {
		this(DEFAULT_CAPACITY);
	}

	public HashTable(int capacity) {
		this.bucketArray = (LinkedList<HTPair>[]) new LinkedList[capacity];
		this.size = 0;
	}
	public int size(){
		return this.size;
	}

	public void put(K key, V value) throws Exception {

		int bi = this.HashFunction(key);
		LinkedList<HTPair> bucket = this.bucketArray[bi];
		HTPair pair = new HTPair(key, value);
		if (bucket == null) {
			bucket = new LinkedList<>();
			bucket.addLast(pair);
			this.bucketArray[bi] = bucket;
			this.size++;
		} else {
			int pta = bucket.findAt(pair);
			if (pta == -1) {
				bucket.addLast(pair);
				this.size++;
			} else {
				HTPair node = bucket.getAt(pta);
				node.value = value;
			}
		}
		
		double lamda = (this.size*1.0 )/this.bucketArray.length;
		if(lamda>2){
			this.rehash();
		}
	}

	public void display() {
		for (LinkedList<HTPair> bucket : this.bucketArray) {
			if (bucket != null && !bucket.isEmpty()) {
				bucket.display();
			} else {
				System.out.println("Null");
			}
		}
		System.out.println("END.....");
	}

	private int HashFunction(K key) {
		int hc = key.hashCode();
		hc = Math.abs(hc);
		int bi = hc % this.bucketArray.length;
		return bi;
	}
	
	public V getValue(K key)throws Exception{
		int bi = this.HashFunction(key);
		LinkedList<HTPair> bucket = this.bucketArray[bi];
		HTPair ptf = new HTPair(key, null);
		if(bucket==null){
			return null;
		}else{
			int findAt = bucket.findAt(ptf);
			if(findAt==-1){
				return null;
			}else{
				HTPair pair = bucket.getAt(findAt);
				return pair.value;
			}
		}
	}
	
	public V remove(K key)throws Exception{
		int bi = this.HashFunction(key);
		LinkedList<HTPair> bucket = this.bucketArray[bi];
		HTPair ptf = new HTPair(key, null);
		if(bucket==null){
			return null;
		}else{
			int findAt = bucket.findAt(ptf);
			if(findAt==-1){
				return null;
			}else{
				HTPair pair = bucket.getAt(findAt);
				bucket.removeAt(findAt);
				this.size--;
				return pair.value;
			}
		}
	}
	
	public void rehash()throws Exception{
		LinkedList<HTPair>[] oba = this.bucketArray;
		this.bucketArray = (LinkedList<HTPair>[]) new LinkedList[2*oba.length];
		this.size=0;
		for(LinkedList<HTPair> ob : oba){
			while(ob!=null && !ob.isEmpty()){
				HTPair pair = ob.removeFirst();
				this.put(pair.key, pair.value);
			}
		}
	}

}
