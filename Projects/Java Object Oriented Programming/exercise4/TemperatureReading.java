package exercise4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemperatureReading {
    //fields to store the specific moment of the temperature reading and temperatures at two different locations
    //timeDate holds the exact date and time of each temperature reading
    //downingTemp and east15Temp store temperature readings from two different sites (Downing and East 15)
    private final LocalDateTime timeDate;
    private final double downingTemp;
    private final double east15Temp;

    //constructor to initialize TemperatureReading objects with a timestamp and two temperatures
    //this enforces immutability as there are no setter methods, ensuring that once a reading is created
    //it cannot be altered which is critical for data integrity
    public TemperatureReading(LocalDateTime timeDate, double downingTemp, double east15Temp) {
        this.timeDate = timeDate;
        this.downingTemp = downingTemp;
        this.east15Temp = east15Temp;
    }

    //returns the time and date of the reading
    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    //returns the Downing location's temperature
    public double getDowningTemp() {
        return downingTemp;
    }

    //returns the East 15 location's temperature
    public double getEast15Temp() {
        return east15Temp;
    }

    //determines the minimum temperature recorded from a list of readings
    public static double getMinTemperature(List<TemperatureReading> readings) {
        return readings.stream()
                .mapToDouble(r -> Math.min(r.getDowningTemp(), r.getEast15Temp()))
                .min()
                .orElseThrow(() -> new IllegalArgumentException("List of readings is empty"));
    }

    //determines the maximum temperature recorded from a list of readings
    public static double getMaxTemperature(List<TemperatureReading> readings) {
        return readings.stream()
                .mapToDouble(r -> Math.max(r.getDowningTemp(), r.getEast15Temp()))
                .max()
                .orElseThrow(() -> new IllegalArgumentException("List of readings is empty"));
    }

    //reads temperature data from a CSV file and constructs a list of TemperatureReading objects
    public static List<TemperatureReading> readFromFile(String filename) throws IOException {
        List<TemperatureReading> readings = new ArrayList<>();
        Path path = Path.of(filename);
        List<String> lines = Files.readAllLines(path);

        //initialize the date and time from July 1 2017
        LocalDateTime timeDown = LocalDateTime.of(2017, 7, 1, 0, 0);

        for (String line : lines) {
            String[] temps = line.split(",");
            if (temps.length != 2) {
                throw new IOException("Invalid line format: " + line);
            }
            //convert Kelvin to Celsius for Downing temperature
            double downingTemp = Double.parseDouble(temps[0]) - 273.15;
            //convert Kelvin to Celsius for East 15 temperature
            double east15Temp = Double.parseDouble(temps[1]) - 273.15;
            readings.add(new TemperatureReading(timeDown, downingTemp, east15Temp));
            //increment the timeDate by one hour for each reading
            timeDown = timeDown.plusHours(1);
        }

        return readings;
    }
}
