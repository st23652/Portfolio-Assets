# ROS2 Autonomous Navigation & Mapping Robot 🤖🗺️

## Overview
This project showcases autonomous robotic control and mapping using the Robot Operating System (ROS2). It is divided into two primary operational modes: a precision trajectory tracker and an autonomous environment explorer. By leveraging Odometry data, LiDAR sensor inputs, and proportional-integral-derivative (PID) control logic, the system effectively maps unknown environments and executes exact geometric movements.

## Key Features
* **Precision Square Tracing:** Uses position-based PID control to navigate a strict square trajectory (1-meter sides). The system continuously reads `/odom` feedback to adjust linear and angular velocities, correcting heading deviations and enforcing strict position tolerances.
* **Autonomous SLAM Exploration:** Implements a random-walk algorithm that allows the robot to autonomously navigate and map an unknown space. It actively avoids collisions by processing real-time `/scan` (LaserScan) data.
* **Real-Time Data Visualization:** Integrates seamlessly with RViz to visualize sensor data, transformation frames (TF), and the real-time generation of the occupancy grid map.
* **Automated Data Logging:** Tracks the robot's coordinates during mapping sessions, exporting the X, Y, and Yaw telemetry data directly into a `.csv` file for post-navigation analysis and trajectory overlay.

## System Architecture (ROS2 Nodes)
The project consists of two core Python nodes:
* `SquareTracer` (`myrobot.py`): The trajectory node. It subscribes to odometry to track position, computes distance errors, and publishes targeted velocity commands to the `/cmd_vel` topic to trace perfect squares. 
* `RandomExplorer` (`mapping_robot.py`): The SLAM helper node. It subscribes to both odometry and LiDAR. It defaults to moving forward but forces a random turn when its collision-detection thresholds are breached, ensuring comprehensive coverage of a room for the mapping engine.

## Technologies Used
* **Framework:** ROS2 (Robot Operating System)
* **Language:** Python (`rclpy`)
* **Sensors/Data:** LiDAR (LaserScan), Odometry
* **Visualization Tool:** RViz
* **Control Systems:** PID Control, Obstacle Avoidance algorithms