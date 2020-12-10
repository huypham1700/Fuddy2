const { check, checkSchema } = require('express-validator');

const validateRegisterUser = () => {
    return [
        check('email', 'Invalid does not Empty').not().isEmpty(),
        check('email', 'Invalid email').isEmail(),
        //check('hashPassword', 'password more than 6 degits').isLength({ min: 6 }),
        // checkSchema( {
        //     "userType": {
        //       in: 'body',
        //       matches: {
        //         options: [/\b(?:default|google|facebook)\b/],
        //         errorMessage: "Invalid userType, must be: facebook, default, google"
        //       }
        //     }
        //   }),    
    ];
}

const validateLogin = () => {
    return [
        check('email', 'Invalid does not Empty').not().isEmpty(),
        check('email', 'Invalid email').isEmail(),
    ];
}


const validateFoodNew = () => {
    return [
        check('name', 'Invalid does not Empty').not().isEmpty(),
        //check('shopId', 'Invalid email').not().isEmpty(),
    ];
}

const validateString = () => {
    return [
        //check('search', 'Invalid does not Empty').not().isEmpty(),
        //check('shopId', 'Invalid email').not().isEmpty(),
    ];
}
const validatePhoneToken = () => {
    return [
        check('userId', 'insert phoneToken').not().isEmpty(),
        //  check('userId', 'Invalid email').not(),
    ];
}


module.exports = {
    validateRegisterUser: validateRegisterUser,
    validateLogin: validateLogin,
    validateFoodNew: validateFoodNew,
    validateString: validateString,
    validatePhoneToken: validatePhoneToken
}