# ROS2 Reactive Safety Controller
A formal Finite State Machine (FSM) implementation for robotic collision avoidance and state management.

## Features
- **Formal FSM Design:** Uses a defined state-transition function (δ) to manage robot behavior, ensuring predictable responses to sensor input.
- **Dynamic Safety Buffer:** Processes LiDAR scan data to trigger state transitions based on proximity to obstacles.
- **Hardware-Aware Logic:** Implements velocity limiting and emergency stop protocols to ensure safe operation.

## Technical Implementation
The system subscribes to `/scan` (LiDAR) and `/odom` (Odometry) topics, feeding a state-transition node that publishes to `/cmd_vel` to command the TurtleBot3.

```python
# Conceptual FSM Transition Logic
def update_state(current_state, distance):
    # Transition function (δ) logic
    if current_state == "FORWARD" and distance < 0.3:
        return "EMERGENCY_STOP"
    elif current_state == "EMERGENCY_STOP" and distance > 0.5:
        return "SEARCH"
    return current_state

```

How it was done
State Machine Modeling: Drew a formal state diagram to define the robot’s safe operating modes, ensuring all inputs (LiDAR readings) have a defined transition output.

LiDAR Processing: Used sensor_msgs/LaserScan to extract distance metrics and map the robot's local environment, treating the scan data as binary inputs for the logic controller.

Control Integration: Integrated the logic into a ROS2 node, utilizing the cmd_vel publishing mechanism to enforce safety constraints before the robot executes higher-level navigation commands.