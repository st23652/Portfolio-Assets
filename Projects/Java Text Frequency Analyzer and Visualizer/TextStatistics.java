import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            //the text statistics is read from the the file
            TextStatistics textStat = TextIO.readText("C://Users//5688s//OneDrive//Desktop//markedLab2/input.txt");
            System.out.println(textStat.toString());
            //over here the print is the text statistics to console

            JFrame f = new JFrame("bar graph");
            // creating a jframe to display the bar graph
            f.setSize(2000, 1500);
            //setting the frame size
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //setting the close operation
            f.add(new TextStatisticsDisplay(textStat));
            //adding TextStatisticsDisplay component
            f.setVisible(true);
            //setting the frame visibility
        } catch (FileNotFoundException e) {
            //handling file not found exception
            System.out.println(e.getMessage());
        }
    }
}
