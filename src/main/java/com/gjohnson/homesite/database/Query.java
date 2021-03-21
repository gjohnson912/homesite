package com.gjohnson.homesite.database;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Query {

    private boolean selectAll = false;
    private final List<Entity> selectEntities = new ArrayList<>();
    private Entity fromEntity;
    private final List<Join> joins = new ArrayList<>();
    private final List<Where> whereDefinitions = new ArrayList<>();

    public Query selectAll() {
        this.selectAll = true;
        return this;
    }

    public Query select(Class<?> modelClass, Class<?>... modelClasses) {
        if (this.selectAll) {
            throw new UnsupportedOperationException("Cannot specify specific items to select when selectAll was indicated.");
        }
        selectEntities.add(new Entity(modelClass));
        if (modelClasses != null) {
            selectEntities.addAll(Stream.of(modelClasses).map(Entity::new).collect(Collectors.toList()));
        }
        return this;
    }

    public Query select(Entity entity, Entity... entities) {
        if (this.selectAll) {
            throw new UnsupportedOperationException("Cannot specify specific items to select when selectAll was indicated.");
        }
        selectEntities.add(entity);
        if (entities != null) {
            selectEntities.addAll(Arrays.asList(entities));
        }
        return this;
    }

    public Query from(Class<?> modelClass) {
        return from(new Entity(modelClass));
    }

    public Query from(Class<?> modelClass, String alias) {
        return from(new Entity(modelClass, alias));
    }

    public Query from(Entity entity) {
        this.fromEntity = entity;
        if (this.selectAll) {
            this.selectEntities.add(entity);
        }
        return this;
    }

    public Query join(Join joinDefinition) {
        this.joins.add(joinDefinition);
        if (this.selectAll) {
            this.selectEntities.add(joinDefinition.getJoinEntity());
        }
        return this;
    }

    public Query join(Entity joinEntity, Condition condition, Entity parentEntity) {
        return this.join(new Join(joinEntity, condition, parentEntity));
    }

    public Query join(Class<?> joinClass, Condition condition, Class<?> parentClass) {
        return this.join(new Entity(joinClass), condition, new Entity(parentClass));
    }

    public Query join(Class<?> joinClass, String joinAlias, Condition condition, Class<?> parentClass) {
        return this.join(new Entity(joinClass, joinAlias), condition, new Entity(parentClass));
    }

    public Query join(Class<?> joinClass, Condition condition, Class<?> parentClass, String parentAlias) {
        return this.join(new Entity(joinClass), condition, new Entity(parentClass, parentAlias));
    }

    public Query join(Class<?> joinClass, String joinAlias, Condition condition, Class<?> parentClass, String parentAlias) {
        return this.join(new Entity(joinClass, joinAlias), condition, new Entity(parentClass, parentAlias));
    }

    public Query join(Class<?> joinClass, Condition condition, Entity parentEntity) {
        return this.join(new Entity(joinClass), condition, parentEntity);
    }

    public Query join(Class<?> joinClass, String joinAlias, Condition condition, Entity parentEntity) {
        return this.join(new Entity(joinClass, joinAlias), condition, parentEntity);
    }

    public Query join(Entity joinEntity, Condition condition, Class<?> parentClass) {
        return this.join(joinEntity, condition, new Entity(parentClass));
    }

    public Query join(Entity joinEntity, Condition condition, Class<?> parentClass, String parentAlias) {
        return this.join(joinEntity, condition, new Entity(parentClass, parentAlias));
    }

    public Query where(Where whereClause) {
        this.whereDefinitions.add(whereClause);
        return this;
    }

    public Query where(Entity entity, Condition condition) {
        return this.where(new Where(entity, condition));
    }

    public Query where(Class<?> whereClass, Condition condition) {
        return this.where(new Entity(whereClass), condition);
    }

    protected List<Entity> getSelectEntities() {
        return this.selectEntities;
    }

    protected Entity getFromEntity() {
        return fromEntity;
    }

    protected List<Join> getJoins() {
        return joins;
    }

    protected List<Where> getWhereDefinitions() {
        return whereDefinitions;
    }
}
