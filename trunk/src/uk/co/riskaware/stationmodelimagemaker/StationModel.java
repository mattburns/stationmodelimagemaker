package uk.co.riskaware.stationmodelimagemaker;

public class StationModel {

    private final WindBarb windBarb;

    public StationModel(WindBarb windBarb) {
        this.windBarb = windBarb;
    }

    public WindBarb getWindBarb() {
        return windBarb;
    }
}
