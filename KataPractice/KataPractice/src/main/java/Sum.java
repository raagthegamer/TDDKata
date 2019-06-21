package main.java;

import java.util.regex.Pattern;

public class Sum {

	public static int add(String numbers) throws Exception {

		if (numbers.length() == 0) {
			return 0;
		}

		if (numbers.length() == 1) {
			return Integer.parseInt(numbers);
		}

		StringBuilder sb = new StringBuilder("");
		boolean neg = false;
		for (int i = 0; i < numbers.length(); i++) {

			if (numbers.toCharArray()[i] == ('-')) {
				int negative = i + 1;
				Character negNumber = numbers.charAt(negative);
				sb.append(" -" + negNumber);
				neg = true;
			}
		}
		if (neg) {
			throw new Exception("negatives not allowed" + sb);
		}

		String delimiter = ",|\n";

		if (numbers.contains("//")) {
			delimiter = numbers.substring(2, 3);
			// only one delimiter
			if (numbers.contains("[") && numbers.contains("]") && numbers.charAt(numbers.indexOf(']') + 1) != '[') {

				delimiter = numbers.substring(3, numbers.indexOf(']'));

				numbers = numbers.substring(numbers.indexOf(']') + 2);
			} 
			// if there are multiple delimiters
			else if (numbers.contains("[") && numbers.contains("]")
					&& numbers.charAt(numbers.indexOf(']') + 1) == '[') {
				delimiter = numbers.substring(3, numbers.indexOf(']')) + "|"
						+ numbers.substring(numbers.indexOf(']') + 2, numbers.indexOf(']', numbers.indexOf(']') + 2));
				numbers = numbers.substring(numbers.indexOf('\n') + 1);
			} else {
				numbers = numbers.substring(4);
			}
		}

		String[] inputsArray = null;

		// if the delimiter is a meta character
		if (delimiter.contains("*") || delimiter.contains("?") || delimiter.contains("+") || delimiter.contains(".")) {
			if (delimiter.contains("|")) {
				inputsArray = numbers.split("[" + delimiter + "]+");
			} else {
				inputsArray = numbers.split(Pattern.quote(delimiter));
			}
		} else {
			inputsArray = numbers.split(delimiter);
		}

		int sum = 0;

		for (int i = 0; i < inputsArray.length; i++) {
			if (Integer.parseInt(inputsArray[i].trim()) > 1000) {
				inputsArray[i] = "0";
			}
			sum += Integer.parseInt(inputsArray[i].trim());
		}

		return sum;

	}

//	public static void main(String[] args) throws Exception {
//		int ans = add("//[***][%%]\n1***2%%3");
//		System.out.println(ans);
//	}

}
