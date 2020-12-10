var express = require('express')
var router = express.Router()
const bcrypt = require('bcrypt');
const { validationResult } = require('express-validator')
const { validateRegisterUser, validateLogin } = require('../validations/validate')

const { Op } = require("sequelize");
const { sequelize } = require('../databases/database')
const UserModel = require('../models/User')(sequelize)
const ImageModel = require('../models/Image')(sequelize)

ImageModel.hasMany(UserModel, { foreignKey: 'imageId' })
UserModel.belongsTo(ImageModel, { foreignKey: 'imageId' })
    //http://192.168.1.142:3000/users/register
router.post('/register', validateRegisterUser(), async(req, res) => {
    //validate du lieu tu client gui len    
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.status(422).json({
            result: 'failed',
            data: {},
            message: 'Validation input error',
            errors: errors.errors
        });
        return;
    }
    const { email, password, userType } = req.body
    try {
        let foundUsers = await UserModel.findAll({
            where: {
                email: {
                    [Op.eq]: email
                }
            }
        })
        if (foundUsers.length > 0) {
            res.json({
                result: 'failed',
                data: {},
                message: 'User tồn tại'
            })
            return
        }
        let hashPassword = await bcrypt.hash(password, 10);
        const expireDate = new Date();
        await expireDate.setDate(expireDate.getDate() + 30);
        let newUser = await UserModel.create({
            email,
            hashPassword,
            name: "",
            userType,
            imageId: 1,
            expireDate,
            tokenKey: require('key-creator').generate()
        })
        await newUser.save()

        res.json({
            result: 'SC',
            data: newUser,
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



//http://192.168.1.142:3000/users/login
router.post('/login', validateLogin(), async(req, res) => {
    //validate du lieu tu client gui len    
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.status(422).json({
            result: 'failed',
            data: {},
            message: 'Validation input error',
            errors: errors.errors
        });
        return;
    }
    //lay du lieu tu client        
    const { email, password } = req.body
    try {
        let foundUsers = await UserModel.findAll({
            where: {
                email: {
                    [Op.eq]: email
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageURL']
            }]
        })
        if (foundUsers.length == 0) {
            res.json({
                result: 'failed',
                data: {},
                message: 'Email/password is incorrect'
            })
            return
        }
        let foundUser = foundUsers[0]
        let hashPassword = foundUser.hashPassword

        let isMatchPassword = await bcrypt.compare(password, hashPassword)
        if (isMatchPassword) {
            const future30Days = new Date();
            future30Days.setDate(future30Days.getDate() + 30);
            foundUser.tokenKey = require('key-creator').generate()
            foundUser.expireDate = future30Days
            await foundUser.save()
            foundUser.hashPassword = "not show"
            res.status(200).json({
                result: 'SC',
                data: foundUser
            })
            return
        } else {
            res.json({
                result: 'failed',
                data: {},
                message: 'Email/password is incorrect'
            })
            return
        }
    } catch (exception) {
        res.status(500).json({
            result: 'failed',
            data: {},
            message: `Error details: ${exception.toString()}`,
            errors: []
        })
    }
})

module.exports = router