package uk.co.riskaware.stationmodelimagemaker;

public class WindBarb {

    private final double direction;
    private final double knots;

    public WindBarb(double direction, double knots) {
        super();
        this.direction = direction;
        this.knots = knots;
    }

    public double getDirection() {
        return direction;
    }

    public double getDirectionInRadians() {
        return Math.toRadians(direction);
    }

    public double getKnots() {
        return knots;
    }

}
