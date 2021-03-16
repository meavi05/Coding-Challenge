package avi.codechef.march_2021;

import java.io.*;

class InterestingXOR {


	static long[] decToBinary(long n) {
		long[] binaryNum = new long[1000];
		int i = 0;
		while (n > 0) {
			binaryNum[i] = n % 2;
			n = n / 2;
			i++;
		}
		int count = 0;
		long[] returnValue = new long[i];
		for (int j = i - 1; j >= 0; j--)
			returnValue[count++] = binaryNum[j];
		return returnValue;
	}

	static long binaryToDecimal(long n)
	{
		long dec_value = 0;
		long base = 1;

		long temp = n;
		while (temp>0) {
			long last_digit = temp % 10;
			temp = temp / 10;
			dec_value += last_digit * base;
			base = base * 2;
		}
		return dec_value;
	}

	public static Long solve(Long n) {
		long binaryNum[] = decToBinary(n);
		//String binaryString = Long.toBinaryString(n);
		int bLength = binaryNum.length;
		String one = "";
		String two = "";
		for (int i = 0; i < bLength; i++) {
			if (i == 0) {
				one = one +"1";
				two = two +"0";
				continue;
			}
			if (binaryNum[i] == 0) {
				one = one +"1";
			} else {
				one = one +"0";
			}
			two = two+"1";
		}
		Long binary_One = Long.parseLong(one);
		Long binary_Two = Long.parseLong(two);
		//Long decimal_One = binaryToDecimal(binary_One);
		///Long decimal_Two = binaryToDecimal(binary_Two);
		return ((Long.parseLong(one, 2))*(Long.parseLong(two, 2)));
	}
}

public class InterestingXOR_Basic {

	public static void main(String[] args) throws IOException {

		File file = new File(NoTimeToWait_Basic.class.getClassLoader().getResource("Codechef/InterestingXOR_Basic_Output.txt").getPath());
		file.createNewFile();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

		BufferedReader bufferedReader = new BufferedReader((new FileReader(new File(NoTimeToWait_Basic.class.getClassLoader().getResource("Codechef/InterestingXOR_Basic_Input.txt").getPath()))));

		Integer testCases = Integer.parseInt(bufferedReader.readLine());
		while(testCases-->0){
			Long input = Long.parseLong(bufferedReader.readLine());
			Long result = InterestingXOR.solve(input);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
