package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ResultNearestClone {
	public static int modifiedBFS(List<List<Integer>> adjList, List<Integer> color, int s) {
		boolean visited[] = new boolean[adjList.size() + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		//HashMap created to track the distance of the matching color node
		Map<Integer,Integer> distances = new HashMap<Integer,Integer> (adjList.size() + 1);
		for(int k=0;k<=adjList.size();k++) {distances.put(k, 0);}
		visited[s] = true;
		queue.add(s);
		while (queue.size() != 0) {
			int cs = queue.poll();
			Iterator<Integer> i = adjList.get(cs).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					if(color.get(n-1)==color.get(s-1)) {
						return distances.get(cs)+distances.get(n)+1;
					}
					visited[n] = true;
					queue.add(n);
					distances.put(n, distances.get(cs)+1);
				}
			}
		}
		return -1;
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
	 * @param color
	 * @return
	 */
	public static int findNearestColor(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo,
			int colorToAnalyse, List<Integer> color) {
		int minValue = -1;
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(graphNodes + 1);
		for (int i = 1; i <= graphNodes + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= graphFrom.size(); i++) {
			adjList.get(graphFrom.get(i - 1)).add(graphTo.get(i - 1));
			adjList.get(graphTo.get(i - 1)).add(graphFrom.get(i - 1));
		}
		
		List<Integer> indexOfnodesToAnalyze = IntStream.range(0, color.size()).filter(i -> (colorToAnalyse == color.get(i)))
				.mapToObj(i -> i).collect(Collectors.toList());
		minValue = indexOfnodesToAnalyze.size() < 2 ? -1 : adjList.size();
		for (int nodeIndex : indexOfnodesToAnalyze) {
			int result = ResultNearestClone.modifiedBFS(adjList, color, nodeIndex + 1);
			minValue = result != -1 ? result < minValue ? result : minValue : -1;
		}
		return minValue;
	}
}
public class NearestClone_BFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\NearestClone_BFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\NearestClone_BFS_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		String[] graphNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int graphNodes = Integer.parseInt(graphNodesEdges[0]);
		int graphEdges = Integer.parseInt(graphNodesEdges[1]);
		List<Integer> graphFrom = new ArrayList<>();
		List<Integer> graphTo = new ArrayList<>();
		for (int i = 0; i < graphEdges; i++) {
			String[] graphFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			graphFrom.add(Integer.parseInt(graphFromTo[0]));
			graphTo.add(Integer.parseInt(graphFromTo[1]));
		}
		List<Integer> color = Arrays.asList(bufferedReader.readLine().trim().split(" "))
							.stream().map(c -> Integer.parseInt(c)).collect(Collectors.toList());
		int colorToAnalyse = Integer.parseInt(bufferedReader.readLine().trim());
		int result = ResultNearestClone.findNearestColor(graphNodes, graphFrom, graphTo, colorToAnalyse,color);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		bufferedReader.close();
		bufferedWriter.close();
	}
}
