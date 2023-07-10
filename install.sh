#!/bin/bash


# FIRST AND FOREMOST, GET THE REPO TOOL. ONCE YOU ARE DOWN WITH THAT, EXECUTE THIS SCRIPT
# If you have installed AOSP on your system before, comment out the set of commands under 'Install required packages'
# #Make changes in this script as per your needs! 

# Install required packages
sudo apt-get install git-core gnupg flex bison build-essential zip curl zlib1g-dev gcc-multilib g++-multilib libc6-dev-i386 lib32ncurses5-dev x11proto-core-dev libx11-dev lib32z1-dev libgl1-mesa-dev libxml2-utils xsltproc unzip fontconfig

sudo apt update
sudo apt upgrade

# Create a directory named 'aosp' and navigate into it
mkdir aosp && cd aosp

# Set global Git configurations for user name and email
git config --global user.name "YOUR NAME"
git config --global user.email "YOUR EMAIL"

# Create a directory named '.bin' in the user's home directory
mkdir -p ~/.bin

# Add the '.bin' directory to the PATH environment variable
export PATH="${HOME}/.bin:${PATH}"

# Download the 'repo' tool and save it in the '.bin' directory
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/.bin/repo

# Provide executable permissions to the 'repo' tool
chmod a+rx ~/.bin/repo

#################################################### IF YOU HAVE INSTALLED AOSP BUILDS BEFORE, COMMENT EVERY COMMAND BEFORE THIS ##########################################################
#_________________________________________________________________________________________________________________________________________________________________________________________#

# cd aosp  (Remove the # to this command before you begin only if you have installed aosp builds before. Navigate to your directory)

# Initialize the AOSP source code repository using 'repo' tool
# Please visit 'source code tags and builds' by Google and get your choice of android build
repo init -u https://android.googlesource.com/platform/manifest -b <YOUR_CHOICE_OF_ANDROID>

#After repo init, if you face an issue with python, specifically, '/usr/bin/env: ‘python’: No such file or directory' execute the command below and remove the comment #
# sudo ln -s /usr/bin/python3 /usr/bin/python

# After this script is done executing, download the binaries from Google and Qualcomm corresponding to your Build ID from the <CHOICE_OF_ANDROID> earlier.
# IT IS VERY IMPORTANT TO DOWNLOAD THE RIGHT BINARY OR ELSE YOU WILL FACE ISSUES LIKE GETTING STUCK IN BOOTLOOP, OR ON THE GOOGLE LOGO ON THE BOOT SCREEN!!

# The binaries you download will have to be extracted.
