package Lecture22and23;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {

	private class Vertex {
		String name;
		HashMap<Vertex, Integer> neighbors;

		Vertex(String name) {
			this.name = name;
			this.neighbors = new HashMap<>();
		}

		public boolean equals(Object ot) {
			Vertex vtx = (Vertex) ot;
			return this.name.equals(vtx.name);
		}

		public int hashCode() {
			return this.name.hashCode();
		}

		public void display() {
			String rv = "";
			rv = rv + this.name + "=> ";
			Set<Vertex> nbs = this.neighbors.keySet();
			for (Vertex nb : nbs) {
				rv = rv + nb.name + "(" + this.neighbors.get(nb) + "), ";
			}
			rv = rv + "END";
			System.out.println(rv);
		}

	}

	private HashMap<String, Vertex> vertices;

	Graph() {
		this.vertices = new HashMap<>();
	}

	public int numVertices() {
		return this.vertices.size();
	}

	public void addVertex(String name) {
		if (this.vertices.containsKey(name)) {
			return;
		}
		Vertex vtx = new Vertex(name);
		this.vertices.put(name, vtx);
	}

	public void removeVertex(String name) {
		if (!this.vertices.containsKey(name)) {
			return;
		}
		Vertex vtx = this.vertices.get(name);
		Collection<Vertex> nbs = vtx.neighbors.keySet();
		for (Vertex nb : nbs) {
			nb.neighbors.remove(name);
		}

		this.vertices.remove(vtx);

	}

	public void display() {

		// Collection<Vertex> vtcs = this.vertices.values();
		// for(Vertex vtx : vtcs){
		// vtx.display();
		// }

		Set<String> vtxnames = this.vertices.keySet();

		for (String vtxname : vtxnames) {

			this.vertices.get(vtxname).display();

		}
		System.out.println("*****************");
	}

	public void addEdge(String s1, String s2, int weight) {

		Vertex v1 = this.vertices.get(s1);
		Vertex v2 = this.vertices.get(s2);

		if (v1 == null || v2 == null || v1.neighbors.containsKey(v2)) {
			return;
		}

		v1.neighbors.put(v2, weight);
		v2.neighbors.put(v1, weight);
	}

	public int numOfEdges() {
		int rv = 0;
		Collection<Vertex> vtcs = this.vertices.values();
		for (Vertex vtx : vtcs) {
			rv = rv + vtx.neighbors.size();
		}
		rv = rv / 2;
		return rv;
	}

	public void removeEdge(String s1, String s2) {
		Vertex v1 = this.vertices.get(s1);
		Vertex v2 = this.vertices.get(s2);

		if (v1 == null || v2 == null || !v1.neighbors.containsKey(v2)) {
			return;
		}

		v1.neighbors.remove(v2);
		v2.neighbors.remove(v1);
	}

	public boolean hasPath(String s1, String s2) {
		Vertex v1 = this.vertices.get(s1);
		Vertex v2 = this.vertices.get(s2);
		if (v1 == null || v2 == null) {
			return false;
		}
		HashMap<Vertex, Boolean> explored = new HashMap<>();
		// return hasPathDFS(v1, v2, explored);
		// return this.hasPathBFSI(v1, v2, explored);
		return this.hasPathDFSI(v1, v2, explored);
	}

	private boolean hasPathDFS(Vertex v1, Vertex v2, HashMap<Vertex, Boolean> explored) {

		if (!explored.containsKey(v1)) {
			explored.put(v1, true);
		} else {
			return false;
		}
		if (v1.neighbors.containsKey(v2)) {
			return true;
		}
		Set<Vertex> vtcs = v1.neighbors.keySet();
		for (Vertex vtx : vtcs) {
			if (hasPathDFS(vtx, v2, explored)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPathBFSI(Vertex v1, Vertex v2, HashMap<Vertex, Boolean> explored) {
		if (v1.neighbors.containsKey(v2)) {
			return true;
		}
		LinkedList<Vertex> queue = new LinkedList<>();
		queue.add(v1);
		while (!queue.isEmpty()) {
			Vertex rvtx = queue.removeFirst();
			if (!explored.containsKey(rvtx)) {
				explored.put(rvtx, true);
				if (rvtx.neighbors.containsKey(v2)) {
					return true;
				} else {
					Set<Vertex> vtcs = rvtx.neighbors.keySet();
					for (Vertex vtx : vtcs) {
						if (!explored.containsKey(vtx)) {
							queue.add(vtx);
						}
					}
				}
			}
		}

		return false;
	}

	public boolean hasPathDFSI(Vertex v1, Vertex v2, HashMap<Vertex, Boolean> explored) {
		LinkedList<Vertex> stack = new LinkedList<>();
		stack.add(v1);
		while (!stack.isEmpty()) {
			Vertex rvtx = stack.removeFirst();
			if (!explored.containsKey(rvtx)) {
				explored.put(rvtx, true);
				if (rvtx.neighbors.containsKey(v2)) {
					return true;
				} else {
					Set<Vertex> vtcs = rvtx.neighbors.keySet();
					for (Vertex vtx : vtcs) {
						if (!explored.containsKey(vtx)) {
							stack.addFirst(vtx);
						}
					}
				}
			}
		}

		return false;
	}

	public void DFT() {
		HashMap<Vertex, Boolean> explored = new HashMap<>();
		Collection<Vertex> keys = this.vertices.values();
		LinkedList<Vertex> stack = new LinkedList<>();
		for (Vertex key : keys) {
			if (!explored.containsKey(key)) {
				stack.addFirst(key);
			}

			while (!stack.isEmpty()) {
				Vertex rvtx = stack.removeFirst();
				if (!explored.containsKey(rvtx)) {
					explored.put(rvtx, true);
					System.out.println(rvtx.name);

					Set<Vertex> vtcs = rvtx.neighbors.keySet();
					for (Vertex vtx : vtcs) {
						if (!explored.containsKey(vtx)) {
							stack.addFirst(vtx);
						}
					}

				}
			}
		}
	}

	public void BFT() {
		HashMap<Vertex, Boolean> explored = new HashMap<>();
		Collection<Vertex> keys = this.vertices.values();
		LinkedList<Vertex> queue = new LinkedList<>();
		for (Vertex key : keys) {
			if (!explored.containsKey(key)) {
				queue.add(key);
			}

			while (!queue.isEmpty()) {
				Vertex rvtx = queue.removeFirst();
				if (!explored.containsKey(rvtx)) {
					explored.put(rvtx, true);
					System.out.println(rvtx.name);

					Set<Vertex> vtcs = rvtx.neighbors.keySet();
					for (Vertex vtx : vtcs) {
						if (!explored.containsKey(vtx)) {
							queue.add(vtx);
						}
					}

				}
			}
		}
	}

	public boolean isConnected() {
		HashMap<Vertex, Boolean> explored = new HashMap<>();
		Collection<Vertex> vtcs = this.vertices.values();
		LinkedList<Vertex> queue = new LinkedList<>();

		for (Vertex vtx : vtcs) {
			if (!explored.containsKey(vtx)) {
				queue.add(vtx);
				explored.put(vtx, true);
			}
			while (!queue.isEmpty()) {
				Vertex rvtx = queue.removeFirst();
				Set<Vertex> nbs = rvtx.neighbors.keySet();
				for (Vertex nb : nbs) {
					if (!explored.containsKey(nb)) {
						queue.add(nb);
						explored.put(nb, true);
					}
				}
			}
			if (explored.size() != this.vertices.size()) {
				return false;
			}
			break;
		}
		return true;
	}

	public void getCC() {
		HashMap<Vertex, Boolean> explored = new HashMap<>();
		Collection<Vertex> vtcs = this.vertices.values();
		LinkedList<Vertex> queue = new LinkedList<>();
		ArrayList<ArrayList<String>> lists = new ArrayList<>();
		for (Vertex vtx : vtcs) {
			ArrayList<String> list = new ArrayList<>();
			if (!explored.containsKey(vtx)) {
				queue.add(vtx);
			}
			while (!queue.isEmpty()) {
				Vertex rvtx = queue.removeFirst();
				if (!explored.containsKey(rvtx)) {
					explored.put(rvtx, true);
					list.add(rvtx.name);
					Set<Vertex> nbs = rvtx.neighbors.keySet();
					for (Vertex nb : nbs) {
						if (!explored.containsKey(nb)) {
							queue.add(nb);
						}
					}
				}
			}
			if (!list.isEmpty())
				lists.add(list);

		}
		System.out.println(lists);
	}

	public boolean isBipartite() {
		HashMap<Vertex, String> explored = new HashMap<>();
		Collection<Vertex> vtcs = this.vertices.values();
		LinkedList<Vertex> queue = new LinkedList<>();
		for (Vertex vtx : vtcs) {
			if (!explored.containsKey(vtx)) {
				explored.put(vtx, "R");
			}
			Set<Vertex> nbs = vtx.neighbors.keySet();
			for (Vertex nb : nbs) {
				if (!explored.containsKey(nb)){
					if (explored.get(vtx).equals("R")) {
						explored.put(nb, "W");
					} else {
						explored.put(nb, "R");
					}
				}else{
					if (explored.get(nb) == explored.get(vtx)) {
						return false;
					}
				}

			}

		}
		

		return true;
	}

}
