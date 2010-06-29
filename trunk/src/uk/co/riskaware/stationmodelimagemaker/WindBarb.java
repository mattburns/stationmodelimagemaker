package uk.co.riskaware.stationmodelimagemaker;

public class WindBarb {

    private final int direction;
    private final int knots;

    public WindBarb(int direction, int knots) {
        super();
        this.direction = direction;
        this.knots = knots;
    }

    public int getDirection() {
        return direction;
    }

    public double getDirectionInRadians() {
        return Math.toRadians(direction);
    }

    public int getKnots() {
        return knots;
    }

}
