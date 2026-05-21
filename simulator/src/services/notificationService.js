const axios = require("axios");

const BASE_URL = "http://localhost:9000/api/notifications";

const sendNotification = async (payload) => {
    try {
        const response = await axios.post(BASE_URL, payload);

        return {
            success: true,
            data: response.data
        };

    } catch (error) {

        return {
            success: false,
            error: error.message
        };
    }
};

module.exports = { sendNotification };