package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MeanCenteringNormalizationTest {

    private final NormalizationStrategy strategy = new MeanCenteringNormalization();

    @Test
    void subtractsMeanFromEachValue() {
        double[] result = strategy.normalize(new double[] {1.0, 2.0, 3.0});

        assertArrayEquals(new double[] {-1.0, 0.0, 1.0}, result, 1e-9);
    }

    @Test
    void resultSumsToZero() {
        double[] result = strategy.normalize(new double[] {4.0, 7.0, 10.0, 3.0});

        double sum = 0;
        for (double value : result) {
            sum += value;
        }
        assertEquals(0.0, sum, 1e-9);
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