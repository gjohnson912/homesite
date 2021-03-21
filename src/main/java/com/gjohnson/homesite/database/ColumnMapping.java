package com.gjohnson.homesite.database;

import java.util.function.BiConsumer;

public class ColumnMapping<T, I> {

    private String tableAlias;
    private String columnName;
    private Class<I> modelType;
    private BiConsumer<T, I> setModelProperty;
    private boolean tableSelectedIndicator;

    public ColumnMapping() {
    }

    public ColumnMapping(String tableAlias, String columnName, Class<I> modelType, BiConsumer<T, I> setModelProperty, boolean tableSelectedIndicator) {
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.modelType = modelType;
        this.setModelProperty = setModelProperty;
        this.tableSelectedIndicator = tableSelectedIndicator;
    }

    public ColumnMapping(String tableAlias, String columnName, Class<I> modelType, BiConsumer<T, I> setModelProperty) {
        this(tableAlias, columnName, modelType, setModelProperty, false);
    }

    public String getDefaultColumnLabel() {
        return this.getColumnLabel(this.tableAlias);
    }

    public String getColumnLabel(String alias) {
        return alias + "_" + this.columnName;
    }

    public String getDefaultFullSelectColumn() {
        return this.getFullSelectColumn(this.getTableAlias());
    }

    public String getFullSelectColumn(String alias) {
        return alias + "." + this.getColumnName() + " AS " + this.getColumnLabel(alias);
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class<I> getModelType() {
        return modelType;
    }

    public void setModelType(Class<I> modelType) {
        this.modelType = modelType;
    }

    public BiConsumer<T, I> getSetModelProperty() {
        return setModelProperty;
    }

    public void setSetModelProperty(BiConsumer<T, I> setModelProperty) {
        this.setModelProperty = setModelProperty;
    }

    public boolean isTableSelectedIndicator() {
        return tableSelectedIndicator;
    }

    public void setTableSelectedIndicator(boolean tableSelectedIndicator) {
        this.tableSelectedIndicator = tableSelectedIndicator;
    }
}
