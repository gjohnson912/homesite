package com.gjohnson.homesite.database;

public class BaseRepository {

    private final DataModelMapperConfiguration configuration;
    private final SqlBuilder sqlBuilder;

    public BaseRepository(DataModelMapperConfiguration configuration) {
        this.configuration = configuration;
        this.sqlBuilder = new SqlBuilder(this.configuration);
    }

    public <T> DataModelMapper<T> getMapper(Class<T> modelClass) {
        return (DataModelMapper<T>) this.configuration.getModelToMapperMap().get(modelClass);
    }

    public String build(Query query) {
        return this.sqlBuilder.build(query);
    }
}
