import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//class to manage the Album database
public class AlbumDatabase {
    public static void main(String[] args) {
        //creating a scanner object to receive user input
        //System.in reads from the console
        Scanner scan = new Scanner(System.in);
        //initialize an ArrayList to store Album objects
        //ArrayList is a dynamic data structure that can grow or shrink in size as needed
        ArrayList<Album> alb = new ArrayList<>();

        //loop to continuously take user input until 'quit' is entered
        while (true) {
            //read a line of input from the user and store it in the 'input' variable
            String input = scan.nextLine();

            //this is case-insensitive
            //checking if the user has input 'quit' or not
            if (input.equalsIgnoreCase("quit")) {
                //this is used to exit the loop if the user inputs 'quit'
                break;
            }

            //splits the input into the band, title, and the rating of the album based on the comma and space separator (", ")
            //the split() method returns an array of strings containing the separated values
            String[] parts = input.split(", ");
            //check if the input has been correctly split into 3 parts
            if (parts.length != 3) {
                //notify user of input format error
                //this only takes place if the input does not contain exactly 3 parts
                System.out.println("Error : Please enter exactly three values (band, title, rating of album).");
                //continue to the next iteration of the loop to prompt the user for correct input
                continue;
            }

            //extract the band name from the 1st part of the input array
            String band = parts[0];
            //extract the title of the album from the 2nd part of the input array
            String title = parts[1];
            //declare a variable to store the rating of the album
            int ratingOfAlbum;

            try {
                //attempt to analyze the rating from the 3rd part of the input array as an integer
                ratingOfAlbum = Integer.parseInt(parts[2]);
            } catch (NumberFormatException x) {
                //if parsing fails, catch the NUmberFormatExeception
                //notify the user of the error in the rating input
                System.out.println("Error : Invalid rating. Please enter a valid integer.");
                //continue to the next iteration of the loop to prompt the user for correct input
                continue;
            }

            //creating an Album object and adding it to the list
            Album album = new Album(title, band, ratingOfAlbum);
            //add the Album object to the ArrayList
            alb.add(album);
        }

        //closes the scanner to release system resources
        scan.close();

        //printing the Album Database heading
        System.out.println("/nAlbum Database: ");
        //sort albums based on the compareTo() method and the Album class
        //the compareTo() method compares albums based on rating (descending), band name (ascending), and title (ascending)
        Collections.sort(alb);
        //Iterate through the sorted album list and print each album using the toString() method
        for (Album album : alb) {
            System.out.println(album);
        }
    }
}