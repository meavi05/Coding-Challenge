package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Result {

	public static LinkedList<Integer> modifiedBFS(List<List<Integer>> adjList, int s) {
		boolean visited[] = new boolean[adjList.size() + 1];
		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll();
			result.add(s);

			Iterator<Integer> i = adjList.get(s).listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		result.poll();
		return result;
	}

	/*
	 * Complete the 'order' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * following parameters: 1. UNWEIGHTED_INTEGER_GRAPH city 2. INTEGER company
	 */
	/*
	 * For the unweighted graph, <name>:
	 *
	 * 1. The number of nodes is <name>Nodes. 2. The number of edges is <name>Edges.
	 * 3. An edge exists between <name>From[i] and <name>To[i].
	 *
	 */
	/**
	 * 
	 * @param cityNodes
	 * @param cityFrom
	 * @param cityTo
	 * @param company (starting Node)
	 * @return
	 */
	public static List<Integer> order(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(cityNodes + 1);
		for (int i = 1; i <= cityNodes+1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= cityFrom.size(); i++) {
			adjList.get(cityFrom.get(i-1)).add(cityTo.get(i-1));
		}
		for (int i = 1; i < cityFrom.size(); i++) {
			List<Integer> connectedCities = adjList.get(i);
			Collections.sort(connectedCities);
		}
		return Result.modifiedBFS(adjList, company);
	}
}

public class DeliveryManagementSystem_BFS {

	public static void main(String[] args) throws IOException {
			  File file = new File("resources\\DeliveryManagementSystem_BFS_Output.txt");
		      file.createNewFile();
			  BufferedReader bufferedReader = new BufferedReader((new FileReader("resources\\DeliveryManagementSystem_BFS_Input.txt")));
			  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			        String[] cityNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			        int cityNodes = Integer.parseInt(cityNodesEdges[0]);
			        int cityEdges = Integer.parseInt(cityNodesEdges[1]);
			        List<Integer> cityFrom = new ArrayList<>();
			        List<Integer> cityTo = new ArrayList<>();
			        for (int i = 0; i < cityEdges; i++) {
			            String[] cityFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			            cityFrom.add(Integer.parseInt(cityFromTo[0]));
			            cityTo.add(Integer.parseInt(cityFromTo[1]));
			        }
			        int company = Integer.parseInt(bufferedReader.readLine().trim());
			        List<Integer> result = Result.order(cityNodes, cityFrom, cityTo, company);
					
					  for (int i = 0; i < result.size(); i++) {
					  bufferedWriter.write(String.valueOf(result.get(i)));
					   if (i != result.size() -1) {
						   bufferedWriter.write("\n"); 
						   } 
					   }
			        bufferedWriter.newLine();
			        bufferedReader.close();
			        bufferedWriter.close();
			
		}

}
