package com.gjohnson.homesite.database;

public class Join {

    private Entity joinEntity;
    private Condition condition;
    private Entity parentEntity;
    private boolean leftJoin;

    public Join() {

    }

    public Join(Entity joinEntity, Condition condition, Entity parentEntity, boolean isLeftJoin) {
        this.joinEntity = joinEntity;
        this.condition = condition;
        this.parentEntity = parentEntity;
        this.leftJoin = isLeftJoin;
    }

    public Join(Entity joinEntity, Condition condition, Entity parentEntity) {
        this(joinEntity, condition, parentEntity, false);
    }

    public Entity getJoinEntity() {
        return joinEntity;
    }

    public void setJoinEntity(Entity joinEntity) {
        this.joinEntity = joinEntity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Entity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public boolean isLeftJoin() {
        return leftJoin;
    }

    public void setLeftJoin(boolean leftJoin) {
        this.leftJoin = leftJoin;
    }
}
