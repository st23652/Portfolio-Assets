package exercise1;

import java.util.Random;

// Class Declaration: Skyscraper is a subclass of Building
public class Skyscraper extends Building {
    // Constructor: Generates random height (between 10 and 19) and sets width to 3
    public Skyscraper() {
        super(new Random().nextInt(10) + 10, 3);
    }

    // Override getLevel() method to define the appearance of each level of the skyscraper
    @Override
    public String getLevel(int level) {
        // Check if the current level is the top level (roof)
        if (level == getHeight() - 1) {
            return "_|_"; // Return the representation of the roof
        }
        // Check if the current level is an even level (body)
        else if (level % 2 == 0) {
            return "| |"; // Return the representation of an alternating body
        }
        // For all other levels (odd levels)
        else {
            return "|#|"; // Return the representation of an alternating body
        }
    }
}
