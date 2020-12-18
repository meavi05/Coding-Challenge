package avi.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ANZ {
	
	static int recursionCount = 0;
	
	/**
	 * This method take the list of number(array of numbers) and provides all the sub-array having 
	 * equal number of x and y present in the sub-array.
	 * @param a
	 * @param x
	 * @param y
	 * @return
	 */
	static List<List<Integer>> subArraywithSameXY(List<Integer> a, int x, int y) {
		int countSubArray = 0;
		List<List<Integer>> subArrays = new ArrayList<List<Integer>>();
		// Check for each subarray for the required condition
		for (int i = 0; i <= a.size() - 1; i++) {
			int ctX = 0, ctY = 0;
			int start = i;
			for (int j = i; j <= a.size() - 1; j++) {
				if (a.get(j) == x) {
					start = start == j ? j : start;
					ctX += 1;
				} else if (a.get(j) == y) {
					ctY += 1;
				}
				if (ctX == ctY) {
					subArrays.add(new ArrayList<Integer>(a.subList(start, j + 1)));
					countSubArray = countSubArray + 1;
				}
			}
		}
		return (subArrays);
	}

	/**
	 * Recursive implementation to find all the sub-array of the given array
	 * @param arr
	 * @param start
	 * @param end
	 * @param subArrays
	 * @return
	 */
	static List<List<Integer>> printRecursiveSubArrays(List<Integer>arr, int start, int end,List<List<Integer>> subArrays) 
	{      
		recursionCount++;
	    // Stop if we have reached the end of the array      
	    if (end == arr.size())  
	        return subArrays; 
	      
	    // Increment the end point and start from 0  
	    else if (start > end)  
	    	printRecursiveSubArrays(arr, 0, end + 1,subArrays); 
	          
	    // Print the subarray and increment the starting point  
	    else
	    { 
	    	System.out.print("["); 
	        List<Integer> subarray = new ArrayList<Integer>(); 
	        for (int i = start; i < end; i++){ 
	            System.out.print(arr.get(i)+", "); 
	            subarray.add(arr.get(i));
	        } 
	        subarray.add(arr.get(end)); 
	        subArrays.add(subarray);
	        System.out.println(arr.get(end)+"]"); 
	        printRecursiveSubArrays(arr, start + 1, end,subArrays); 
	    } 
	      
	    return subArrays; 
	} 
	
	/**
	 * Iterative implementation to find all the sub-array of the given array
	 * @param arr
	 * @param subArrays
	 * @return
	 */
	static List<List<Integer>> printIterativeSubArrays(List<Integer>arr,List<List<Integer>> subArrays) 
	{
	        // Pick starting point
	        for (int i=0; i <arr.size(); i++)
	        {
	            // Pick ending point
	            for (int j=i; j<arr.size(); j++)
	            {
	                // Print subarray between current starting
	                // and ending points
	            	List<Integer> subArray = new ArrayList<Integer>();
	                for (int k=i; k<=j; k++)
	                	subArray.add(arr.get(j));
	                subArrays.add(subArray);
	            }
	        }
		return subArrays;      
	} 

	/**
	 * 
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
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
	public static List<List<Integer>> solve(List<Integer> list, Integer x, Integer y) {
		List<List<Integer>> recursiveSubArrays = new ArrayList<List<Integer>>();
		List<List<Integer>> iterativeSubArrays = new ArrayList<List<Integer>>();
		 recursiveSubArrays = printRecursiveSubArrays(list,0,0,recursiveSubArrays);
		 iterativeSubArrays = printIterativeSubArrays(list,iterativeSubArrays);
		 List<List<Integer>> subArrayswithSameXY = subArraywithSameXY(list,x,y);
		return subArrayswithSameXY;
		
	}
	
	
}
public class ANZ_HackerRank {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\ANZ_HackerRank_Output.txt");
		file.createNewFile();
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\ANZ_HackerRank_Input.txt")));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		List<Integer> list = Arrays.asList(params).stream().map(param -> Integer.parseInt(param)).collect(Collectors.toList());
		Integer x = Integer.parseInt(bufferedReader.readLine().trim());
		Integer y = Integer.parseInt(bufferedReader.readLine().trim());
		List<List<Integer>> result = ANZ.solve(list,x,y);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
