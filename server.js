const express = require('express');
const app = express();

const sqlite3 = require('sqlite3');
const db = new sqlite3.Database('ids.db');
app.use(express.json());

//Creates the database if it doesn't exist
db.serialize(() => {
	db.run('CREATE TABLE IF NOT EXISTS ids (id INTEGER PRIMARY KEY)');
});

app.post('/addID', (req, res) => {
	const id = req.body.id;
	console.log("sentData " + id);

	db.run('INSERT INTO ids (id) VALUES (?)', [id], (err) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error inserting ID into database');
		} else {
			res.send('ID inserted successfully');
			console.log("ID inserted successfully");
		}
	});
});

app.delete('/deleteID', (req, res) => {
	const id = req.body.id;
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

app.post('/checkID', (req, res) => {
	const id = req.body.id;
	console.log("checkData " + id);
	db.all('SELECT * FROM ids WHERE id = ?', [id], (err, rows) => {
		if (err) {
			console.error(err);
			res.status(500).send('Error retrieving IDs from database');
		} else {
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

app.delete('/deleteAllIds', (req, res) => {
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

app.listen(5000, () => console.log('Example app is listening on port 3000.'));