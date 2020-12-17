package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ShortestReach {
	
	/**
	 * This modified BFS will traverse through the graph and add the road cost
	 * return the total road cost for connecting the graph component.
	 * @param adjList
	 * @param rc
	 * @return
	 */
	public static List<Integer> modifiedBFS(List<List<Integer>> adjList, int s) {
		boolean visited[] = new boolean [adjList.size()];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		Map<Integer,Integer> distances = new HashMap<Integer,Integer>();
		for(int k=0;k<adjList.size();k++) {distances.put(k, 0);}
		visited[s] = true;
		queue.add(s);
		while (queue.size() != 0) {
			int cs = queue.poll();
			Iterator<Integer> i = adjList.get(cs).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					distances.put(n, (distances.get(cs)+6));
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		distances.remove(s);
		distances.remove(0);
		List<Integer> result = distances.values().stream().map(i -> i==0?-1:i).collect(Collectors.toList());
		return result;
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
	public static List<Integer> findShortestReach(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo, int source) {
		List<Integer> result = new ArrayList<Integer>();
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(graphNodes + 1);
		for (int i = 1; i <= graphNodes + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= graphFrom.size(); i++) {
			adjList.get(graphFrom.get(i - 1)).add(graphTo.get(i - 1));
			adjList.get(graphTo.get(i - 1)).add(graphFrom.get(i - 1));
		}
		result = ShortestReach.modifiedBFS(adjList,source);
		return result;
	}
}
public class ShortestReach_BFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\ShortestReach_BFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\ShortestReach_BFS_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int graphNodes = Integer.parseInt(params[0]);
		int graphEdges = Integer.parseInt(params[1]);
		List<Integer> graphFrom = new ArrayList<>();
		List<Integer> graphTo = new ArrayList<>();
		for (int i = 0; i < graphEdges; i++) {
			String[] graphFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			graphFrom.add(Integer.parseInt(graphFromTo[0]));
			graphTo.add(Integer.parseInt(graphFromTo[1]));
		}
		int source = Integer.parseInt(bufferedReader.readLine().trim());
		List<Integer> result = ShortestReach.findShortestReach(graphNodes, graphFrom, graphTo, source);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
