var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const StoreModel = require('../models/Store')(sequelize)
const ImageModel = require('../models/Image')(sequelize)
const RatingModel = require('../models/RatingStore')(sequelize)
const UserModel = require('../models/User')(sequelize)
const CommentStoreModel = require('../models/StoreComment')(sequelize)
const { validateString } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op, INTEGER } = require("sequelize");

ImageModel.hasMany(StoreModel, { foreignKey: 'imageId' })
StoreModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

StoreModel.hasMany(RatingModel, { foreignKey: 'storeId' })
RatingModel.belongsTo(StoreModel, { foreignKey: 'storeId' })

ImageModel.hasMany(UserModel, { foreignKey: 'imageId' })
UserModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

CommentStoreModel.belongsTo(UserModel, { foreignKey: 'userId' })
UserModel.hasMany(CommentStoreModel, { foreignKey: 'userId' })

/**
 * URL: http://localhost:3000/store/search
 */
router.post('/search', validateString(), async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01"
        })
        return;
    }
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
    const { offset, limit } = req.body
    try {
        let foundStore = await StoreModel.findAll({
        
            include: [{
                    model: ImageModel,
                    as: "Image_model",
                    attributes: ['imageURL']
                },
                {
                    model: RatingModel,
                    as: "RatingStore_models",
                }
            ],
            order: StoreModel.id
            ,
            limit: limit,
            offset: offset
        })

        res.json({
            result: 'SC',
            data: foundStore,
            message: 'Find successfully'
        })
    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
})



/**
 * URL: http://localhost:3000/store/detailStore
 */
router.post('/detailStore', async(req, res) => {
    //validate du lieu tu client gui len
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01",
            data: null
        })
        return;
    }
    const { id } = req.body
    try {
        let foundStore = await StoreModel.findAll({
            where: {
                id: {
                    [Op.eq]: id
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageURL']
            }]
        })
        if (foundStore.length == 0) {
            res.json({
                result: 'ST01',
                data: null
            })
            return
        }
        let result5 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 5
                }
            }
        })
        let result4 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 4
                }
            }
        })
        let result3 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 3
                }
            }
        })
        let result2 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 2
                }
            }
        })
        let result1 = await RatingModel.findAndCountAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                },
                rating: {
                    [Op.eq]: 1
                }
            }
        })
        let newComment = await CommentStoreModel.findAll({
            where: {
                storeId: {
                    [Op.eq]: foundStore[0].id
                }
            },
            include: [{
                model: UserModel,
                as: "User_model",
                attributes: ['name'],
                include: [{
                    model: ImageModel,
                    as: "Image_model",
                    attributes: ['imageURL']
                }]
            }],
            limit: 3,
            order: [
                ['id', 'DESC']
            ]
        })

        res.json({
            result: 'SC',
            data: {
                foundStore,
                result5,
                result4,
                result3,
                result2,
                result1,
                newComment
            }
        })

    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            data: null,
            message: `Error details: ${exception.toString()}`,
        })
    }
})

module.exports = router