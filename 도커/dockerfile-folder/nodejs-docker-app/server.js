const express = require('express')

//Constants
const PORT = 8080;

//APP
const app = express();
app.get('/', (req, res) => {
    res.send("안녕하세요")
});

app.listen(PORT);
console.log("Server is running")