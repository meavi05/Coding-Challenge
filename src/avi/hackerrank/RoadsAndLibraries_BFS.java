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

class RoadsAndLibraries {
	
	/**
	 * This modified BFS will traverse through the graph and add the road cost
	 * return the total road cost for connecting the graph component.
	 * @param adjList
	 * @param rc
	 * @param visited
	 * @param s
	 * @return
	 */
	public static long modifiedBFS(List<List<Integer>> adjList, long roadCost, boolean[] visited, int s) {
		long totalRoadCost = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s] = true;
		queue.add(s);
		while (queue.size() != 0) {
			int cs = queue.poll();
			Iterator<Integer> i = adjList.get(cs).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					totalRoadCost = totalRoadCost + roadCost;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return totalRoadCost;
	}

	/**
	 * 
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
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
	public static long findCheapestCost(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo, int libCost,
			int roadCost) {
		long rc = roadCost;
		long lc = libCost;
		if(roadCost>=libCost)
			return libCost*graphNodes;
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(graphNodes + 1);
		for (int i = 1; i <= graphNodes + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= graphFrom.size(); i++) {
			adjList.get(graphFrom.get(i - 1)).add(graphTo.get(i - 1));
			adjList.get(graphTo.get(i - 1)).add(graphFrom.get(i - 1));
		}
		long result = 0;
		boolean visited[] = new boolean[adjList.size()];
		for(int t=1;t<visited.length;t++) {
			if(visited[t]==false) 
				result = result + lc + RoadsAndLibraries.modifiedBFS(adjList,rc,visited,t);
		}
		return result;
	}
}
public class RoadsAndLibraries_BFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\RoadsAndLibraries_BFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\RoadsAndLibraries_BFS_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int graphNodes = Integer.parseInt(params[0]);
		int graphEdges = Integer.parseInt(params[1]);
		int libCost = Integer.parseInt(params[2]);
		int roadCost = Integer.parseInt(params[3]);
		List<Integer> graphFrom = new ArrayList<>();
		List<Integer> graphTo = new ArrayList<>();
		for (int i = 0; i < graphEdges; i++) {
			String[] graphFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			graphFrom.add(Integer.parseInt(graphFromTo[0]));
			graphTo.add(Integer.parseInt(graphFromTo[1]));
		}
		long result = RoadsAndLibraries.findCheapestCost(graphNodes, graphFrom, graphTo, libCost,roadCost);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
