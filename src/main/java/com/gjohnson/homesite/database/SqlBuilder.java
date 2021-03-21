package com.gjohnson.homesite.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SqlBuilder {

    private static final Logger logger = LogManager.getLogger();

    private final DataModelMapperConfiguration configuration;

    public SqlBuilder(DataModelMapperConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * This is the heart of the query API that is built up by the various sql objects. It takes in the {@link Query} and
     * builds the straight SQL string.
     *
     * @param query the fully populated {@link Query}
     * @return a String with the full sql string to be submitted to the database
     */
    public String build(Query query) throws InvalidQueryException {
        if (query == null) {
            throw new InvalidQueryException("Query provided was null.");
        }
        final var sqlQuery = new StringBuilder()
                .append(this.buildSelectClause(query.getSelectEntities()).orElseThrow(InvalidQueryException::selectException))
                .append(this.buildFromClause(query.getFromEntity()).orElseThrow(InvalidQueryException::fromException))
                .append(this.buildJoinClause(query.getJoins()).orElse(""))
                .append(this.buildWhereClause(query.getWhereDefinitions()).orElse(""));
        logger.info("Query: {}", sqlQuery.toString());
        return sqlQuery.toString();
    }

    private Optional<String> buildSelectClause(List<Entity> selectEntities) {
        if (selectEntities == null || selectEntities.size() == 0) {
            return Optional.empty();
        }
        final StringBuilder selectClause = new StringBuilder("SELECT ");
        boolean isFirstSelectEntity = true;
        for (Entity entity : selectEntities) {
            if (!isFirstSelectEntity) {
                selectClause.append(", ");
            }
            selectClause.append(this.configuration.getModelToMapperMap().get(entity.getDataModelClass())
                    .getColumnMappings()
                    .stream()
                    .map(columnMapping -> entity.isUseDefaultAlias()
                            ? columnMapping.getDefaultFullSelectColumn()
                            : columnMapping.getFullSelectColumn(entity.getTableAlias()))
                    .collect(Collectors.joining(", ")));
            isFirstSelectEntity = false;
        }

        return Optional.of(selectClause.toString());
    }

    private Optional<String> buildFromClause(Entity fromEntity) {
        if (fromEntity == null) {
            return Optional.empty();
        }
        final DataModelMapper<?> dataModelMapper = this.configuration.getModelToMapperMap().get(fromEntity.getDataModelClass());
        final String tableAlias = fromEntity.isUseDefaultAlias()
                ? dataModelMapper.getDefaultTableAlias()
                : fromEntity.getTableAlias();
        return Optional.of(" FROM " + dataModelMapper.getTableName() + " " + tableAlias);
    }

    private Optional<String> buildJoinClause(List<Join> joins) {
        if (joins == null) {
            return Optional.empty();
        }
        var joinClause = new StringBuilder();
        for (Join join : joins) {
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
                joinClause.append(" LEFT ");
            }
            joinClause.append(" JOIN ")
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
        return Optional.of(joinClause.toString());
    }

    private Optional<String> buildWhereClause(List<Where> whereDefinitions) {
        if (whereDefinitions == null || whereDefinitions.size() == 0) {
            return Optional.empty();
        }

        var whereClause = new StringBuilder(" WHERE ");
        for (Where where : whereDefinitions) {
            final DataModelMapper<?> dataModelMapper = this.configuration.getModelToMapperMap().get(
                    where.getEntity().getDataModelClass());
            final String tableAlias = where.getEntity().isUseDefaultAlias()
                    ? dataModelMapper.getDefaultTableAlias()
                    : where.getEntity().getTableAlias();
            whereClause.append(tableAlias)
                    .append(".")
                    .append(where.getCondition().getLeftSide())
                    .append(where.getCondition().getOperator())
                    .append(where.getCondition().getRightSide());
        }
        return Optional.of(whereClause.toString());
    }
}
