var express = require('express');
var router = express.Router();
const { sendFirebaseCloudMessage } = require('../firebase-notifications/firebaseCloudMessaging')
const { sequelize } = require('../databases/database')
const PhoneTokenModel = require('../models/PhoneToken')(sequelize)
const NotificationModel = require('../models/Notification')(sequelize)
const { validatePhoneToken } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");
const { checkToken } = require('../helpers/TokenCheck')

/**
 * URL: http://localhost:3000/noti/addTokenKey
 */
router.post('/addTokenKey', validatePhoneToken(), async(req, res) => {
    //validate du lieu tu client gui len
    const errors = validationResult(req);
    const { tokenKey } = req.body
    try {
        if (!errors.isEmpty()) {
            let foundPhoneTokenModel = await PhoneTokenModel.findAll({
                where: {
                    tokenKey: {
                        [Op.eq]: tokenKey
                    }
                }
            })
            if (foundPhoneTokenModel.length > 0) {
                res.json({
                    result: 'failed',
                    message: 'PhoneToken already exsited'
                })
                return
            }
            
        }
            let newPhoneToken = await PhoneTokenModel.create({
                tokenKey
            })
            await newPhoneToken.save()

            res.json({
                result: 'ok',
                message: 'Register new user successfully'
            })
            return
        
    } catch (exception) {
        res.status(500).json({
            result: 'failed 500',
            data: {},
            message: `Error details: ${exception.toString()}`,
            errors: []
        })
    }
})




// // //http://192.168.1.142:3000/noti/sendNoti
router.post('/sendNotiGlobal', validatePhoneToken(), async(req, res) => {
    //validate du lieu tu client gui len    

    const { title, data } = req.body

    let foundPhoneToken = await PhoneTokenModel.findAll({
        attributes: ['tokenKey']
    })

    const listToken = Array.from(foundPhoneToken, x => x.getDataValue('tokenKey'))
    await sendFirebaseCloudMessage({
        title: title,
        data: data,
        notificationTokens: listToken
    })
    res.json({
        result: 'ok',
        message: 'Send ok'
    })
})




/**
 * URL: http://localhost:3000/foods/getfood
 */
const getTokenKey = async() => {
    let foundPhoneToken = await PhoneTokenModel.findAll({
        attributes: ['tokenKey']
    })
    return foundPhoneToken

}

/**
 * URL: http://localhost:3000/noti/getListNoti
 */
router.post('/getListNoti', validatePhoneToken(), async(req, res) => {
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01"
        })
        return;
    }
    let foundListNoti = await NotificationModel.findAll();
    res.json({
        result: 'SC',
        data: foundListNoti
    })
})



module.exports = router