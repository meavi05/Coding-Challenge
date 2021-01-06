package avi.codechef.jan_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class EncodedString {


	public static String solve(String binaryValue, int numberOfBits) {
		String result = "";
		String [] binaryLetters = new String[numberOfBits/4];
		String [] alphabets = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
		int count = 0;
		for(int i =0;i<binaryValue.length();i= i+4)
		binaryLetters[count++]=binaryValue.substring(i, i+4);
		for(String s: binaryLetters) {
			int decimalValue = Integer.parseInt(s, 2);
			result =  result + alphabets[decimalValue];
		}
		return result;
	}
	
}
public class EncodedString_Basic {

	public static void main(String[] args) throws IOException {
		File file = new File("resources\\Codechef\\EncodedString_Basic_Output.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		BufferedReader bufferedReader = new BufferedReader(
				(new FileReader("resources\\Codechef\\EncodedString_Basic_Input.txt")));
		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(bufferedReader.readLine());
		while(queries>0) {
		int numberOfBits = Integer.parseInt((bufferedReader.readLine().replaceAll("\\s+$", "")));
		String binaryValue = bufferedReader.readLine().replaceAll("\\s+$", "");
		
		String result = EncodedString.solve(binaryValue,numberOfBits);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		queries--;
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
