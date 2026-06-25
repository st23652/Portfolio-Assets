# Autonomous Mobile Robot Navigation System

> **A comprehensive C++ based robotic navigation pipeline developed within the Robot Operating System (ROS) and Gazebo simulation environment, featuring custom PID control and optimized LiDAR mapping.**

## 📖 Overview
This project focuses on the programmatic development of an autonomous mobile robot capable of navigating from an initial location, traversing a series of designated waypoints, and reaching a terminal destination. Built utilizing ROS and Gazebo, the system demonstrates a pedagogical and technical progression from basic dead-reckoning odometry to an advanced, closed-loop Proportional-Integral-Derivative (PID) control system integrated with optimized environmental mapping.

## ✨ Key Technical Highlights
* **Closed-Loop PID Control:** Engineered a custom PID controller to replace basic "Bang-Bang" motion logic. By fine-tuning proportional and derivative gains ($K_p=1.0, K_d=0.1$), the system achieves continuous, smooth-arc navigation and real-time heading corrections without requiring full stops.
* **LiDAR Data Processing & Mapping:** Processed raw polar measurements from a 2D LiDAR scanner into global Cartesian coordinates to generate environmental maps.
* **Algorithmic Optimization & Memory Management:** Addressed critical system instability and memory overflow issues (caused by high-frequency LiDAR data) by implementing a custom Grid-Based Filtering algorithm. By discretizing coordinates to a 0.05m resolution and utilizing `std::set` to discard duplicate data points, the system drastically reduced I/O latency and memory footprint.
* **Finite State Machine (FSM) Architecture:** Designed a robust FSM operating at a 10Hz sampling frequency to manage distinct navigational stages and waypoint transitions based on real-time coordinate and yaw thresholds.

## 🧠 System Evolution & Architecture

The project was developed in progressive iterative stages, each enhancing the robot's autonomy and stability:

* **Stage 1: Odometry Control (Open-Loop)**
  Initially implemented a dead-reckoning strategy relying exclusively on wheel encoders. The robot utilized a rigid "Turn-then-Move" logic, decoupling rotational and translational movements to follow a prescribed rectilinear path.
* **Stage 2: Sensor Integration**
  Integrated laser scan callbacks to actively read and transform obstacle data. Early iterations exposed the limitations of processing unfiltered point clouds, leading to system crashes under data volume overload.
* **Stage 3: Trajectory & Control Refinement**
  Transitioned to a continuous motion model using PID control, allowing the robot to maintain linear velocity while simultaneously adjusting angular velocity for smooth cornering and target approach.
* **Stage 4: Optimized Core Implementation**
  The final `ce315_core_node` integrates the refined PID logic with the Grid-Based spatial filter. This combination ensures stable, long-term operation, clean map generation, and highly optimized, continuous trajectory execution.

## 🛠️ Technical Stack
* **Language:** C++
* **Framework:** ROS (Robot Operating System)
* **Simulation:** Gazebo
* **Key Libraries:** `rclcpp`, `nav_msgs`, `sensor_msgs`, `geometry_msgs`
* **Core Concepts:** Kinematics, PID Tuning, Spatial Filtering, Sensor Transformation