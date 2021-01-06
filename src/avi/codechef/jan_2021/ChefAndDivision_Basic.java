package avi.codechef.jan_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ChefAndDivision {

	public static Integer solve(Integer numberOfRequiredProblems, Integer numberOfDays, Integer totalNumberofProblem) {
		 return ((totalNumberofProblem/numberOfRequiredProblems)>numberOfDays)?numberOfDays:(totalNumberofProblem/numberOfRequiredProblems);
	}
	
}
public class ChefAndDivision_Basic {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\ChefAndDivision_Basic_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\ChefAndDivision_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		Integer numberOfRequiredProblems = Integer.parseInt(params[1]);
		Integer numberOfDays = Integer.parseInt(params[2]);
		Integer totalNumberofProblem = 0;
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		for(String s : params) {
			totalNumberofProblem = totalNumberofProblem + Integer.parseInt(s);
		}		
		Integer result = ChefAndDivision.solve(numberOfRequiredProblems,numberOfDays,totalNumberofProblem);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
