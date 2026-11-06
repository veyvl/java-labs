package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForPositiveEvenNumber() {
        assertTrue(CourseToolkit.isEven(4));
    }

    @Test
    void returnsFalseForPositiveOddNumber() {
        assertFalse(CourseToolkit.isEven(7));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        int number = -8;

        boolean result = CourseToolkit.isEven(number);

        assertTrue(result);
    }

    @Test
    void isPrimeReturnsFalseForNumbersLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(1));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(-7));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsTrueForPrimeNumber() {
        assertTrue(CourseToolkit.isPrime(13));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumber() {
        assertFalse(CourseToolkit.isPrime(12));
    }

    @Test
    void isPrimeReturnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
        assertFalse(CourseToolkit.isPrime(9));
    }

    @Test
    void isPalindromeReturnsTrueForPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("java"));
    }

    @Test
    void isPalindromeIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void isPalindromeTreatsSpacesAsSignificant() {
        assertFalse(CourseToolkit.isPalindrome("a ba"));
    }

    @Test
    void isPalindromeReturnsTrueForEmptyString() {
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsFractionalResult() {
        assertEquals(2.5, CourseToolkit.average(new int[] {1, 2, 3, 4}), 1e-9);
    }

    @Test
    void averageWorksWithNegativeNumbers() {
        assertEquals(-2.0, CourseToolkit.average(new int[] {-1, -2, -3}), 1e-9);
    }

    @Test
    void averageDoesNotModifyInputArray() {
        int[] values = {5, 1, 3};

        CourseToolkit.average(values);

        assertArrayEquals(new int[] {5, 1, 3}, values);
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[0]));
    }
}