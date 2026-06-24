//declaration of the package to which the class belongs
package exercise2;

//declaration of the City class
public class City {
    //private fields to encapsulate the properties of a city
    //the name of the city
    private String name;
    //the country in which the city is located
    private String country;
    //the population of the city
    private int population;
    //boolean flag indicating whether the city is the capital of its country
    private boolean capital;

    //constructor for the City class
    //it initializes the city object with the provided values
    public City(String name, String country, int population, boolean capital) {
        //assigns the name of the city
        this.name = name;
        //assigns the country of the city
        this.country = country;
        //assigns the population of the city
        this.population = population;
        //assigns the boolean flag indicating if the city is a capital
        this.capital = capital;
    }

    //getter method for the name field
    public String getName() {
        return name;
    }

    //getter method for the country field
    public String getCountry() {
        return country;
    }

    //getter method for the population field
    public int getPopulation() {
        return population;
    }

    //getter method for the capital field, with a name that indicates it will return a boolean
    public boolean isCapital() {
        return capital;
    }

    //overridden toString method to provide a string representation of the city object
    @Override
    public String toString() {
        //checks if the city is a capital and returns a corresponding string representation
        if (isCapital()) {
            //if the city is a capital, format the string to include that information
            return name + " is the capital of " + country + " and has a population of " + population;
        } else {
            //if the city is not a capital, format the string to reflect its non-capital status
            return name + " is in " + country + " and has a population of " + population;
        }
    }
}
