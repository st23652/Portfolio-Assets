//this is a class representing an Album where the comparable implements is an interface for the sorting
public class Album implements Comparable<Album> {
    //stores the title of the album
    private String title;
    //stores the name of the band
    private String band;
    //stores the rating of the album
    private int ratingOfAlbum;

    //this is a constructor for creating an album object
    //takes in the title, band, and rating as parameters and initializes the corresponding instance variables
    public Album(String title, String band, int ratingOfAlbum) {
        this.title = title;
        this.band = band;
        this.ratingOfAlbum = ratingOfAlbum;
    }

    //this is just a getter method for retrieving the title of the Album
    //returns the value of the 'title' instance variable
    public String getTitle() {
        return title;
    }

    //this is to retrieve the band of the Album
    //getter method
    //returns the value of the 'band' instance variable
    public String getBand() {
        return band;
    }

    //this is to retrieve the rating of the Album
    //getter method
    //returns the value of the 'ratingOfAlbum' instance variable
    public int getRating() {
        return ratingOfAlbum;
    }

    //this compareTo method is for the sorting the Album object
    //overrides the compareTo method defined in the comparable interface
    //compares the current Album object with another Album object passed as a parameter
    //returns a negative integer, zero, or a positive integer as this Album is less than, equal to, or greater than the specified Album
    @Override
    public int compareTo(Album other) {
        //compare albums based on rating, then band, then title in descending order
        //Integer.compare(x, y) returns a negative integer, zero, or a positive integer as x is less than, equal to, or greater than y
        int compareRating = Integer.compare(other.ratingOfAlbum, this.ratingOfAlbum);
        //if the ratings are not equal, return the result of comparing the ratings
        if (compareRating != 0) {
            return compareRating;
        }

        //if the ratings are equal, compare albums based on band name  in ascending order
        //String.compareTo(str) returns a (-) int, 0, or a (+) int as this String is less than, equal to, or greater than the specified String (str)
        int compareBand = this.band.compareTo(other.band);
        //if the band names are not equal, return the result of comparing the band names
        if (compareBand != 0) {
            return compareBand;
        }

        //toString method generates a string representation of the Album object
        //Overrides the toString method defined in the object class
        //returns a string containing the album details in the format: "Album{title='title', band='band', rating=rating}"
        return this.title.compareTo(other.title);
    }

    //toString method makes sure that the Album details are printed
    //this method is for generating a string representation of the Album object
    //overrides the toString method defined in the object class
    @Override
    public String toString() {
        return "Album{title='" + title + "', band='" + band + "', rating=" + ratingOfAlbum + "}";
    }
}