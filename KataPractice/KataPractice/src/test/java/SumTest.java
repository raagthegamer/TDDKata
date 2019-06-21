package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.Sum;

class SumTest {

	@Test
	public void returnZeroOnEmptyString() throws Exception {
		assertEquals(0, Sum.add(""));
	}

	@Test
	public void returnNumberOnSingleInput() throws Exception {
		assertEquals(1, Sum.add("1"));
	}

	@Test
	public void returnSumOfTwoInputs() throws Exception {
		assertEquals(3, Sum.add("1,2"));
	}

	@Test
	public void returnSumOfUnknownNumberOfInputs() throws Exception {
		assertEquals(10, Sum.add("1,2,3,4"));
	}

	@Test
	public void allowNewLineAsDelimeter() throws Exception {
		assertEquals(6, Sum.add("1 \n2,3"));
	}

	@Test
	public void canSetCustomDelimeter() throws Exception {
		assertEquals(3, Sum.add("//;\n1;2"));
	}

	@Test
	public void throwExceptionOnNegativeInput() throws Exception {
		assertEquals("java.lang.Exception: negatives not allowed -1 -2 -3",
				assertThrows(Exception.class, () -> Sum.add("-1, -2, -3")).toString());
	}

	@Test
	public void ignoreNumbersGreaterThanOneThousand() throws Exception {
		assertEquals(2, Sum.add("2, 1001"));
	}

	@Test
	public void allowDelimeterOfAnyLength() throws Exception {
		assertEquals(6, Sum.add("//[***]\n1***2***3"));
	}

	@Test
	public void allowMultipleDelimeters() throws Exception {
		assertEquals(6, Sum.add("//[*][%]\n1*2%3"));
	}
	
	@Test
	public void allowMultipleDelimetersOfMoreThanOneChar() throws Exception
	{
		assertEquals(6, Sum.add("//[***][%%]\n1***2%%3"));
	}

}
