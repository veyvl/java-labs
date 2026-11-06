package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MinMaxNormalizationTest {

    private final NormalizationStrategy strategy = new MinMaxNormalization();

    @Test
    void scalesValuesToZeroOneRange() {
        double[] result = strategy.normalize(new double[] {2.0, 4.0, 6.0});

        assertArrayEquals(new double[] {0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void worksWithNegativeValues() {
        double[] result = strategy.normalize(new double[] {-10.0, 0.0, 10.0});

        assertArrayEquals(new double[] {0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void returnsZerosWhenAllValuesAreEqual() {
        double[] result = strategy.normalize(new double[] {3.0, 3.0, 3.0});

        assertArrayEquals(new double[] {0.0, 0.0, 0.0}, result, 1e-9);
    }

    @Test
    void doesNotModifyInputArray() {
        double[] values = {5.0, 1.0, 3.0};

        strategy.normalize(values);

        assertArrayEquals(new double[] {5.0, 1.0, 3.0}, values, 1e-9);
    }

    @Test
    void rejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[0]));
    }
}