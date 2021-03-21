package com.gjohnson.homesite.database;

import java.util.Map;

public class DataModelMapperConfiguration {

    private final Map<Class<?>, DataModelMapper<?>> modelToMapperMap;

    public DataModelMapperConfiguration(Map<Class<?>, DataModelMapper<?>> modelToMapperMap) {
        this.modelToMapperMap = modelToMapperMap;
    }

    public Map<Class<?>, DataModelMapper<?>> getModelToMapperMap() {
        return modelToMapperMap;
    }
}
