package exercise1;

import java.util.Random;

//class Declaration where House is a subclass of Building
public class House extends Building {
    //constructor generates random height (between 2 and 3) and sets width to 2
    public House() {
        super(new Random().nextInt(2) + 2, 2);
    }

    //override getLevel() method to define the appearance of each level of the house
    @Override
    public String getLevel(int level) {
        //check if the current level is the top level (roof)
        if (level == getHeight() - 1) {
            //return the representation of the roof
            return "/\\";
        }
        //for all other levels (body)
        else {
            //return the representation of the body
            return "||";
        }
    }
}
