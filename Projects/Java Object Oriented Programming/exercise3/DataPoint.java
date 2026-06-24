package exercise3;

//class representing a data point containing latitude, longitude, and temperature
public class DataPoint {
    //latitude coordinate of the data point
    private double latitude;
    //longitude coordinate of the data point
    private double longitude;
    //temperature value of the data point
    private double temp;

    //constructor to initialize a DataPoint object with latitude, longitude, and temperature
    public DataPoint(double latitude, double longitude, double temp) {
        //set latitude
        this.latitude = latitude;
        //set longitude
        this.longitude = longitude;
        //set temperature
        this.temp = temp;
    }

    //getter method to retrieve the latitude of the data point
    public double getLatitude() {
        //return the latitude
        return latitude;
    }

    //getter method to retrieve the longitude of the data point
    public double getLongitude() {
        //return the longitude
        return longitude;
    }

    //getter method to retrieve the temperature of the data point
    public double getTemp() {
        //return the temperature
        return temp;
    }
}
