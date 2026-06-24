package exercise1;

//declaring an abstract class named Building
public abstract class Building {
    //declaring private instance variables height and width to store building dimensions
    private int height;
    private int width;

    //constructor for Building class with parameters height and width
    public Building(int height, int width) {
        //initializing instance variables with provided values
        this.height = height;
        this.width = width;
    }

    //getter method to retrieve the height of the building
    public int getHeight() {
        return height;
    }

    //getter method to retrieve the width of the building
    public int getWidth() {
        return width;
    }

    //abstract method declaration to be implemented by subclasses to get the level of the building
    public abstract String getLevel(int level);
    //this method should return a string representing the content of the specified level of the building

    //override toString() method to represent the Building object as a string
    @Override
    public String toString() {
        //creating a StringBuilder object to build the string representation of the building
        StringBuilder sb = new StringBuilder();
        //iterating over each level of the building from top to bottom
        for (int level = height - 1; level >= 0; level--) {
            //appending the content of each level to the StringBuilder object
            sb.append(getLevel(level)).append("\n");
        }
        //returning the final string representation of the building
        return sb.toString();
    }
}
