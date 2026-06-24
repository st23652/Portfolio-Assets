package exercise3;

import java.io.*;
import java.util.*;

public class TemperatureData {
    private List<DataPoint> dataPoints;

    public TemperatureData(String filename) {
        dataPoints = new ArrayList<>();
        readTemperatures(filename);
    }

    private void readTemperatures(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // The first line contains the number of data points, which we don't necessarily need to use
            // since we're storing the points in a List that dynamically expands.
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double latitude = Double.parseDouble(parts[0]);
                double longitude = Double.parseDouble(parts[1]);
                double temp = Double.parseDouble(parts[2]);
                DataPoint dataPoint = new DataPoint(latitude, longitude, temp);
                dataPoints.add(dataPoint);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DataPoint> getDataPoints() {
        return new ArrayList<>(dataPoints);
    }
}
