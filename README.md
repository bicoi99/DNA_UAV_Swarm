# DNA_UAV_Swarm
Welcome to team Defend Now Attack (DNA) github page. We are a team that represent the University of Southampton in the BAE 2020 UAV Swarm Challenge.

## DNA_Eyes
This is the sub-group within our team that is reponsible for providing vision for the team.

So far I have created a folder for my sub-group to experiment with OpenCV, particularly the Hough Transform for circle detection. Several other image processing techniques are to be explore when completing the tasks provided (colour segmentation, grayscaling, thresholding, Canny edge detection etc.).

### Instructions for first few familiarising tasks
**Getting the files**
1. If you are familiar with GitHub then clone this repo and everything is included
2. If you are not then simply go into DNA_Eyes/images folder and grab all the images for your own use

**Tasks**

__To complete this task, you should know how to read in images to Python using OpenCV, convert between different colour spaces (grayscale, RGB, BGR etc.), conditionals and loops, array slicing and OpenCV drawing functions for plotting and displaying results (circle, rectangle, lines, etc.). Tutorials for these are readily with a simple google search, I will leave that to you to do on your own but I have included some links to Hough Circle Transform below for you to navigate, those two websites are my go-to when learning OpenCV so utilise those where possible__

1. Attempt to find circles using provided function
   * Use OpenCV to find circle in the single circle image.
   * Then move on to 2 circles, do you need to change anything?
   * __Think of what the Hough Transform require as input and what it outputs. Ensure that the correct input goes in. When you know what the function outputs, use them to plot graphs to verify that the function works. Check the DNA_Eyes/results folder for results that I generated and DNA_Eyes/solutions folder for the script I used to make the plots **View these after you have attempted or have gotten stuck, why did I do the things I did, what else can be implemented to use this on other images with less details?**__

2. Now that you know the basics, applied what you know on more difficult images - concentric circles, archery targets and tilted circles/ovals
   * You will notice that the algorithm fails at finding ovals - finding no circles on oval images
   * Algorithm also fails/functions incorrectly on the concentric circles. This is a fundamental flaw of this Hough Transform, how might you rectify this, what other techniques can you implement to detect center of circles (because at the end we only want to center of the circles to find the distance difference to the center of our screen).

**Useful links with background and examples**
* Hough circle transform - main algorithm we are interested in
   * Documentation: https://docs.opencv.org/4.2.0/d4/d70/tutorial_hough_circle.html
   * OpenCV-Python Tutorials: https://opencv-python-tutroals.readthedocs.io/en/latest/py_tutorials/py_imgproc/py_houghcircles/py_houghcircles.html