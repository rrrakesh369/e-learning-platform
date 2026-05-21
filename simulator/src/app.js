const express = require("express");
const cors = require("cors");


const enrollRoute = require("./routes/enrollRoute");
const assignmentRoute = require("./routes/assignmentRoute");
const batchRoute = require("./routes/batchRoute");

const app = express();

app.use(express.json());

// app.use(cors());

app.use("/simulate/enroll", enrollRoute);

app.use("/simulate/assignment",assignmentRoute);

app.use("/simulate/batch",batchRoute);

const PORT = 3001;

app.listen(
    PORT,
    () => {
        console.log(
            `Simulator running on port ${PORT}`
        );
    }
);