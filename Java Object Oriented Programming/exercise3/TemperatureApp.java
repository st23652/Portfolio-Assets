package exercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureApp {
    //main method to run the application
    public static void main(String[] args) {
        //invoke GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(TemperatureApp::createAndShowGUI);
    }

    //method to create and show the GUI
    private static void createAndShowGUI() {
        //create TemperatureData object to read temperature data from file
        TemperatureData data = new TemperatureData("../heatmap.csv");

        //create TemperatureDisplay object to display temperature data
        //default representation: Use color
        TemperatureDisplay display = new TemperatureDisplay(data, true);

        //create JFrame to hold the display and controls
        JFrame frame = new JFrame("Temperature Heatmap");
        //close application when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //use border layout for JFrame
        frame.setLayout(new BorderLayout());

        //create JPanel to hold control components
        JPanel controls = new JPanel();

        //create checkbox to toggle color representation
        JCheckBox colorToggle = new JCheckBox("Use Color", true);
        colorToggle.addActionListener(e -> display.setUseColour(colorToggle.isSelected()));

        //create buttons for zooming in and out
        JButton zoomIn = new JButton("+");
        //increase zoom by 10%
        zoomIn.addActionListener(e -> display.setZoomFactor(display.getZoomFactor() * 1.1));

        JButton zoomOut = new JButton("-");
        //decrease zoom by 10%
        zoomOut.addActionListener(e -> display.setZoomFactor(display.getZoomFactor() * 0.9));

        //add components to the controls panel
        controls.add(colorToggle);
        controls.add(zoomIn);
        controls.add(zoomOut);

        //add display and controls panels to the JFrame
        //display at the center of the frame
        frame.add(display, BorderLayout.CENTER);
        //controls at the bottom of the frame
        frame.add(controls, BorderLayout.SOUTH);

        //pack the frame to set its preferred size and layout
        frame.pack();
        //center the frame on the screen
        frame.setLocationRelativeTo(null);
        //make the frame visible
        frame.setVisible(true);
    }
}
