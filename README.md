# ID-Finder
### About
This app is designed to help BU-affiliated person to recover their ID. The person who lost their ID can check to see if admin has found the ID and is inside the database. On the other-hand, the admin can input the found ID in the database so the student can check it; and the admin can then remove the ID if the ID is found.

### Features
- Has server-client Interaction
- Database to store the IDs
- Simple UI/UX
- Read JSON files
### Tools Used
- Android Studio
- Visual Studio Code
- Node.js
- Express.js
- SQLite Database
- Integration of Copilot in our workspace
- Google Suite

---
# Installation 
_You need to run both the server (back-end) and the app (front-end) to get all the features working._

## Running the Server
Running the server will allow the app to communicate to the database and get the information. This is a crucial step to ensure the app has its full functionality. The instructions are in the *Readme.md* file in the [ServerCode](https://github.com/HudsonReynolds2/ID-Finder/tree/ServerCode) branch. 

## Running the App
To run the app in Android Studio, clone the project from GitHub to a local folder on your PC. Then run the Gradle to sync the files and build the module. If running through a physical phone, enable developer mode. Then connect the phone via USB, and allow to debug using USB. Before running the app, make sure the settings are correct (i.e. server IP). Go to [Settings.java](https://github.com/HudsonReynolds2/ID-Finder/blob/master/app/src/main/java/com/example/idfinder/Settings.java) located inside `app->main->java->com->example->idfinder->Settings.java` and change the Server URL with the correct server and port. This is important or else your app won't be able to access the database that the IDs are stored in. Another important note, make sure to connect to the same wifi as the server. Then sync the Gradle to ensure everything is updated and build the app. Select the correct phone to run the app and run it. The app should open automatically.

