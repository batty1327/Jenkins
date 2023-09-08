// Import the 'express' module and assign it to the 'express' variable.
var express = require('express');

// Create an instance of the Express application.
var app = express();

// Define a route for handling HTTP GET requests to the root path ('/').
app.get('/', function (req, res) {
    // When a GET request is made to '/', respond with the string 'hello world'.
    res.send('hello world');
});

// Start the Express application on the specified port.
// If 'process.env.PORT' is defined (e.g., in a cloud environment), use that port;
// otherwise, use port 80 as a default.
app.listen(process.env.PORT || 80);

// Export the Express application for use in other parts of the program (e.g., in tests).
module.exports = app;
