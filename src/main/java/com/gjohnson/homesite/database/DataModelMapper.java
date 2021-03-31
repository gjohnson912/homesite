package com.gjohnson.homesite.database;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class DataModelMapper<T> implements RowMapper<T> {

    private Class<T> clazz;

    public DataModelMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract String getTableName();

    public abstract String getDefaultTableAlias();

    public abstract List<ColumnMapping> getColumnMappings();

    public List<String> getColumnNames() {
        return Optional.ofNullable(getColumnMappings())
                .map(columnMappings -> columnMappings.stream().map(ColumnMapping::getColumnName).collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    public String getColumnLabel(String columnName) {
        return getDefaultTableAlias() + "_" + columnName;
    }

    public final Class<T> getSupportedClass() {
        return this.clazz;
    }

    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            final T model = this.clazz.getDeclaredConstructor().newInstance();
            for (ColumnMapping mapping : getColumnMappings()) {
                if (mapping.getSetModelProperty() != null) {
                    if (mapping.getModelType().isAssignableFrom(byte[].class)) {
                        mapping.getSetModelProperty().accept(model, resultSet.getBytes(mapping.getDefaultColumnLabel()));
                    } else {
                        mapping.getSetModelProperty().accept(model,
                                resultSet.getObject(mapping.getDefaultColumnLabel(), mapping.getModelType()));
                    }
                }
            }
            return model;

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Unable to create necessary model object.", e);
        }
    }

    public Optional<T> mapRowIfPresent(ResultSet resultSet, int i) throws SQLException {
        if (resultSetIncludesThis(resultSet.getMetaData())) {
            return Optional.ofNullable(mapRow(resultSet, i));
        } else {
            return Optional.empty();
        }
    }

    public boolean resultSetIncludesThis(ResultSetMetaData resultSetMetaData) throws SQLException {
        final Optional<ColumnMapping> columnMapping = getColumnMappings().stream()
                .filter(ColumnMapping::isTableSelectedIndicator)
                .findFirst();
        if (columnMapping.isPresent()) {
            for (int j = 1; j <= resultSetMetaData.getColumnCount(); j++) {
                if (getColumnLabel(columnMapping.get().getColumnName()).equals(resultSetMetaData.getColumnLabel(j))) {
                    return true;
                }
            }
        }

        return false;
    }
}
