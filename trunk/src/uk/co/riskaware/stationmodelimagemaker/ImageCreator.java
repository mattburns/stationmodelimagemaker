package uk.co.riskaware.stationmodelimagemaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCreator {

    private final int shaftLength;
    private final int tickLength;
    private final int tickSpacing;
    private final int width;
    private final int height;
    private final int xCenter;
    private final int yCenter;
    private final Color barbColor;

    public ImageCreator() {
        this(100, new Color(20, 70, 170, 200));
    }

    public ImageCreator(int shaftLength, Color barbColor) {
        this.shaftLength = shaftLength;

        tickLength = shaftLength / 4;
        tickSpacing = tickLength / 2;
        width = (shaftLength + tickLength) * 2;
        height = width;
        xCenter = width / 2;
        yCenter = height / 2;
        this.barbColor = barbColor;
    }

    public void writePNG(File output, StationModel stationModel)
            throws IOException {
        BufferedImage bi2 = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2D = createGraphicWithStyle(bi2);

        drawBarb(stationModel, g2D);
        drawCenter(g2D);

        ImageIO.write(bi2, "PNG", output);
    }

    private Graphics2D createGraphicWithStyle(BufferedImage bi2) {
        Graphics2D g2D = (Graphics2D) bi2.getGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setPaint(barbColor);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        return g2D;
    }

    private void drawCenter(Graphics2D g2D) {
        int circleRadius = 3;
        int circleDiameter = circleRadius * 2;
        g2D.fill(new Ellipse2D.Double(xCenter - circleRadius, yCenter
                - circleRadius, circleDiameter, circleDiameter));
    }

    private void drawBarb(StationModel stationModel, Graphics2D g2D) {
        double knots = stationModel.getWindBarb().getKnots();
        int endMarker = xCenter - shaftLength;

        GeneralPath barbLine = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        barbLine.moveTo(xCenter, yCenter);
        barbLine.lineTo(xCenter, endMarker);

        boolean atEnd = true;
        while (knots >= 5) {

            if (knots >= 50) {
                barbLine.lineTo(xCenter + 1 + tickLength, endMarker);
                barbLine.lineTo(xCenter + 1, endMarker + tickSpacing);
                knots -= 50;
            } else if (knots >= 10) {
                if (atEnd) {
                    endMarker -= tickSpacing;
                }
                barbLine.moveTo(xCenter + 1, endMarker + tickSpacing);
                barbLine.lineTo(xCenter + 1 + tickLength, endMarker);
                knots -= 10;
            } else {
                barbLine.moveTo(xCenter + 1, endMarker + tickSpacing);
                barbLine.lineTo(xCenter + 1 + (tickLength / 2), endMarker
                        + (tickSpacing / 2));
                knots -= 5;
            }
            atEnd = false;
            barbLine.moveTo(xCenter + 1, endMarker + tickSpacing);
            endMarker += tickSpacing;
        }
        g2D.rotate(stationModel.getWindBarb().getDirectionInRadians(), xCenter,
                yCenter);
        g2D.draw(barbLine);
    }
}
