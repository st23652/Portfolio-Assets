package exercise1;

import java.util.Random;

//class Declaration: Factory is a subclass of Building
public class Factory extends Building {
    //constructor generates random height (between 4 and 5) and sets width to 5
    public Factory() {
        super(new Random().nextInt(2) + 4, 5);
    }

    //override getLevel() method to define the appearance of each level of the factory
    @Override
    public String getLevel(int level) {
        //check if the current level is the top level (roof)
        if (level == getHeight() - 1) {
            //return the representation of the roof
            return "/   \\";
        }
        //check if the current level is the base level
        else if (level == 0) {
            //return the representation of the base
            return "|/ \\|";
        }
        //for all other levels (body)
        else {
            //return the representation of the body
            return "|   |";
        }
    }
}
