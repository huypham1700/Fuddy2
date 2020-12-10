var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const RatingModel = require('../models/RatingStore')(sequelize)
const { validateString } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");


/**
 * URL: http://localhost:3000/rating/myRate
 */
router.post('/myRate', async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "failed",
            data: null,
            message: 'Token is invalid'
        })
        return;
    }
    const { userId } = req.body
    try {
        let result = await RatingModel.findAndCountAll({
            where: {
                userId: {
                    [Op.eq]: userId
                }
            }
        })
        res.json({
            result: 'SC',
            data: {
                result
            }
        })
    } catch (exception) {
        res.status(500).json({
            result: 'failed 500',
            data: {},
            message: `Error details: ${exception.toString()}`,
            errors: []
        })
    }
})


module.exports = router