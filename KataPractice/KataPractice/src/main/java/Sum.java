package main.java;

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

		String delimeter = ",|\n";

		if (numbers.contains("//")) {
			delimeter = numbers.substring(2, 3);
			numbers = numbers.substring(4);
		}

		String[] inputsArray = numbers.split(delimeter);
		int sum = 0;
		for (int i = 0; i < inputsArray.length; i++) {
			sum += Integer.parseInt(inputsArray[i].trim());
		}

		return sum;
	}

}
