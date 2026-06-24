# Java Text Frequency Analyzer and Visualizer 📊

## Overview
This project is a Java application designed to read text data from a file, parse the content to count character frequencies, and visualize the results[cite: 4, 5]. It calculates the occurrences of all 26 alphabetic letters and 10 numerical digits[cite: 2]. The program outputs a formatted text summary to the console and generates a graphical bar chart using Java Swing[cite: 3, 4].

## Concepts Demonstrated
* **File Handling and I/O:** Reading files line by line using `Scanner` and `FileReader` while handling `FileNotFoundException` errors[cite: 4, 5].
* **Data Parsing:** Processing strings by converting them to lowercase and evaluating individual characters using `Character.isLetter()` and `Character.isDigit()`[cite: 5].
* **GUI Development:** Extending `JComponent` to create custom graphics and rendering shapes and text using the `Graphics` class[cite: 3].
* **Data Visualization:** Mapping integer frequencies to proportional pixel heights to render a custom bar graph[cite: 3].

## Project Structure
* `TextStatistics.java`: Manages the data state by storing frequencies of each letter and number digit in private integer arrays[cite: 2]. It includes methods to increment frequencies and provides a custom `toString()` method using a `StringBuilder` to format the output[cite: 2].
* `TextIO.java`: Handles the file reading operations[cite: 5]. It scans the designated text file, iterates through each character, and updates the `TextStatistics` object accordingly[cite: 5].
* `TextStatisticsDisplay.java`: A graphical component that paints the bar graph interface[cite: 3]. It renders blue bars for letter frequencies and red bars for digit frequencies, ensuring they are shifted along the x-axis to prevent overlapping[cite: 3].
* `Main.java`: The main execution script that ties the components together[cite: 4]. It prints the parsed statistics to the console and initializes a `JFrame` window to display the graphical output[cite: 4].