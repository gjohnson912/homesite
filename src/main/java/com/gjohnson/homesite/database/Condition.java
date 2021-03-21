package com.gjohnson.homesite.database;

import java.util.Optional;

public class Condition {

    private String leftSide;
    private String operator;
    private String rightSide;

    public Condition(String leftSide, String operator, String rightSide) {
        this.leftSide = leftSide;
        this.operator = operator;
        this.rightSide = rightSide;
    }

    public Condition(String leftSide, Operator operator, String rightSide) {
        this.leftSide = leftSide;
        this.operator = Optional.ofNullable(operator).map(Operator::getSqlOperator).orElseThrow();
        this.rightSide = rightSide;
    }

    public static Condition equals(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.EQUALS, rightSide);
    }

    public static Condition lessThan(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.LESS_THAN, rightSide);
    }

    public static Condition lessThanOrEqual(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.LESS_THAN_OR_EQUAL, rightSide);
    }

    public static Condition greaterThan(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.GREATER_THAN, rightSide);
    }

    public static Condition greaterThanOrEqual(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.GREATER_THAN_OR_EQUAL, rightSide);
    }

    public static Condition like(String leftSide, String rightSide) {
        return new Condition(leftSide, Operator.LIKE, rightSide);
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRightSide() {
        return rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }
}
