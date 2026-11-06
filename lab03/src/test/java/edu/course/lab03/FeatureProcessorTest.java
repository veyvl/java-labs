package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FeatureProcessorTest {

    @Test
    void processUsesGivenStrategy() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());

        double[] result = processor.process(new double[] {0.0, 5.0, 10.0});

        assertArrayEquals(new double[] {0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void strategyCanBeReplacedWithoutChangingProcessor() {
        double[] vector = {1.0, 2.0, 3.0};
        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor meanCentering = new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[] {0.0, 0.5, 1.0}, minMax.process(vector), 1e-9);
        assertArrayEquals(new double[] {-1.0, 0.0, 1.0}, meanCentering.process(vector), 1e-9);
    }

    @Test
    void processDoesNotModifyInputVector() {
        double[] vector = {4.0, 2.0, 6.0};
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());

        processor.process(vector);

        assertArrayEquals(new double[] {4.0, 2.0, 6.0}, vector, 1e-9);
    }

    @Test
    void rejectsNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
}