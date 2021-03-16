package avi.codechef.march_2021;

import java.io.*;
import java.util.Arrays;

class NoTimeToWait {


	public static String solve(Integer x, Integer t, Integer[] timeZones) {
		Arrays.sort(timeZones);
		if(timeZones[timeZones.length-1]+t>=x)
			return "YES";
		else return "NO";

	}
}

public class NoTimeToWait_Basic {

	public static void main(String[] args) throws IOException {
		//File file = new File("resources\\Codechef\\NoTimeToWait_Basic_Output.txt");
		//file.createNewFile();
		//	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		//BufferedReader bufferedReader = new BufferedReader(
		//		(new FileReader("resources\\Codechef\\NoTimeToWait_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//int queries = Integer.parseInt(bufferedReader.readLine());
		//while(queries>0) {

		File file = new File(NoTimeToWait_Basic.class.getClassLoader().getResource("Codechef/NoTimeToWait_Basic_Output.txt").getPath());
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

		BufferedReader bufferedReader = new BufferedReader((new FileReader(new File(NoTimeToWait_Basic.class.getClassLoader().getResource("Codechef/NoTimeToWait_Basic_Input.txt").getPath()))));

		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		Integer N = Integer.parseInt(params[0]);
		Integer x = Integer.parseInt(params[1]);
		Integer T = Integer.parseInt(params[2]);
		Integer[] timeZones = new Integer[N];
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		for(int i =0; i<params.length; i++) {
			timeZones[i]=Integer.parseInt(params[i]);
		}
		String result = NoTimeToWait.solve(x,T,timeZones);

		bufferedWriter.write(result);
		bufferedWriter.newLine();
		//queries--;
		//}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
