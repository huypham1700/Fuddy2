var express = require('express');
var router = express.Router();
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const FoodModel = require('../models/Food')(sequelize)
const ImageModel = require('../models/Image')(sequelize)
const RatingModel = require('../models/RatingStore')(sequelize)
const CategoryModel = require('../models/Category')(sequelize)
const StoreModel = require('../models/Store')(sequelize)
const UserModel = require('../models/User')(sequelize)
const CommentFoodModel = require('../models/FoodComment')(sequelize)
const { validateFoodNew } = require('../validations/validate')
const { validationResult } = require('express-validator')
const { Op } = require("sequelize");


ImageModel.hasMany(FoodModel, { foreignKey: 'imageId' })
FoodModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

StoreModel.hasMany(FoodModel, { foreignKey: 'storeId' })
FoodModel.belongsTo(StoreModel, { foreignKey: 'storeId' })

CategoryModel.hasMany(FoodModel, { foreignKey: 'catId' })
FoodModel.belongsTo(CategoryModel, { foreignKey: 'catId' })

FoodModel.hasMany(RatingModel, { foreignKey: 'storeId' })
RatingModel.belongsTo(FoodModel, { foreignKey: 'storeId' })

ImageModel.hasMany(UserModel, { foreignKey: 'imageId' })
UserModel.belongsTo(ImageModel, { foreignKey: 'imageId' })

CommentFoodModel.belongsTo(UserModel, { foreignKey: 'userId' })
UserModel.hasMany(CommentFoodModel, { foreignKey: 'userId' })


/**
 * URL: http://localhost:3000/foods/addnew
 */
router.post('/addnew', validateFoodNew(), async(req, res) => {
    //validate du lieu tu client gui len    
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.status(422).json({
            result: 'DT01',
        });
        return;
    }
    const { storeId, name, catId, price, description, imageId } = req.body
    try {
        let foundFoods = await FoodModel.findAll({
            where: {
                name: {
                    [Op.like]: name
                },
                storeId: {
                    [Op.eq]: storeId
                }
            }
        })
        if (foundFoods.length > 0) {
            res.json({
                result: 'FD01'
            })
            return
        }

        let newFood = await FoodModel.create({
            storeId,
            name,
            catId,
            price,
            description,
            imageId
        })
        await newFood.save()

        res.json({
            result: 'SC',
            data: newFood
        })
    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
})


/**
 * URL: http://localhost:3000/foods/search
 */

router.post('/search', async(req, res) => {
    const { tokenkey } = req.headers
    const isValidToken = await checkToken({ tokenkey })
    if (isValidToken == false) {
        res.json({
            result: "TK01"
        })
        return;
    }
    const {search, offset, limit } = req.body
    try {
        let foundBooks = await FoodModel.findAll({
            where: {
                name: {
                    [Op.substring]: search
                }
            },
            include: [{
                    model: ImageModel,
                    as: "Image_model",
                    attributes: ['imageURL']
                },
                {
                    model: CategoryModel,
                    as: "Category_model",
                    attributes: ['name']
                },
                {
                    model: StoreModel,
                    as: "Store_model",
                    attributes: ['address']
                }
            ],
            limit: limit,
            offset: offset
        })
        res.json({
            result: "SC",
            data: foundBooks
        })
    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
});

/**
 * URL: http://localhost:3000/foods/search
 */

router.post('/getdetail', async(req, res) => {
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
        
        let foundBooks = await FoodModel.findAll({
            where: {
                id: {
                    [Op.eq]: id
                }
            },
            include: [{
                    model: ImageModel,
                    as: "Image_model",
                    attributes: ['imageURL']
                },
                {
                    model: CategoryModel,
                    as: "Category_model",
                    attributes: ['name']
                },
                {
                    model: StoreModel,
                    as: "Store_model",
                    attributes: ['address']
                }
            ]
        })
        
        res.json({
            result: "SC",
            data: foundBooks
        })
    } catch (exception) {
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
})

router.post('/getFoodByCatId', async(req,res) =>{
    const {tokenkey} = req.headers
    const isValidToken = await checkToken({tokenkey})
    if(!isValidToken){
        res.json({
            result: "TK01",
            data: null
        })
    }
    const { catId } =  req.body
    try{
        let foundFoods = await FoodModel.findAll({
            where: {
                catId: {
                    [Op.eq]: catId
                }
            },
            include: [{
                model: ImageModel,
                as: "Image_model",
                attributes: ["imageUrl"]
            },
            {
               model: StoreModel,
               as: "Store_model",
               attributes: ["id"] 
            }]
        })
        if (foundFoods.length == 0) {
            res.json({
                result: 'ST01',
                data: null
            })
            return
        }
        res.json({
            result: "SC",
            data: foundFoods
        })
    }catch (exception){
        res.status(500).json({
            result: 'E500',
            message: `Error details: ${exception.toString()}`
        })
    }
})
module.exports = router