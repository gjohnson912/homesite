package com.gjohnson.homesite.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.stream.Collectors;

public class SqlBuilder {

    private static final Log LOGGER = LogFactory.getLog(SqlBuilder.class);

    private final DataModelMapperConfiguration configuration;

    public SqlBuilder(DataModelMapperConfiguration configuration) {
        this.configuration = configuration;
    }

    public String build(Query query) {
        final StringBuilder sqlQuery = new StringBuilder("SELECT ");
        if (query != null) {
            if (query.getSelectEntities() != null && query.getSelectEntities().size() > 0) {
                boolean isFirstSelectEntity = true;
                for (Entity entity : query.getSelectEntities()) {
                    if (!isFirstSelectEntity) {
                        sqlQuery.append(", ");
                    }
                    sqlQuery.append(this.configuration.getModelToMapperMap().get(entity.getDataModelClass())
                            .getColumnMappings()
                            .stream()
                            .map(columnMapping -> entity.isUseDefaultAlias()
                                    ? columnMapping.getDefaultFullSelectColumn()
                                    : columnMapping.getFullSelectColumn(entity.getTableAlias()))
                            .collect(Collectors.joining(", ")));
                    isFirstSelectEntity = false;
                }
            }
            if (query.getFromEntity() != null) {
                final DataModelMapper<?> dataModelMapper = this.configuration.getModelToMapperMap().get(
                        query.getFromEntity().getDataModelClass());
                final String tableAlias = query.getFromEntity().isUseDefaultAlias()
                        ? dataModelMapper.getDefaultTableAlias()
                        : query.getFromEntity().getTableAlias();
                sqlQuery.append(" FROM ").append(dataModelMapper.getTableName()).append(" ").append(tableAlias);
            }
            if (query.getJoins() != null) {
                for (Join join : query.getJoins()) {
                    final DataModelMapper<?> dataModelMapper = this.configuration.getModelToMapperMap().get(
                            join.getJoinEntity().getDataModelClass());
                    final String joinTableAlias = join.getJoinEntity().isUseDefaultAlias()
                            ? dataModelMapper.getDefaultTableAlias()
                            : join.getJoinEntity().getTableAlias();
                    final DataModelMapper<?> parentModelMapper = this.configuration.getModelToMapperMap().get(
                            join.getParentEntity().getDataModelClass());
                    final String parentTableAlias = join.getParentEntity().isUseDefaultAlias()
                            ? parentModelMapper.getDefaultTableAlias()
                            : join.getParentEntity().getTableAlias();
                    if (join.isLeftJoin()) {
                        sqlQuery.append(" LEFT ");
                    }
                    sqlQuery.append(" JOIN ")
                            .append(dataModelMapper.getTableName())
                            .append(" ")
                            .append(joinTableAlias)
                            .append(" ON ")
                            .append(joinTableAlias)
                            .append(".")
                            .append(join.getCondition().getLeftSide())
                            .append(join.getCondition().getOperator())
                            .append(parentTableAlias)
                            .append(".")
                            .append(join.getCondition().getRightSide());
                }
            }
            if (query.getWhereDefinitions() != null && query.getWhereDefinitions().size() > 0) {
                sqlQuery.append(" WHERE ");
                for (Where where : query.getWhereDefinitions()) {
                    final DataModelMapper<?> dataModelMapper = this.configuration.getModelToMapperMap().get(
                            where.getEntity().getDataModelClass());
                    final String tableAlias = where.getEntity().isUseDefaultAlias()
                            ? dataModelMapper.getDefaultTableAlias()
                            : where.getEntity().getTableAlias();
                    sqlQuery.append(tableAlias)
                            .append(".")
                            .append(where.getCondition().getLeftSide())
                            .append(where.getCondition().getOperator())
                            .append(where.getCondition().getRightSide());
                }
            }
        }
        LOGGER.info("Query: " + sqlQuery.toString());
        return sqlQuery.toString();
    }
}
