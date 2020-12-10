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
    tokenKey: {
      type: DataTypes.STRING(65535),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "tokenKey"
    },
    createAt: {
      type: DataTypes.DATEONLY,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "createAt"
    }
  };
  const options = {
    tableName: "PhoneToken",
    comment: "",
    timestamps: false,
    indexes: []
  };
  const PhoneTokenModel = sequelize.define("PhoneToken_model", attributes, options);
  return PhoneTokenModel;
};