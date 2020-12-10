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
    name: {
      type: DataTypes.STRING(65535),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "name"
    },
    imageId: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "imageId",
      references: {
        key: "id",
        model: "Image_model"
      }
    }
  };
  const options = {
    tableName: "Category",
    comment: "",
    timestamps: false,
    indexes: []
  };
  const CategoryModel = sequelize.define("Category_model", attributes, options);
  return CategoryModel;
};