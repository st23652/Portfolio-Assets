# Java GUI Rectangle Manager 🟥🟦

## Overview
This project is a Java desktop application designed to visually manage a collection of rectangle objects. It provides a complete suite of CRUD (Create, Read, Update, Delete) operations through a user-friendly, multi-panel graphical interface. The application serves as a practical demonstration of event-driven programming, custom Swing rendering, and strict data validation protocols.

## Key Features
* **Shape Manipulation:** Users can add, search, update, and delete rectangles using an interactive control panel and dialog pop-ups.
* **Dynamic Canvas:** Rectangles are instantly rendered on a central canvas pane with customizable X/Y positions, dimensions, and colors selected from a dropdown menu.
* **System Console Panel:** A dedicated text area on the right side of the window provides real-time user feedback, displaying success logs, search details, and sorted database listings.
* **Strict Input Validation:** The system enforces complex logical constraints before rendering a shape. For example, it requires the rectangle's width to be even if the last digit of its ID is odd, and the height to be odd if the ID ends in an even number. 

## Project Architecture
The application is structured logically to separate the GUI framework from the data models[cite: 18]:
* `MainFrame`: The primary view class that constructs the window layout, including the input fields, action buttons, and the display canvas. It utilizes multiple action listeners to handle user events and trigger input dialogs.
* `RectangleManager`: The core controller responsible for storing the data state, sorting the objects by ID, and managing retrieval or deletion requests.
* `RectangleShape`: The foundational data model encapsulating the properties of the visual object, such as its unique identifier, dimensional footprint (width/height), and color payload.

## Development Details
* **Language:** Java 17
* **UI Framework:** Java Swing / AWT
* **IDE:** IntelliJ IDEA