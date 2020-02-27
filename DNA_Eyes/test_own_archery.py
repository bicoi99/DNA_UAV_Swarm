import cv2
import matplotlib.pyplot as plt
import numpy as np

img = cv2.imread('Test_own_archery.JPG')
rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

plt.imshow(rgb)
plt.show()
