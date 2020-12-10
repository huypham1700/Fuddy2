const {
  DataTypes
} = require('sequelize');

module.exports = sequelize => {
  const attributes = {
    id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: true,
      autoIncrement: true,
      comment: null,
      field: "id"
    },
    email: {
      type: DataTypes.STRING(300),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "email",
      unique: "UC_User"
    },
    hashPassword: {
      type: DataTypes.STRING(300),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "hashPassword"
    },
    name: {
      type: DataTypes.TEXT,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "name"
    },
    imageId: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: "((1))",
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "imageId",
      references: {
        key: "id",
        model: "Image_model"
      }
    },
    phoneNumber: {
      type: DataTypes.STRING(50),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "phoneNumber"
    },
    address: {
      type: DataTypes.TEXT,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "address"
    },
    userType: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "userType"
    },
    tokenKey: {
      type: DataTypes.STRING(300),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "tokenKey"
    },
    expireDate: {
      type: DataTypes.DATEONLY,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "expireDate"
    }
  };
  const options = {
    tableName: "User",
    comment: "",
    timestamps: false,
    indexes: []
  };
  const UserModel = sequelize.define("User_model", attributes, options);
  return UserModel;
};