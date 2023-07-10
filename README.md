# Network_Analyzer
*******************************************************************
Network Analyzer using Java
Developed by- Anish Puranik @Sigma Connectivity, Carlsbad, CA
Developed in VSCode and Android Studio
OS used- Ubuntu 18.04.6 LTS
	 Processor- Intel® Xeon(R) Silver 4114 CPU @ 2.20GHz × 20
AOSP build- aosp_barbet-userdebug (Android 11)
Phone- Pixel 5a (5G)
*******************************************************************

README

included: 
All code files (Android Studio Project, install.sh, sync.sh, build_sdk.sh) 
This README:
Steps to build your choice of AOSP build, build your SDK and execute the code in Android Studio and install the Network Analyzer on your phone, that keeps a track of received and transmitted Kb per 3 seconds. The app will also provide an option to check if the phone is connected to network or not and if not, it will redirect you to user settings to enable Wi-Fi. You will be able to download this log into a text file locally on your phone. 

PATH- <Your_Device_Name>/Internal shared storage/Documents/Sent_Received_Data.txt
adb command- adb shell cat /storage/emulated/0/Documents/Sent_Received_Data.txt (or your custom path)
___________________________________________________________________________________________________________________________________________________________________________________________________________

*** After following this successfully, you will have an app that allows you to download network stats to a text file. ***

- To get started, you need to have an AOSP build installed on your Android phone. You can also use an emulator with an AOSP build.

- Execute the 'install.sh' script. This script will:
 	1. Install required packages
	2. Create an 'asop' directory for you where you need to install and extract vendor binaries.
	3. Get the repo tool and save it.
	4. Navigate to the directory and initialize AOSP source code using the repo tool

- After this script has successfully executed, go to 'https://developers.google.com/android/drivers' and download the correct binaries. Navigate to your root folder, in this case, aosp, and extract these files, accept the license. You should have both qualcomm and Google binaries.

- Once that is done, run the 'sync.sh' script. This will:
	1. Sync the repo files
	2. Flash the build on your phone
	***IMP*** make changes in the shell script to incorporate your flavor of Android so that you 		build the correct target.
__________________________________________________________________________________________________________________________________________________________________________________________________________

We now get to Android Studio. To build the SDK, you need to install some binaries. You can use 'build_sdk.sh' script to build the SDK. The credits for this script, go to Kevin Ossia.
__________________________________________________________________________________________________________________________________________________________________________________________________________

Open this project in your Android Studio after setting up the right SDK. Thats it! You can make your own modifications to the code if you want. There are several methods to perform this activity.




