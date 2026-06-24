package exercise4;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JulyTemperatures {
    //main method to run the application
    public static void main(String[] args) {
        //run the GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(JulyTemperatures::createAndShowGUI);
    }

    //method to create and show the main application window
    private static void createAndShowGUI() {
        //create a JFrame with title July Temperatures
        JFrame frame = new JFrame("July Temperatures");
        //exit the application when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            //path to the CSV file containing temperature data for July
            String filePath = "../temperatures-July.csv";
            //read temperature data from the file
            List<TemperatureReading> readings = TemperatureReading.readFromFile(filePath);
            //create a TemperaturePlot object with the readings
            TemperaturePlot plot = new TemperaturePlot(readings);
            //add the plot to a JScrollPane to enable scrolling
            frame.add(new JScrollPane(plot));
            //resize the frame to fit its components
            frame.pack();
            //center the frame on the screen
            frame.setLocationRelativeTo(null);
            //make the frame visible
            frame.setVisible(true);
        } catch (IOException e) {
            //print the stack trace of the exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    //display an error message dialog
                    "Error loading temperature data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            //exit the application with an error code
            System.exit(1);
        }
    }
}
