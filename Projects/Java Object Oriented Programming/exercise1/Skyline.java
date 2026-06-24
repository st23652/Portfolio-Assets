package exercise1;

import java.util.Arrays;
import java.util.Random;

// Class Declaration: Skyline class represents a collection of buildings arranged in a skyline
public class Skyline {
    // Array to store the buildings in the skyline
    private Building[] building;

    // Constructor: Creates a skyline with a specified number of buildings
    public Skyline(int numberOfBuildings) {
        building = new Building[numberOfBuildings]; // Initialize the array to store buildings
        Random rand = new Random(); // Random object to generate random building types
        // Loop to create buildings
        for (int x = 0; x < numberOfBuildings; x++) {
            int typeOfBuilding = rand.nextInt(3); // Generate a random number to determine the type of building
            switch (typeOfBuilding) {
                case 0:
                    building[x] = new House(); // Create a House object and add it to the skyline
                    break;
                case 1:
                    building[x] = new Factory(); // Create a Factory object and add it to the skyline
                    break;
                case 2:
                    building[x] = new Skyscraper(); // Create a Skyscraper object and add it to the skyline
                    break;
            }
        }
    }

    // Override toString() method to represent the Skyline object as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // StringBuilder to construct the string representation of the skyline
        int maxHeight = Arrays.stream(building).mapToInt(Building::getHeight).max().orElse(0);
        // Find the maximum height among all buildings in the skyline

        // Loop through each level of the skyline (from top to bottom)
        for (int level = maxHeight - 1; level >= 0; level--) {
            // Loop through each building in the skyline
            for (Building building : building) {
                int height = building.getHeight(); // Get the height of the current building
                int width = building.getWidth(); // Get the width of the current building
                // Check if the current level is within the height of the building
                if (level < height) {
                    sb.append(building.getLevel(level)); // Append the level of the building at the current level
                } else {
                    sb.append(" ".repeat(width + 2)); // Append white space equal to the width + 2 (to maintain spacing)
                }
            }
            sb.append("\n"); // Move to the next row after completing each level of the skyline
        }

        return sb.toString(); // Return the string representation of the skyline
    }
}
