package avi.codechef.jan_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class FairElections {

	public static Integer solve(Integer john_pack, Integer jack_pack, Long[] john_votes, Long[] jack_votes) {
		Arrays.sort(john_votes);
		Arrays.sort(jack_votes);
		int result = -1;
		for(int i=0;i<jack_votes.length/2;i++) {
			Long temp = jack_votes[i];
			jack_votes[i]=jack_votes[jack_votes.length-i-1];
			jack_votes[jack_votes.length-i-1]=temp;
		}
		Long john_sum=0l,jack_sum=0l;
		for(int i=0;i<jack_votes.length;i++) {
			jack_sum = jack_sum + jack_votes[i];
		}
		for(int i=0;i<john_votes.length;i++) {
			john_sum = john_sum + john_votes[i];
		}
		if(john_sum>jack_sum)return 0;
		else {
			int minLength = (john_pack>jack_pack?jack_pack:john_pack);
			int swaps = 0;
			for(int i = 0;i<minLength;i++) {
				john_sum = john_sum + jack_votes[i]- john_votes[i];
				jack_sum = jack_sum - jack_votes[i]+ john_votes[i];
				swaps++;
				if(john_sum>jack_sum) {
					result = swaps;
					break;
				}
			}
		}
		return result;
	}
	
}
public class FairElections_Basic {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\FairElections_Basic_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\FairElections_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		String params [] = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		Integer john_pack = Integer.parseInt(params[0]);
		Integer jack_pack = Integer.parseInt(params[1]);
		Long[] john_votes = new Long[john_pack];
		Long[] jack_votes = new Long[jack_pack];
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" "); 
		for(Integer i=0;i<params.length;i++) {
			john_votes[i]=Long.parseLong(params[i]);
		}	
		params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" "); 
		for(Integer i=0;i<params.length;i++) {
			jack_votes[i]=Long.parseLong(params[i]);
		}
		Integer result = FairElections.solve(john_pack,jack_pack,john_votes,jack_votes);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
