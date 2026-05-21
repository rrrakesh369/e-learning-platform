const express = require("express");
const router = express.Router();

const {
    sendNotification
} = require(
    "../services/notificationService"
);

router.post(
    "/",
    async (req, res) => {

        let successCount = 0;
        let failedCount = 0;

        for (let i = 1; i <= 50; i++) {

            const payload = {

                userId: "user80",

                type:
                "COURSE_ENROLLED",

                title:
                `Batch notification ${i}`,

                message:
                `This is batch notification message ${i}`
            };

            try {

                await sendNotification(
                    payload
                );

                successCount++;

            } catch (error) {

                failedCount++;

                console.log(
                    `Failed ${i}:`,
                    error.response
                    ?.data
                    ?.message
                    || error.message
                );
            }
        }

        console.log(
            `Success: ${successCount}`
        );

        console.log(
            `Failed: ${failedCount}`
        );

        res.status(200).json({

            totalRequests: 50,

            success:
            successCount,

            failed:
            failedCount
        });
    }
);

module.exports = router;