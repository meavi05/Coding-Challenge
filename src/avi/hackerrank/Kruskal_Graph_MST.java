package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Graph {

	int numberOfVertex, numberOfEdge;
	List<Edge> edges;

	Graph(int numberOfVertex, int numberOfEdge) {
		this.numberOfVertex = numberOfVertex;
		this.numberOfEdge = numberOfEdge;
		edges = new ArrayList<Edge>(numberOfEdge);
		for (int i = 0; i < numberOfEdge; i++) {
			edges.add(new Edge());
		}
	}

	public List<List<Integer>> getAdjacencyList() {
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(numberOfVertex);
		for (int i = 0; i < numberOfVertex; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.size(); i++) {
			adjList.get(edges.get(i).source).add(edges.get(i).destination);
			adjList.get(edges.get(i).destination).add(edges.get(i).source);
		}
		return adjList;
	}
}

class Edge implements Comparable<Edge> {
	int source, destination, weight;

	@Override
	public int compareTo(Edge compareEdge) {
		return this.weight - compareEdge.weight;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source + ", destination=" + destination + ", weight=" + weight + "]";
	}
	
}

class subset {
	int parent, rank;
};

class Kruskal_MST {

	static int find(subset subsets[], int i) {
		// find root and make root as parent of i
		// (path compression)
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}

	static void Union(subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		// Attach smaller rank tree under root
		// of high rank tree (Union by Rank)
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;

		// If ranks are same, then make one as
		// root and increment its rank by one
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	public static List<Edge> solve(Graph graph) {
		List<Edge> result = new ArrayList<Edge>(graph.numberOfVertex);
		for (int i = 0; i < graph.numberOfVertex; ++i)
			result.add(new Edge());
		Collections.sort(graph.edges);
		subset subsets[] = new subset[graph.numberOfVertex];
		for (int i = 0; i < graph.numberOfVertex; ++i)
			subsets[i] = new subset();

		for (int v = 0; v < graph.numberOfVertex; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}
		int i = 0, e = 0; // Index used to pick next edge

		// Number of edges to be taken is equal to V-1
		while (e < graph.numberOfVertex - 1) {
			// Step 2: Pick the smallest edge. And increment
			// the index for next iteration
			Edge next_edge = new Edge();
			next_edge = graph.edges.get(i);
			i++;

			int x = find(subsets, next_edge.source);
			int y = find(subsets, next_edge.destination);

			// If including this edge does't cause cycle,
			// include it in result and increment the index
			// of result for next edge
			if (x != y) {
				result.set(e, next_edge);
				e++;
				Union(subsets, x, y);
			}
			// Else discard the next_edge
		}
		return result;
	}
}

public class Kruskal_Graph_MST {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Kruskal_Graph_MST_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader((new FileReader("resources\\Kruskal_Graph_MST_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while (queries > 0) {
			String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			Integer graphNodes = Integer.parseInt(params[0]);
			Integer graphEdges = Integer.parseInt(params[1]);
			Graph graph = new Graph(graphNodes, graphEdges);
			for (int i = 0; i < graphEdges; i++) {
				String[] graphEdge = bufferedReader.readLine().trim().split(" ");
				graph.edges.get(i).source = Integer.parseInt(graphEdge[0]);
				graph.edges.get(i).destination = Integer.parseInt(graphEdge[1]);
				if (null != graphEdge[2])
					graph.edges.get(i).weight = Integer.parseInt(graphEdge[2]);
			}
			List<Edge> results = Kruskal_MST.solve(graph);
			bufferedWriter.write(results.toString());
			bufferedWriter.newLine();
			queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
