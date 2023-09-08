// Import the 'supertest' library to make HTTP requests
var request = require('supertest');

// Import the Express.js application (presumably defined in 'index.js')
var app = require('../index.js');

// Describe a test suite for the server's behavior when receiving a GET request to '/'
describe('GET /', function() {

  // Define a test case within the suite
  it('respond with hello world', function(done) {
    
    // Send an HTTP GET request to the root ('/') route of the Express app
    request(app)
      .get('/')
      
      // Expect the response to be 'hello world'
      .expect('hello world', done);
  });
});
