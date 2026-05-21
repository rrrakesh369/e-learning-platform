const express = require("express");
const router = express.Router();

const {sendNotification} = require("../services/notificationService");

router.post("/", async (req, res) => {

    const payload = {
        userId: "user20",
        type: "COURSE_ENROLLED",
        title: "New Course: React Basics",
        message: "You have successfully enrolled in React Basics course."
    };

    const result = await sendNotification(payload);

    res.status(result.success ? 200 : 500).json(result);

});

module.exports = router;