# Server Instructions

### Setting Up the Server
To get the Server running, open a folder/workspace where you will host the server. Then install [Node.js](https://nodejs.org/en). Open PowerShell or command prompt to the folder you want to host the server and run the following commands
1. `npm init` <- creates the .json file for the project 
2. `npm install express` <- installs express package
3. `npm install sqlite3` <- installs the database package

The commands above will allow you to create the Server
###### (Optional) Changing the Port

If you wish to change the port of your server, go to `server.js` and change the number `5000` to wanted port number in the following line: 
```javascript 
app.listen(5000, () => console.log('Example app is listening on port 5000.'));
```


### Running the Server
To run the server, go to the PowerShell that was opened and type in `ipconfig` and record your IPv4 address. This will be your server URL. Make sure to update this in your [Settings.java](https://github.com/HudsonReynolds2/ID-Finder/blob/master/app/src/main/java/com/example/idfinder/Settings.java) located inside app->main->java->com->example->idfinder->Settings.java in the main branch. Then to run it, just type in `node server.js`; to stop the server, use **CTRL + C** (Windows) or **COMMAND+C** (Mac). 

