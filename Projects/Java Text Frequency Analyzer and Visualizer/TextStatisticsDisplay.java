import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TextIO {
    public static TextStatistics readText(String filename) throws FileNotFoundException {
        //method to read the text from the file
        //this also generates the object from textstatistics and to store it as well
        TextStatistics textStat = new TextStatistics();

        try (Scanner scan = new Scanner(new FileReader(filename))) {
            //this makes sure the file is read line by line
            //it is also converted to a lower case for case sensitive analysis
            while (scan.hasNextLine()) {
                String sentence = scan.nextLine().toLowerCase();
                //iterating through each character in the line
                for (int a = 0; a < sentence.length(); a++) {
                    char c = sentence.charAt(a);
                    //checking if the character given is a letter or number
                    if (Character.isLetter(c)) {
                        //an incrementation of the frequency of the corresponding letter
                        textStat.incrementLetterFrequency(c - 'a');
                    } else if (Character.isDigit(c)) {
                        //this is a check if the character is a digit
                        //an incrementation of the frequency of the corresponding digit
                        textStat.incrementDigitFrequency(c - '0');
                    }
                }
            }
        }
        //returning the textstatistics object which contains the text statistics
        return textStat;
    }
}
