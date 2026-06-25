# Biomedical & Industrial Image Segmentation
A pipeline for isolating and counting microscopic or industrial objects using color space analysis.

## Implementation: HSV Thresholding
I implemented color-based segmentation to separate foreground objects from static backgrounds, which is more robust to lighting variations than grayscale thresholding.

```python
# Convert BGR to HSV
hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

# Mask objects based on pre-defined HSV range
mask = cv2.inRange(hsv, lower_bound, upper_bound)

# Use morphological operations to clean the mask
# 'MORPH_OPEN' removes noise while preserving object shape
kernel = np.ones((5,5), np.uint8)
processed_mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)

```

How it was done
Color Conversion: Switched to HSV color space to decouple intensity from color information, significantly improving segmentation reliability under varying light.

Refinement: Applied morphological MORPH_OPEN (erosion followed by dilation) to eliminate isolated noise pixels and separate touching objects in the mask.

Counting: Used cv2.findContours to identify the boundaries of the segmented regions, allowing for automated object enumeration.