## Update ##
This project has been dropped in favour of a GWT based approach:
http://code.google.com/p/gwtstationmodel/

## Introduction ##
Java library to programmatically creating image files representing station models.

http://en.wikipedia.org/wiki/Station_model


Currently only supports simple wind barbs.

![http://stationmodelimagemaker.googlecode.com/files/barb_330_65.png](http://stationmodelimagemaker.googlecode.com/files/barb_330_65.png)

Example code that created the above image:
```
File tempFile = new File("temp.png");
WindBarb windBarb = new WindBarb(330, 65);
StationModel stationModel = new StationModel(windBarb);

ImageCreator ic = new ImageCreator();
ic.writePNG(tempFile, stationModel);
```

