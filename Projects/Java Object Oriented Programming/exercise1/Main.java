package exercise1;

//class declaration: main class containing the main method to execute the program
public class Main {
    //main method: Entry point of the program
    public static void main(String[] args) {
        //create instances of different types of buildings: House, Factory, and Skyscraper
        //create a new House object
        House house = new House();
        //create a new Factory object
        Factory factory = new Factory();
        //create a new Skyscraper object
        Skyscraper skyscraper = new Skyscraper();

        //print each building individually with labels
        System.out.println("House:" + "\n" + house); // Print the House object
        System.out.println("Factory:" + "\n" + factory); // Print the Factory object
        System.out.println("Skyscraper:" + "\n" + skyscraper); // Print the Skyscraper object

        // Create a Skyline object with a specified number of buildings
        Skyline skyline = new Skyline(10); // Create a new Skyline object with 10 buildings

        // Print the skyline
        System.out.println("\n\nSkyline:"); // Print a label for the skyline
        System.out.println(skyline); // Print the Skyline object
    }
}
