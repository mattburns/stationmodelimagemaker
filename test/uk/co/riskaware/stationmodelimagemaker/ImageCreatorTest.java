package uk.co.riskaware.stationmodelimagemaker;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ImageCreatorTest {

    @Test
    public void testImageCreation() throws IOException {
        File tempFile = new File("temp.png");
        WindBarb windBarb = new WindBarb(170, 65);
        StationModel stationModel = new StationModel(windBarb);

        ImageCreator ic = new ImageCreator();
        ic.writePNG(tempFile, stationModel);
    }

}
