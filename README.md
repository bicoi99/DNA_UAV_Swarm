# DNA_UAV_Swarm
Welcome to team Defend Now Attack (DNA) github page. We are a team that represent the University of Southampton in the BAE 2020 UAV Swarm Challenge.

## A quick guide to git and GitHub
### Git - a version control system
Every developer has a “repository” where all their code is being stored and there is a remote repository stored in the cloud. Differences and changes are stored locally on each developer machine, the remote will only be updated when the repos are “merged” together. This allows individual or subgroups to work on their particular feature without affecting the full code of the entire team. The versions of the code uploaded to the cloud are stored and can be returned to at any time if decided that the new changes are to be discarded.

### Basic terminal/command prompt navigation commands
1. To display the folders present within the current directory: `ls` (Mac) `dir` (Windows)
2. To navigate into certain folders: `cd "FOLDER NAME"`

### Installing git
1.	Downloading git
      1.	Visit https://git-scm.com/ . 
      2.	Go to Downloads page and download git for your operating system.
      3.	Follow the install instructions which are self-explanatory.
2.	Check that git is installed correctly on your machine:
      1.	Open up your terminal (Mac)/command prompt(Windows).
      2.	Type the command `git --version` to verify that git is correctly installed.
3.	Set global variables – your git username and email to let people from GitHub know who you are when you make changes
      1.	`git config --global user.name "YOUR NAME HERE"` to set your name.
      2.	`git config --global user.email "YOUR EMAIL HERE"` to set your email.
      3.	`git config --list` to check if global variables are set.

### Initialise git repository from code you already have
1.	If you want to start using git on your code:
      1. Navigate to your local folder in your system using `cd "PATH"` (different slashes in Windows and Mac).
      2. `git init` to start using git for that folder.
