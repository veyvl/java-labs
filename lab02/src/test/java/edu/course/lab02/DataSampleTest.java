package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DataSampleTest {

    @Test
    void createsValidSample() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0, 2.0});

        assertEquals("S-1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertArrayEquals(new double[] {1.0, 2.0}, sample.getFeatures());
        assertFalse(sample.isReady());
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(null, "cat", new double[] {1.0}));
    }

    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(" ", "cat", new double[] {1.0}));
    }

    @Test
    void rejectsNullLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", null, new double[] {1.0}));
    }

    @Test
    void rejectsEmptyLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", "", new double[] {1.0}));
    }

    @Test
    void rejectsNullFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", "cat", null));
    }

    @Test
    void rejectsEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", "cat", new double[0]));
    }

    @Test
    void rejectsNaNFeature() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", "cat", new double[] {1.0, Double.NaN}));
    }

    @Test
    void rejectsInfiniteFeature() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("S-1", "cat", new double[] {Double.POSITIVE_INFINITY}));
    }

    @Test
    void changeStatusToReadyMakesSampleReady() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0});

        sample.changeStatus(SampleStatus.READY);

        assertTrue(sample.isReady());
    }

    @Test
    void processedSampleIsNotReady() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0});

        sample.changeStatus(SampleStatus.PROCESSED);

        assertFalse(sample.isReady());
    }

    @Test
    void changeStatusRejectsNull() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0});

        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
        assertEquals(SampleStatus.NEW, sample.getStatus());
    }

    @Test
    void averageFeatureReturnsMean() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0, 2.0, 4.5});

        assertEquals(2.5, sample.averageFeature(), 1e-9);
    }

    @Test
    void changingOriginalArrayDoesNotAffectSample() {
        double[] source = {1.0, 2.0};
        DataSample sample = new DataSample("S-1", "cat", source);

        source[0] = 100.0;

        assertArrayEquals(new double[] {1.0, 2.0}, sample.getFeatures());
    }

    @Test
    void changingReturnedArrayDoesNotAffectSample() {
        DataSample sample = new DataSample("S-1", "cat", new double[] {1.0, 2.0});

        double[] returned = sample.getFeatures();
        returned[0] = 100.0;

        assertArrayEquals(new double[] {1.0, 2.0}, sample.getFeatures());
    }
}