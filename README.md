# HW1 App
Software Engineering (CEG 4110) HW1

### Overview

This is the source code for an Android app developed in Android Studio. This application allows you to toggle between two parts:

Part one allows you to edit the content of text and randomly assign the text a new color by the click of the "Change Color" button. This new random color is then displayed in its RGB values and HTML color code below the button.

Part two is a paint application that allows you to draw on your screen with the touch of your finger. You are able to change the color of your paint by selecting the new desired RGB values at the top right of the screen. Once completed with your masterpiece, you are able to save your work into your gallary (make sure you give the app access to gallery under settings) or clear your work to start with a blank screen again.

### Deployment

You can either launch this application on your phone through the source code or the APK.

## Source Code:

To run this app, download the project zip folder and extract the contents on to your computer. Launch Android Studio (or download it at this link if you do not have it installed: https://developer.android.com/studio/) and click to open an existing project within Android Studio as shown below. 

![2018-09-14 2](https://user-images.githubusercontent.com/22596783/45560708-e626ec00-b813-11e8-8e9c-81cb24b5c643.png)

Browse to where you saved the extracted project hw1-app-master and click to open it. 

![2018-09-14 3](https://user-images.githubusercontent.com/22596783/45560818-24bca680-b814-11e8-9b56-c02d8cae81c7.png)

Android Studio should then load the project in. Once it has finished indexing, you can either plug in your android device through a USB cable and hit the play button button at the top of your screen and selecting your plugged-in device to run the app on. Or you can create and run an android emulator through android studio by hitting the play button and then selecting/creating an emulator with the dialog screen the pops up.

![2018-09-14 5](https://user-images.githubusercontent.com/22596783/45561112-f1c6e280-b814-11e8-9ad0-9fa1650206e5.png)

After Android Studio has finished building, the HW1 application will download and launch on your phone (or emulator). The app will still be available on your phone under applications as "HW1" even after stopping android studio and/or unplugging your device. Make sure to give the app access to gallery under settings.

## APK:

To run this app, download the project zip folder and extract the contents on to your computer. Plug in your Android mobile device to your computer and connect as media device if you get prompted. Then open up your folder to view your Android device's files under "My Computer" or "This PC". Once you have your Android device's files open, copy the APK from the bin folder in the extracted "hw1-app-master" project to the root of your Android device's files as shown below.

![image](https://user-images.githubusercontent.com/22596783/45722495-ab59e680-bb7a-11e8-909d-72ee0031e3e2.png)

Now, switch to your Android device and open the application "My Files". Locate your APK, click on your APK, and click "Install". Once it has finished installing, click "Open" to launch the application.

### Software Design

The basic design of the software was to build a main screen upon launch. This main screen gives you the option to either run part 1 or part 2. Clicking these options changes the screen displayed to the part picked. Each screen is built and formatted with XML where each feature (button, text, touch, etc.) is attached to on action commands and gets run when click/touched.

The design for part 1 is built through a simple EditText XML tag that allows modification by the user. Then the "Change Color" button calls on an action to grab the EditText tag to change the color to a random value.

The design for part 2 is built as a PaintView class extending view. This allows the class to have the basic functionality of touch movements and actions. The paint and path objects are also used to keep track of paths created through touch with the specified paint attributes. These are both created as ArrayLists to implement the changing of color functionality. The changing of color is implemented through RGB values given by the user. The saving of the canvas was implemented through the retrieval of the drawing cache and then compressed and saved to the camera folder.
