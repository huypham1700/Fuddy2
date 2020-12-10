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
    userId: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "userId",
      references: {
        key: "id",
        model: "User_model"
      }
    },
    tokenKey: {
      type: DataTypes.TEXT,
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
      defaultValue: "(getdate())",
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "createAt"
    }
  };
  const options = {
    tableName: "UserToken",
    comment: "",
    timestamps: false,
    indexes: []
  };
  const UserTokenModel = sequelize.define("UserToken_model", attributes, options);
  return UserTokenModel;
};