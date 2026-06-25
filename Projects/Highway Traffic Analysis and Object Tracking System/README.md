# Highway Traffic Analysis and Object Tracking
An automated vehicle counting system developed in Python/OpenCV.

## Implementation: Background Segmentation
The system isolates moving objects by calculating the difference between the current frame and a moving average background model.

```python
# Background Subtraction
fgbg = cv2.createBackgroundSubtractorMOG2()
fgmask = fgbg.apply(frame)

# Clean noise with morphological operations
kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
fgmask = cv2.morphologyEx(fgmask, cv2.MORPH_OPEN, kernel)

```

How it was done
Video I/O: Captured frames from highway.mp4 using cv2.VideoCapture.

Segmentation: Used Mixture of Gaussians (MOG2) subtraction to highlight foreground objects.

Contour Analysis: Identified blobs, filtered them by size, and tracked their movement across a fixed detection line to increment the vehicle count.