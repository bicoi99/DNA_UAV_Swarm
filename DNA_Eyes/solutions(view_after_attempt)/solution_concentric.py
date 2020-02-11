import cv2
import matplotlib.pyplot as plt
import numpy as np

# This script is in progress
# Tried to use contour detection to find the outermost circles, heirachy works but only when the things are clear
# Can try to use colours of the archery targets to isolate the circles

img = cv2.imread('images/concentric_circles.jpg')

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
edges = cv2.Canny(gray, 100, 200)
contours, heirarchy = cv2.findContours(
    edges, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
circles = cv2.HoughCircles(edges, cv2.HOUGH_GRADIENT, 1, 20)

print(heirarchy)

# if circles.any():
#     for circle in circles[0, :]:
#         x, y, r = circle
#         cv2.circle(img, (x, y), r, (0, 255, 0), 2)
#         cv2.circle(img, (x, y), 2, (0, 0, 255), 3)
# else:
#     print("No circles found")

canvas = np.zeros((626, 626, 3))
cv2.drawContours(canvas, contours, -1, (0, 255, 0), 3)

plt.imshow(canvas)
plt.show()

cv2.HoughCircles()
