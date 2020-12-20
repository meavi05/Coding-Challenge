package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class JourneyToMoon {
	
	static int recursionCount = 0;
	

	static int fact(int n) {
		if(n==1) return 1;
		return n*fact(n-1);
	}
	
	public static long modifiedBFS(List<List<Integer>> adjList, boolean[] visited, int s) {
		long sameCountryAstronauts = 1;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s] = true;
		queue.add(s);
		while (queue.size() != 0) {
			int cs = queue.poll();
			Iterator<Integer> i = adjList.get(cs).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					sameCountryAstronauts++;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return sameCountryAstronauts;
	}

	/**
	 * 
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
	 * @param graphTo 
	 * @param graphFrom 
	 * @param graphNodes 
	 * @param y 
	 * @param x 
     *
     *
	 * 
	 * @param graphNodes
	 * @param graphFrom
	 * @param graphTo
	 * @param colorToAnalyse
	 * @param roadCost
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
		boolean[] visited = new boolean[graphNodes];
		int graphComponents = 0;
		long [] eachgraphComponentLength = new long[graphNodes];
		for(int i=0;i<visited.length;i++) {
			if(!visited[i]) {
				eachgraphComponentLength[graphComponents]=JourneyToMoon.modifiedBFS(adjList, visited, i);
				graphComponents=graphComponents+1;
			}
		}
		long totalPairs = (1l*graphNodes*(graphNodes-1))/2;
		long sameCountryPairs = 0;
		for(int i = 0;i<graphComponents;i++) {
			long l = eachgraphComponentLength[i];
			sameCountryPairs = sameCountryPairs+(l*(l-1))/2;
		}
		return (totalPairs-sameCountryPairs);
	}
	
	
}
public class JourneyToMoon_BFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\JourneyToMoon_BFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\JourneyToMoon_BFS_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
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
