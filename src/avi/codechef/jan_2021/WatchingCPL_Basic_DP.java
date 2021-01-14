package avi.codechef.jan_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class WatchingCPL {
	
	private static int N = 4001;
	private static long MAX = Integer.MAX_VALUE;
	static long dp [][] = new long [N][N];
			
	/**
	 * dp[i][K] denotes the i number of boxes used to get minimum height greater than K.
	 * remainHeight = (suffixSum[i]-dp[i][K]) denotes height that can be achieved by removing K
	 * If(remainHeight>=K) that means you can another K size stand.
	 * @param n
	 * @param k
	 * @param h
	 * @return
	 */
	public static int solve(int n, int k, long[] h) {
		Arrays.sort(h);
		long[] suffixSum = new long[N];
		suffixSum[n]=0;
		for(int i=n-1;i>=0;i--) {
			suffixSum[i]=suffixSum[i+1]+h[i];
		}
		for(int i = 0; i <= n ; i++)
	        for(int j = 0; j <= k; j++)
	            dp[i][j] = MAX;
		dp[n][0] = 0;
	    for(int i = n-1; i >= 0; i--){
	        for(int j = k; j >= 0; j--){
	            if(j <= h[i]){
	                dp[i][j] = h[i];
	                continue;
	            }
	            if(dp[i+1][(int) (j-h[i])] == MAX)
	                dp[i][j] = MAX;
	            else
	                dp[i][j] = min(dp[i+1][j], dp[i+1][(int) (j-h[i])] + h[i]);
	        }
	    }
	    for(int i = n-1; i >= 0; i--)
	        if(suffixSum[i] - dp[i][k] >= k){
	            return n-i;
	        }
	    return -1;
	}

	private static long min(long l, long m) {
		return	l>m? m:l;
	}

}

/**
 * This DP problem is a modified implementation of 0-1 KnapSack problem 
 * @author avi08
 *
 */
public class WatchingCPL_Basic_DP {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\WatchingCPL_Basic_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\WatchingCPL_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int n = Integer.parseInt(params[0]);
		int k = Integer.parseInt(params[1]);
		long [] h = new long[n];
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		for(int i =0;i< params.length;i++) {
			h[i] = Long.parseLong(params[i]);
		}
		int result = WatchingCPL.solve(n,k,h);

		bufferedWriter.write(String.valueOf(Arrays.asList(result)));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
