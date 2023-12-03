//imports and required modules
const express = require('express');
const app = express();

const sqlite3 = require('sqlite3');
const db = new sqlite3.Database('ids.db');
app.use(express.json());

//Creates the database if it doesn't exist
db.serialize(() => {
	db.run('CREATE TABLE IF NOT EXISTS ids (id INTEGER PRIMARY KEY)');
});

//Keywords: 
//post => send data to the server
//get => get data from the server
//req = request
//res = response

//gets the request from the client and sends it to the database
app.post('/addID', (req, res) => {
	const id = req.body.id;
	console.log("sentData " + id);

	//adds the id to the database
	db.run('INSERT INTO ids (id) VALUES (?)', [id], (err) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error inserting ID into database');
		} else {
			//sends back a response to the client
			res.send('ID inserted successfully');
			console.log("ID inserted successfully");
		}
	});
});

//gets the request from the client and sends it to the database
app.delete('/deleteID', (req, res) => {
	const id = req.body.id;
	//delete the requested id from the database
	db.run('DELETE FROM ids WHERE id = ?', [id], (err) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error deleting IDs from database');
		} else {
			console.log("ID deleted successfully");
			res.send('IDs deleted successfully');
		}
	});
});

//checks if the id exists in the database
app.post('/checkID', (req, res) => {
	const id = req.body.id;
	console.log("checkData " + id);
	db.all('SELECT * FROM ids WHERE id = ?', [id], (err, rows) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error retrieving IDs from database');
		} else {
			//if the id exists, send true otherwise send false
			console.log(id);
			if (rows.length > 0) {
				console.log("ID exists");
				res.send(true);
			} else {
				console.log("ID does not exist");
				res.send(false);
			}
		}
	});
});

//gets all the ids from the database
app.get('/getIDs', (req, res) => {
	db.all('SELECT * FROM ids', (err, rows) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error retrieving IDs from database');
		} else {
			console.log("All IDs retrieved")
			res.json(rows);
		}
	});
});

//deletes all the ids from the database
app.delete('/deleteAll', (req, res) => {
	console.log("Deleting all IDs");
	db.run('DELETE FROM ids', (err) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error deleting IDs from database');
		} else {
			console.log("All IDs deleted");
			res.send('IDs deleted successfully');
		}
	});
});

//starts the server
app.listen(5000, () => console.log('Example app is listening on port 500.'));