package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class JourneyToMoon {

	static int recursionCount = 0;

	static int fact(int n) {
		if (n == 1)
			return 1;
		return n * fact(n - 1);
	}

	public static long modifiedBFS(List<List<Integer>> adjList, boolean[] visited, int s, Set<Integer> componentDetails) {
		long sameCountryAstronauts = 1;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s] = true;
		componentDetails.add(s);
		queue.add(s);
		while (queue.size() != 0) {
			int cs = queue.poll();
			Iterator<Integer> i = adjList.get(cs).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					componentDetails.add(n);
					sameCountryAstronauts++;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return sameCountryAstronauts;
	}

	private static int parent(int currentElement, int parent[]) {
		if (parent[currentElement] == currentElement)
			return currentElement;
		else {
			int k = parent(parent[currentElement], parent);
			parent[currentElement] = k;
			return k;
		}
	}

	private static void union(int start, int end, int parent[]) {
		int parent_s = parent(start, parent), parent_e = parent(end, parent);
		if (parent_s != parent_e) {
			parent[parent_s] = parent_e;
		}
	}

/**
 * 
 * @param graphNodes
 * @param graphFrom
 * @param graphTo
 * @return
 */
	public static long solve(Integer graphNodes, List<Integer> graphFrom, List<Integer> graphTo) {

		List<List<Integer>> adjList = new ArrayList<List<Integer>>(graphNodes);
		for (int i = 0; i < graphNodes; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < graphFrom.size(); i++) {
			adjList.get(graphFrom.get(i)).add(graphTo.get(i));
			adjList.get(graphTo.get(i)).add(graphFrom.get(i));
		}

		/*
		 * The same problem can be solved by Union-Find for disjoint sets. Uncomment to
		 * see the implementation
		 */
		//getGraphComponentsDetails_UnionFind(graphNodes, graphFrom, graphTo);

		boolean[] visited = new boolean[graphNodes];
		int graphComponents = 0;
		long[] eachgraphComponentLength = new long[graphNodes];
		List<Set<Integer>>graphComponentsDetail = new ArrayList<Set<Integer>>();
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				Set<Integer> componentDetails = new HashSet<Integer>();
				eachgraphComponentLength[graphComponents] = JourneyToMoon.modifiedBFS(adjList, visited, i,componentDetails);
				graphComponentsDetail.add(componentDetails);
				graphComponents = graphComponents + 1;
			}
		}
		long totalPairs = (1l * graphNodes * (graphNodes - 1)) / 2;
		long sameCountryPairs = 0;
		for (int i = 0; i < graphComponents; i++) {
			long l = eachgraphComponentLength[i];
			sameCountryPairs = sameCountryPairs + (l * (l - 1)) / 2;
		}
		return (totalPairs - sameCountryPairs);
	}

	public static void getGraphComponentsDetails_UnionFind(Integer graphNodes, List<Integer> graphFrom,
			List<Integer> graphTo) {
		int parent[] = new int[graphNodes];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < graphFrom.size(); i++) {
			JourneyToMoon.union(graphFrom.get(i), graphTo.get(i), parent);
		}

		boolean[] visitedNodes = new boolean[graphNodes];
		Map<Integer, HashSet<Integer>> rootMapSets = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < parent.length; i++) {
			if(!visitedNodes[i]) {
			HashSet<Integer> set = new HashSet<Integer>();
			int k = i;
			while (parent[k] != k) {
				set.add(k);
				set.add(parent[k]);
				visitedNodes[k]=true;
				k = parent[k];
			}
			visitedNodes[k]=true;
			if(null != rootMapSets.get(k)) {
				HashSet<Integer> currentSet =  rootMapSets.get(k);
				currentSet.addAll(set);	
				rootMapSets.put(k, currentSet);
			}else
			rootMapSets.put(k, set);
			}
		}
	}

}

public class JourneyToMoon_BFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\JourneyToMoon_BFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader((new FileReader("resources\\JourneyToMoon_BFS_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while (queries > 0) {
			String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			Integer graphNodes = Integer.parseInt(params[0]);
			Integer graphEdges = Integer.parseInt(params[1]);
			List<Integer> graphFrom = new ArrayList<>();
			List<Integer> graphTo = new ArrayList<>();
			for (int i = 0; i < graphEdges; i++) {
				String[] astronautRowItems = bufferedReader.readLine().trim().split(" ");
				graphFrom.add(Integer.parseInt(astronautRowItems[0]));
				graphTo.add(Integer.parseInt(astronautRowItems[1]));
			}
			long result = JourneyToMoon.solve(graphNodes, graphFrom, graphTo);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
			queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
