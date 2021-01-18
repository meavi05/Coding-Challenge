/**
 * 
 */
package avi.codechef.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class CountSubarrays {
	
	private static int dp[] = new int[10];
	
	public static int solve(int n, long[] a) {
		longestIncreasingSubSequence(n,a);
		return longestIncreasingSubArray(n, a);
	}

	private static int longestIncreasingSubSequence(int n, long[] a) {
		dp[0]=1;
		for(int i =1;i<n;i++) {
			dp[i]=1;
			for(int j=0;j<i;j++) {
				if(a[j]<a[i] && dp[i]<dp[j]+1) dp[i]=dp[j]+1;
			}
		}
		int result = 0;
		for(int i =0;i<n;i++) {
			result = result + dp[i];
		}
		return result;
	}
	private static int longestIncreasingSubArray(int n, long[] a) {
		dp[0]=1;
		for(int i =1;i<n;i++) {
			if(a[i-1]<a[i]) {
				dp[i]=dp[i-1]+1;
			}else {
				dp[i]=1;
			}
		}
		int result = 0;
		for(int i =0;i<n;i++) {
			result = result + dp[i];
		}
		return result;
	}
}

/**
 * @author avi08
 *
 */
public class CountIncreasingSubarraysAndSubsequence_DP{

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\CountSubarrays_DP_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\CountSubarrays_DP_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int n = Integer.parseInt(params[0]);
		long [] a = new long[n];
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		for(int i =0;i< params.length;i++) {
			a[i] = Long.parseLong(params[i]);
		}
		int result = CountSubarrays.solve(n,a);

		bufferedWriter.write(String.valueOf(Arrays.asList(result)));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
