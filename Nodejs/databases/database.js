const { Sequelize } = require('sequelize')
const {
    MAXMUM_FILE_SIZE,
    PORT,
    HOSTNAME,
    DB_PORT,
    DB_NAME,
    DB_USERNAME,
    DB_PASSWORD
} = require('../constants/constants')
    // timestamps: false,
Sequelize.DATE.prototype._stringify = function _stringify(date, options) {
    date = this._applyTimezone(date, options);

    // Z here means current timezone, _not_ UTC
    // return date.format('YYYY-MM-DD HH:mm:ss.SSS Z');
    return date.format('YYYY-MM-DD HH:mm:ss');
};
const sequelize = new Sequelize(
        DB_NAME,
        DB_USERNAME,
        DB_PASSWORD, {
            dialect: 'mssql',
            dialectOptions: {
                instanceName: 'SQLEXPRESS',
                requestTimeout: 30000
            },
            host: 'localhost',
            port: 1433,
            additional: {
                timestamps: false,

            }

        }
    )
    //sequelize-automate -t js -h DESKTOP-4SJNRQ6  -d VietIS -u sa -p 123456 -P 1433  -e mssql -o models 
sequelize.authenticate()
    .then(() => {
        console.log('Connection Sequelize successfully.');
    }).catch(err => {
        console.error('Sequelize connect database failed:', err);
    })

module.exports = {
    sequelize
}