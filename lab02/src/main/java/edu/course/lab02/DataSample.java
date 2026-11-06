package edu.course.lab02;

public final class DataSample {

    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be null or blank");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label must not be null or blank");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("features must not be null or empty");
        }
        for (double feature : features) {
            if (Double.isNaN(feature) || Double.isInfinite(feature)) {
                throw new IllegalArgumentException("features must be finite numbers");
            }
        }
        this.id = id;
        this.label = label;
        this.features = features.clone();
        this.status = SampleStatus.NEW;
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeature() {
        double sum = 0;
        for (double feature : features) {
            sum += feature;
        }
        return sum / features.length;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }
}