2. A typical commit procedure **IMPORTANT**
      1. A commit is when you have some edits to your code and you want to tell git that you are satisfied with the code and want to store this checkpoint.
      2. `git status` shows which changes have been made to your repository since your last commit.
         * There might be files that you do not want to be tracked with git (personal settings files/folders).
         * To ignore these, create a `.gitignore` file (exact name) and write the name of the files and folders to be ignored. (demo)
      3. There are three states in git: Working directory, Staging area and Repository [image](https://miro.medium.com/max/686/1*diRLm1S5hkVoh5qeArND0Q.png)
         1. Working directory is where the code changes happen. Untracked and altered files in this state will show up when `git status` is run
         2. Staging area is where we organise our files to be committed. This is for when you want to only commit certain files while leaving other files which are still in the works to be committed later. For our application, we just stage all the files since we are relatively small.
            * `git add -A` to stage **all** changes.
            * `git status` to check the staged files before commit.
         3. Repository is where the code will be stored after committing.
            * `git commit -m "COMMIT MESSAGE HERE"` to commit. The commit message goes into the quotation marks, these should be detailed highlighting what has been changed to make going back easier.
            * `git status` to check (just for peace of mind at this point)
            * `git log` to read all commit messages and more details about the commits. Press `q` to exit the log if there are many commits already.

### Cloning git repository (remote from somewhere, most often GitHub)
1. If you want to download a remote repository into your local machine for development:
      1. Go to GitHub and obtain the URL of the git repo.
      2. `git clone <url> <where to clone>` to clone the repository. (demo)
         * `<url>` is the URL of the git repo.
         * `<where to clone>` is the path at which you want the repo to be cloned to (if left empty, it will be at the current directory - this is normally what we do since we navigate to our destination before cloning).
2. Once you have the repo on your machine, you can edit your changes and commit just as before, but you need to push this to the remote repository if you want the effect to appear at the remote repo. The typical workflow is as follows - similar to before but with 1 extra step at the end **IMPORTANT** (demo)
      1. *Edit code*
      2. `git diff` to display the changes being made
      3. `git status` to check the changes
      4. `git add -A` stage all changes
      5. `git commit -m "COMMIT MESSAGE HERE"` to commit changes with a comment
      6. `git pull origin master` to pull from the remote repo to ensure that the local code is up to date with the remote code (others have made commits while you are working on your feature) **always try to pull before pushing**
      7. `git push origin master` to push your changes from your local repo to the remote repo

### Branching
So far we have been committing straight to the master branch , `git branch -a` to check remote repository branches, but this is a terrible idea when multiple people are working on the same project. Branching is almost always compulsory when working with groups to ensure changes get peered review before getting committed. There can be a branch of a branch of a branch, i.e. the circle detection branch of the dna_eyes branch. (demo workflow)
1. If you are working on a particular feature, create a branch for that feature and enter that branch.
      1. `git branch <branch_name>` to create a branch (no spaces and no quotation marks).
      2. `git branch` to check the branches available locally. The asterisk next to branch indicate the branch you are currently on.
      3. `git checkout <branch_name>` to enter that branch and start work. Check that you switched branch by `git branch`.
2. Once in this branch, you can work just as before, create local commits etc.
3. After finishing the changes, you push the local changes to the remote repo:
      1. `git push -u origin <branch_name>` to push changes locally on that branch to the remote repository branch. origin is the name of the remote repository.
      2. `git branch -a` to check the branches at the remote repository.
4. Now to merge the branches in the remote repository.
   * This is done on GitHub, or in git itself. To do it in git, you checkout to the master branch, pull the changes from origin master, merge the branch using `git merge <branch_name>` and then push to origin master. However, when using GitHub, you can make what is called a pull request and this means others will need to check your code before committing and merging the branch. This is the preferred way because it encourages peer review.
   * It is customary to delete the branch once you have merged the branch into master so you can do it via GitHub or `git branch -d <branch_name>` to delete branch locally and `git push origin --delete <branch_name>` to delete branch at remote repo.

### Reverting changes
When you made a mistake in your commit and want to go back to a previous commit, git shows its strength.

1. `git log` to display the commits, find the hash value of the commit you want to go back to.
2. `git revert <commit_hash>` to revert the changes made by this commit and you are back at this point.

### Full workflow commands, demo
1. *At the master branch, just finished cloning the remote repo (maths_functions). Now want to make some changes to a particular feature (subtract)*.
2. `git branch substract` to create a substract branch locally.
3. `git checkout substract` switch go to this branch.
4. *Edit code*.
5. `git status` to see the changes have been registered.
6. `git add -A` to stage changes.
7. `git commit -m "Added substract feature"` to commit changes locally.
8. `git push -u origin substract` to push the changes and the branch to the remote repo.
9. Go to GitHub and make a pull request to let other check your work,
10. If everything is good, merge the changes and delete the branch on GitHub or through terminal like this:
11. `git checkout master` to go to master branch,
12. `git pull origin master` to pull down any changes made at remote repo.
13. `git merge substract` to merge the branch.
14. `git push origin master` to push the merge to the repository.
15. `git branch -d substract` to delete branch locally.
16. `git push --delete substract` to delete branch at remote repo.
17. `git status` to find the commit hash to revert.
18. `git revert <commit_hash>` to rever to before that commit.

### Documentation
I do not know everything and thus cannot cover everything. I have been using git to track my IP but have not used it in a company setting so branching is not a fimiliar thing for me and I am learning along with you guys. Therefore, I recommend that you guys read the [documentation](https://git-scm.com/book/en/v2) as your bedtime reading.

This guide is largely inspired by this [video](https://www.youtube.com/watch?v=HVsySz-h9r4&t), so all the credits go to him. Do give this a watch if you want a different delivery on the same content.

## DNA_Eyes
This is the sub-group within our team that is reponsible for providing vision for the team.

So far I have created a folder for my sub-group to experiment with OpenCV, particularly the Hough Transform for circle detection. Several other image processing techniques are to be explore when completing the tasks provided (colour segmentation, grayscaling, thresholding, Canny edge detection etc.).

### Instructions for first few familiarising tasks
**Getting the files**
1. If you are familiar with GitHub then clone this repo and everything is included
2. If you are not then simply go into DNA_Eyes/images folder and grab all the images for your own use

**Tasks**

_To complete this task, you should know how to read in images to Python using OpenCV, convert between different colour spaces (grayscale, RGB, BGR etc.), conditionals and loops, array slicing and OpenCV drawing functions for plotting and displaying results (circle, rectangle, lines, etc.). Tutorials for these are readily with a simple google search, I will leave that to you to do on your own but I have included some links to Hough Circle Transform below for you to navigate, those two websites are my go-to when learning OpenCV so utilise those where possible_

1. Attempt to find circles using provided function
   * Use OpenCV to find circle in the single circle image.
   * Then move on to 2 circles, do you need to change anything?
   * _Think of what the Hough Transform require as input and what it outputs. Ensure that the correct input goes in. When you know what the function outputs, use them to plot graphs to verify that the function works. Check the DNA_Eyes/results folder for results that I generated and DNA_Eyes/solutions folder for the script I used to make the plots **View these after you have attempted or have gotten stuck, why did I do the things I did, what else can be implemented to use this on other images with less details?**_

2. Now that you know the basics, applied what you know on more difficult images - concentric circles, archery targets and tilted circles/ovals
   * You will notice that the algorithm fails at finding ovals - finding no circles on oval images
   * Algorithm also fails/functions incorrectly on the concentric circles. This is a fundamental flaw of this Hough Transform, how might you rectify this, what other techniques can you implement to detect center of circles (because at the end we only want to center of the circles to find the distance difference to the center of our screen).

**Useful links with background and examples**
* Hough circle transform - main algorithm we are interested in
   * Documentation: https://docs.opencv.org/4.2.0/d4/d70/tutorial_hough_circle.html
   * OpenCV-Python Tutorials: https://opencv-python-tutroals.readthedocs.io/en/latest/py_tutorials/py_imgproc/py_houghcircles/py_houghcircles.html