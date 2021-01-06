package avi.codechef.jan_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class PointOfImpact {

	public static long[] solve(long n, long k, long x, long y) {
		long[] result = new long[2];
		if (x==y) {
			result[0] = n;
			result[1] = n;
			return result;
		}
		long modifiedK = k % 4;
		long resultX = 0l, resultY = 0l;
		long movement = (x > y) ? n - x : n - y;
		long p = x + movement, q = y + movement;
		if (modifiedK == 1l) {
			resultX = p;
			resultY = q;
		}if (modifiedK == 2l) {
			resultX = q;
			resultY = p;
		}if (modifiedK == 3l) {
			if(x>y) {
				resultX = 0l;
				resultY = n-q;
			}else {
				resultX = n-p;
				resultY = 0l;
			}
		}if (modifiedK == 0l) {
			if(x>y) {
				resultX = n-q;
				resultY = 0l;
			}else {
				resultX = 0l;
				resultY = n-p;
			}
		}
		result[0] = resultX;
		result[1] = resultY;
		return result;
	}
	
}
public class PointOfImpact_Basic {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\PointOfImpact_Basic_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\PointOfImpact_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		long n = Long.parseLong(params[0]);
		long k = Long.parseLong(params[1]);
		long x = Long.parseLong(params[2]);
		long y = Long.parseLong(params[3]);
		long[] result = PointOfImpact.solve(n,k,x,y);

		bufferedWriter.write(String.valueOf(Arrays.asList(result)));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
