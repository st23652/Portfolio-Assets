package exercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TemperatureDisplay extends JComponent {
    //list to store temperature data points
    private List<DataPoint> dataPoints;

    //boolean flag to determine color or grayscale representation
    private boolean useColour;

    //min and max latitude, longitude, and temperature values
    private double minLat, maxLat, minLong, maxLong, minTemp, maxTemp;

    //zoom factor for scaling
    private double zoomFactor = 1.0;

    //constructor to initialize the TemperatureDisplay
    public TemperatureDisplay(TemperatureData data, boolean useColour) {
        this.dataPoints = data.getDataPoints();
        this.useColour = useColour;

        //calculate min and max values for latitude, longitude, and temperature
        minLat = dataPoints.stream().mapToDouble(DataPoint::getLatitude).min().orElse(Double.MAX_VALUE);
        maxLat = dataPoints.stream().mapToDouble(DataPoint::getLatitude).max().orElse(Double.MIN_VALUE);
        minLong = dataPoints.stream().mapToDouble(DataPoint::getLongitude).min().orElse(Double.MAX_VALUE);
        maxLong = dataPoints.stream().mapToDouble(DataPoint::getLongitude).max().orElse(Double.MIN_VALUE);
        minTemp = dataPoints.stream().mapToDouble(DataPoint::getTemp).min().orElse(Double.MIN_VALUE);
        maxTemp = dataPoints.stream().mapToDouble(DataPoint::getTemp).max().orElse(Double.MAX_VALUE);

        //set preferred size and add mouse listener
        this.setPreferredSize(new Dimension(600, 600));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    //paint component to draw temperature display
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //apply zoom scaling
        g2d.scale(zoomFactor, zoomFactor);

        //calculate scale based on component size and latitude/longitude range
        double scaleX = getWidth() / (maxLong - minLong);
        double scaleY = getHeight() / (maxLat - minLat);

        //draw each data point
        for (DataPoint point : dataPoints) {
            int x = (int) ((point.getLongitude() - minLong) * scaleX);
            int y = (int) ((maxLat - point.getLatitude()) * scaleY);
            Color color = useColour ? temperatureToColor(point.getTemp()) : temperatureToGrayscale(point.getTemp());
            g.setColor(color);
            //draw small rectangle for each data point
            g.fillRect(x, y, 2, 2);
        }
    }

    //method to convert temperature to color based on a gradient
    private Color temperatureToColor(double temperature) {
        float normalizedTemp = (float) ((temperature - minTemp) / (maxTemp - minTemp));

        float blueThreshold = 0.33f;
        float greenThreshold = 0.66f;

        if (normalizedTemp <= blueThreshold) {
            float ratio = normalizedTemp / blueThreshold;
            return new Color(0, (int)(ratio * 255), 255);
        } else if (normalizedTemp <= greenThreshold) {
            float ratio = (normalizedTemp - blueThreshold) / (greenThreshold - blueThreshold);
            return new Color((int)(ratio * 255), 255, 0);
        } else {
            float ratio = (normalizedTemp - greenThreshold) / (1 - greenThreshold);
            return new Color(255, (int)((1 - ratio) * 255), 0);
        }
    }

    //method to convert temperature to grayscale
    private Color temperatureToGrayscale(double temperature) {
        if (maxTemp == minTemp) {
            //middle grey
            return new Color(128, 128, 128);
        }

        float normalizedTemp = (float) ((temperature - minTemp) / (maxTemp - minTemp));
        int grayscaleValue = (int)(normalizedTemp * 255);
        grayscaleValue = Math.min(255, Math.max(0, grayscaleValue));
        return new Color(grayscaleValue, grayscaleValue, grayscaleValue);
    }

    //method to handle mouse click event
    private void handleMouseClick(MouseEvent e) {
        double latitude = pixelToLatitude(e.getY());
        double longitude = pixelToLongitude(e.getX());
        double temperature = getTemperatureAt(latitude, longitude);

        JOptionPane.showMessageDialog(this, "Temperature: " + temperature + "K\nCoordinates: " + latitude + ", " + longitude, "DataPoint Info", JOptionPane.INFORMATION_MESSAGE);
    }

    //method to convert pixel Y coordinate to latitude
    private double pixelToLatitude(int pixelY) {
        double relativeY = (double) pixelY / (getHeight() * zoomFactor);
        return maxLat - (relativeY * (maxLat - minLat));
    }

    //method to convert pixel X coordinate to longitude
    private double pixelToLongitude(int pixelX) {
        double relativeX = (double) pixelX / (getWidth() * zoomFactor);
        return minLong + (relativeX * (maxLong - minLong));
    }

    //method to get temperature at specific latitude and longitude
    private double getTemperatureAt(double latitude, double longitude) {
        DataPoint closest = findClosestDataPoint(latitude, longitude);
        return closest != null ? closest.getTemp() : Double.NaN;
    }

    //method to find the closest data point to a given latitude and longitude
    private DataPoint findClosestDataPoint(double latitude, double longitude) {
        DataPoint closest = null;
        double minDistance = Double.MAX_VALUE;

        for (DataPoint dp : dataPoints) {
            double distance = calculateDistance(latitude, longitude, dp.getLatitude(), dp.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                closest = dp;
            }
        }
        return closest;
    }

    //method to calculate distance between two points
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    //setter for using color or grayscale representation
    public void setUseColour(boolean useColour) {
        this.useColour = useColour;
        repaint();
    }

    //setter for zoom factor
    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        revalidate();
        repaint();
    }

    //getter for zoom factor
    public double getZoomFactor() {
        return zoomFactor;
    }

    //method to zoom in
    public void zoomIn() {
        zoomFactor *= 1.1; // Increase zoom by 10%
        revalidate();
        repaint();
    }

    //method to zoom out
    public void zoomOut() {
        //decrease zoom by 10%
        zoomFactor *= 0.9;
        revalidate();
        repaint();
    }
}
