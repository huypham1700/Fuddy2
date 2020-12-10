const {
  DataTypes
} = require('sequelize');

module.exports = sequelize => {
  const attributes = {
    name: {
      type: nvarchar(128),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "name"
    },
    principal_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "principal_id"
    },
    diagram_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: true,
      autoIncrement: true,
      comment: null,
      field: "diagram_id"
    },
    version: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "version"
    },
    definition: {
      type: varbinary,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "definition"
    }
  };
  const options = {
    tableName: "sysdiagrams",
    comment: "",
    indexes: [{
      name: "UK_principal_name",
      unique: true,
      fields: ["principal_id", "name"]
    }]
  };
  const SysdiagramsModel = sequelize.define("sysdiagrams_model", attributes, options);
  return SysdiagramsModel;
};