package exercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TemperaturePlot extends JComponent {
    //list to store temperature readings
    private final List<TemperatureReading> readings;

    //constructor to initialize the component with a list of temperature readings
    public TemperaturePlot(List<TemperatureReading> readings) {
        this.readings = readings;
        setPreferredSize(new Dimension(800, 600)); // Set preferred size of the plot
    }

    //method to paint the component
    @Override
    protected void paintComponent(Graphics g) {
        //call the superclass method to ensure proper painting
        super.paintComponent(g);

        //cast Graphics object to Graphics2D for advanced rendering
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //define padding around the graph
        int padding = 50;
        int labelPadding = 30;

        //determine the width and height of the drawing area
        int width = getWidth() - (2 * padding);
        int height = getHeight() - (2 * padding) - labelPadding;

        //determine min and max temperatures for scaling
        double maxTemp = readings.stream().mapToDouble(r -> Math.max(r.getDowningTemp(), r.getDowningTemp())).max().getAsDouble();
        double minTemp = readings.stream().mapToDouble(r -> Math.min(r.getDowningTemp(), r.getEast15Temp())).min().getAsDouble();

        //draw the axes
        g2.drawLine(padding, getHeight() - padding - labelPadding, padding, padding); // Y-axis
        g2.drawLine(padding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding); // X-axis

        //set the stroke for the grid lines
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

        //draw grid lines and axis labels for days
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d");
        //assuming a reading every hour
        for (int i = 0; i < readings.size(); i += 24) {
            int x0 = i * width / (readings.size() - 1) + padding;
            int y0 = getHeight() - padding - labelPadding;
            String dayLabel = dateFormatter.format(readings.get(i).getTimeDate());
            g2.drawString(dayLabel, x0 - g2.getFontMetrics().stringWidth(dayLabel) / 2, y0 + labelPadding);
            g2.drawLine(x0, y0 - labelPadding, x0, padding);
        }

        //draw grid lines and axis labels for temperatures
        for (int i = (int) minTemp; i <= maxTemp; i++) {
            int y0 = (int) ((maxTemp - i) * height / (maxTemp - minTemp)) + padding;
            g2.drawString(String.format("%.1f°C", (double) i), padding - labelPadding, y0 + g2.getFontMetrics().getHeight() / 2 - 3);
            g2.drawLine(padding, y0, getWidth() - padding, y0);
        }

        //restore the stroke
        g2.setStroke(oldStroke);

        //draw the temperature lines for Downing Street and East 15 Acting School
        //true for Downing Street
        drawTemperatureLine(g2, width, height, padding, labelPadding, true);
        //false for East 15
        drawTemperatureLine(g2, width, height, padding, labelPadding, false);
    }

    //helper method to draw temperature lines
    private void drawTemperatureLine(Graphics2D g2, int width, int height, int padding, int labelPadding, boolean forDowningStreet) {
        double maxTemp = readings.stream().mapToDouble(r -> Math.max(r.getDowningTemp(), r.getEast15Temp())).max().getAsDouble();
        double minTemp = readings.stream().mapToDouble(r -> Math.min(r.getDowningTemp(), r.getEast15Temp())).min().getAsDouble();

        //set color based on location
        g2.setColor(forDowningStreet ? Color.RED : Color.BLUE);

        for (int i = 1; i < readings.size(); i++) {
            int x1 = (i - 1) * width / (readings.size() - 1) + padding;
            int y1 = (int) ((maxTemp - (forDowningStreet ? readings.get(i - 1).getDowningTemp() : readings.get(i - 1).getEast15Temp())) * height / (maxTemp - minTemp)) + padding;
            int x2 = i * width / (readings.size() - 1) + padding;
            int y2 = (int) ((maxTemp - (forDowningStreet ? readings.get(i).getDowningTemp() : readings.get(i).getEast15Temp())) * height / (maxTemp - minTemp)) + padding;
            //draw line between consecutive points
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
}
