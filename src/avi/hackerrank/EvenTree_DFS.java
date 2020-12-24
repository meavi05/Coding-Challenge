package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

class EvenTree {
	
	/**
	 * This modified BFS will traverse through the graph and add the road cost
	 * return the total road cost for connecting the graph component.
	 * @param adjList
	 * @param j 
	 * @param i 
	 * @param visited 
	 * @param subtrees 
	 * @param rc
	 * @return
	 */
	public static int modifiedDFS(List<List<Integer>> adjList, boolean[] visited,int s,List<Integer> subTreeCount) {
		visited[s] = true;
		ListIterator<Integer> i = adjList.get(s).listIterator();
		int nodeCount = 0;
		while(i.hasNext()) {
			int n = i.next();
			if(!visited[n]) {
			visited[n]=true;
			nodeCount++;
			int z = modifiedDFS(adjList,visited,n,subTreeCount);
			nodeCount = nodeCount + z;
			}
		}
		if (nodeCount % 2 != 0) {
			subTreeCount.add(s);
		}
		return nodeCount;
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
	public static long getEvenForest(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo) {
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(graphNodes + 1);
		for (int i = 1; i <= graphNodes + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= graphFrom.size(); i++) {
			adjList.get(graphFrom.get(i - 1)).add(graphTo.get(i - 1));
			adjList.get(graphTo.get(i - 1)).add(graphFrom.get(i - 1));
		}
		boolean visited[] = new boolean [adjList.size()+1];
		List<Integer> subtrees = new ArrayList<Integer>();
		EvenTree.modifiedDFS(adjList,visited,1,subtrees);
		System.out.println(subtrees);
		return subtrees.size()-1;
	}
	
	/**
	 * 
	 * Give the map of parent children mapping for every node, count of elements reacable from the node
	 * @param map
	 * @param count
	 * @param node
	 * @return
	 */
	public static int setCount(HashMap<Integer,ArrayList<Integer>> map, int[] count, int node){
		if(!map.containsKey(node)){
			count[node]=1;
			return 1;
		}
		ArrayList<Integer> list = map.get(node);
		int sum=1;
		for(int value:list){
			sum+=setCount(map,count,value);
		}
		count[node]= sum;
		return count[node];
	}
}
public class EvenTree_DFS {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\EvenTree_DFS_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\EvenTree_DFS_Input.txt")));
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
		long result = EvenTree.getEvenForest(graphNodes, graphFrom, graphTo);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
