# Computational Stereo & 3D Depth Estimation
A study on geometric computer vision and camera projection.

## Implementation: Calibration Logic
Used a chessboard pattern to solve for camera intrinsic matrices and focal length.

```python
# Chessboard corner detection for calibration
ret, corners = cv2.findChessboardCorners(gray, (7, 6), None)

# Compute camera matrix and distortion coefficients
ret, mtx, dist, rvecs, tvecs = cv2.calibrateCamera(
    objpoints, imgpoints, gray.shape[::-1], None, None
)

```

How it was done
Calibration: Captured multiple angles of a known chessboard target.

Geometric Modeling: Solved for the intrinsic matrix (focal length, principal point) using calibrateCamera.

Estimation: Applied these parameters to triangulate the distance to the tip of an object, quantifying uncertainty in the estimation.