const express = require("express");
const router = express.Router();

const {
    sendNotification
} = require("../services/notificationService");

router.post("/", async (req, res) => {

    const payload = {
        userId: "user25",
        type: "ASSIGNMENT_DUE",
        title: "Assignment Due: Week 3 Quiz",
        message: "Week 3 quiz deadline is approaching. Complete it soon."
    };

    const result = await sendNotification(payload);

    res.status(result.success ? 200 : 500).json(result);

});

module.exports = router;