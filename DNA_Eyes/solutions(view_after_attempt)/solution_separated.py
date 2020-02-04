import cv2
import matplotlib.pyplot as plt
import numpy as np

# This script works for single and 2 circles which have no inner circles.
# Cannot detect tilted circles which are ovals
# Fails when applied to concentric circles because algorithms cannot be used here -> Needs better methods

img = cv2.imread('images/archery_target.jpg')

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
circles = cv2.HoughCircles(gray, cv2.HOUGH_GRADIENT, 1, 20)

if circles.any():
    for circle in circles[0, :]:
        x, y, r = circle
        cv2.circle(img, (x, y), r, (0, 255, 0), 2)
        cv2.circle(img, (x, y), 2, (0, 0, 255), 3)
else:
    print("No circles found")

plt.imshow(img)
plt.show()
