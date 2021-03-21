package com.gjohnson.homesite.database;

import java.util.Objects;

public class Entity {

    private final Class<?> dataModelClass;
    private final String tableAlias;
    private final boolean useDefaultAlias;

    public Entity(Class<?> dataModelClass) {
        this.dataModelClass = Objects.requireNonNull(dataModelClass);
        this.tableAlias = null;
        this.useDefaultAlias = true;
    }

    public Entity(Class<?> dataModelClass, String tableAlias) {
        this.dataModelClass = Objects.requireNonNull(dataModelClass);
        this.tableAlias = Objects.requireNonNull(tableAlias);
        this.useDefaultAlias = false;
    }

    public Class<?> getDataModelClass() {
        return dataModelClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public boolean isUseDefaultAlias() {
        return useDefaultAlias;
    }
}
