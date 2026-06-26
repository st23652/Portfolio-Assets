# ROS2 Dynamic Obstacle Detection & Safety Controller
A supervisory safety layer for autonomous mobile robots.

## Features
- Dynamic safety buffer calculation based on real-time LiDAR inputs.
- Obstacle prioritization: Distinguishing between static walls and dynamic obstacles.
- Command-Override logic: Injecting safety-stop signals into the `/cmd_vel` topic.
- ROS2-native integration: Subscribing to `/scan` for rapid reflex reactions.

## Technical Implementation: LiDAR Data Processing
Processing LiDAR data to detect obstacles within a 360-degree range.

```python
# Accessing specific laser beam segments (e.g., front, left, right)
# my_ls.ranges[0] is front, 90 is right, 270 is left
def safety_monitor_callback(msg):
    front_distance = msg.ranges[0]
    
    # Emergency stop threshold
    if front_distance < 0.3:
        emergency_stop()
    else:
        continue_navigation()


```

How it was done
Sensor Integration: Utilized the sensor_msgs/LaserScan topic to access real-time distance readings from the TurtleBot3's LiDAR scanner.

Reflexive Programming: Implemented a callback-driven architecture that prioritizes safety signals over navigation waypoints, ensuring the robot stops instantly upon obstacle detection.

Visualization: Used RViz to monitor the scan data and visualize the "safety bubble" around the robot during navigation trials.

