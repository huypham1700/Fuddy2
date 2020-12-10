const MAXMUM_FILE_SIZE = 50
const PORT = 3000

//const HOSTNAME = '192.168.1.142'
//10.1.35.148
//sequelize-automate -t js -h VIETTQ  -d AndroidLibrary -u sa -p Matkhaumoi1 -P 1433  -e mssql -o models 
const HOSTNAME = 'DESKTOP-AP536LU'
const DB_PORT = 1433
const DB_NAME = "VietIS"
const DB_USERNAME = 'sa'
const DB_PASSWORD = '123456'

module.exports = {
    MAXMUM_FILE_SIZE,
    PORT,
    HOSTNAME,
    DB_PORT,
    DB_NAME,
    DB_USERNAME,
    DB_PASSWORD
}