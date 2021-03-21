package com.gjohnson.homesite.database;

public class Where {

    private Entity entity;
    private Condition condition;

    public Where() {
    }

    public Where(Entity entity, Condition condition) {
        this.entity = entity;
        this.condition = condition;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
