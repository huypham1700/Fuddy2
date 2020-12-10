var express = require('express');
var router = express.Router()
const { checkToken } = require('../helpers/TokenCheck')
const { sequelize } = require('../databases/database')
const ImageModel = require('../models/Image')(sequelize)
const CategoryModel = require('../models/Category')(sequelize)
const FoodModel = require('../models/Food')(sequelize)
const StoreModel = require('../models/Store')(sequelize)
const{Op}=require("sequelize");
const Store = require('../models/Store');

ImageModel.hasMany(CategoryModel, {foreignKey: 'imageId'})
CategoryModel.belongsTo(ImageModel, { foreignKey: 'imageId'})

router.post('/getAllCategory', async(req, res) =>{
    const {tokenkey} = req.headers
    const isValidToken = await checkToken({tokenkey})
    if(isValidToken == false){
        res.json({
            result: "TK01",
            data: null
        })
        return
    }

    try{
        let foundCategory= await CategoryModel.findAll({
            
            include:[{
                model: ImageModel,
                as: "Image_model",
                attributes: ['imageUrl']
            }]
        })
        res.json({
            result: 'SC',
            data: foundCategory 
        })
    }catch (exception){
        res.status(500).json({
            result: 'E500',
            data : null,
            message: `Error details: ${exception.toString()}`
        })
    }
})

module.exports = router