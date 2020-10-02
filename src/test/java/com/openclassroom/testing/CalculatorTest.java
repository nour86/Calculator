package com.openclassroom.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;
	
	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}
	@AfterAll
	public static void showDuration() {
		System.out.println("Appel après tous les tests");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}
	
	
	@BeforeEach
	public void initCalculator() {
	calculatorUnderTest = new Calculator();
	System.out.println("Appel avant chaque test");
	}
	
	@AfterEach
	public void undefCalculator() {
		System.out.println("Appel après chaque test");
		calculatorUnderTest= null;
	}
	
	@Test
	void testAddTwoPositiveNumbers() {
			// ARRANGE
		int a = 2;
		int b = 3;
			// ACT
		int somme = calculatorUnderTest.add(a,b);
			// ASSERT
		assertThat(somme).isEqualTo(5);
	}
	@Test
	void  testMultiplyTwoPositiveNumbers() {
		
		//ARRANGE
		int a = 2;
		int b = 3;
		//ACT
		int produit = calculatorUnderTest.multi(a,b);
		// ASSERT
		assertThat(produit).isEqualTo(6);
	}
	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = {1,2,4,61,1202 })
	public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		//ARRANGE
		//ACT
		final int actualResult = calculatorUnderTest.multi(arg, 0);
		//ASSERT
		assertThat(actualResult).isEqualTo(0);
	}
	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({"1,2,3","2,3,5","45,54,99"})
	public void add_shouldReturnTheSum_ofMultiplieIntergers(int arg1, int arg2, int expectResult) {
		//ARRANGE
		//ACT
		final int actualResult = calculatorUnderTest.add(arg1, arg2);
		//ASSERT
		assertThat(actualResult).isEqualTo(expectResult);
	}
	
	
	@Test
	public void listDigits_shouldReturnTheListOfDigits_ofPositiveIntegers () {
		//GIVEN
		int number = 67854;
		//WHEN
		Set <Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		//THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(6,7,8,5,4);
	}
}
