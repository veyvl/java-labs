package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DiscountPolicyTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void noDiscountRejectsNegativePrice() {
        DiscountPolicy policy = new NoDiscount();

        assertThrows(IllegalArgumentException.class, () -> policy.apply(-1.0));
    }

    @Test
    void percentDiscountOfTwentyGivesEightyForHundred() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void percentDiscountBoundaryValues() {
        assertEquals(100.0, new PercentDiscount(0).apply(100.0), 1e-9);
        assertEquals(0.0, new PercentDiscount(100).apply(100.0), 1e-9);
    }

    @Test
    void percentDiscountRejectsPercentOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
    }

    @Test
    void percentDiscountRejectsNegativePrice() {
        DiscountPolicy policy = new PercentDiscount(10);

        assertThrows(IllegalArgumentException.class, () -> policy.apply(-5.0));
    }

    @Test
    void priceCalculatorUsesGivenPolicyAndPolicyCanBeReplaced() {
        PriceCalculator withoutDiscount = new PriceCalculator(new NoDiscount());
        PriceCalculator withDiscount = new PriceCalculator(new PercentDiscount(20));

        assertEquals(100.0, withoutDiscount.calculate(100.0), 1e-9);
        assertEquals(80.0, withDiscount.calculate(100.0), 1e-9);
    }

    @Test
    void priceCalculatorRejectsNullPolicy() {
        assertThrows(IllegalArgumentException.class, () -> new PriceCalculator(null));
    }
}