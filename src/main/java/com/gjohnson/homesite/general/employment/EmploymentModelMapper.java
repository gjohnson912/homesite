package com.gjohnson.homesite.general.employment;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;
import com.gjohnson.homesite.general.establishment.EstablishmentModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EmploymentModelMapper extends DataModelMapper<Employment> {

    public static final EmploymentModelMapper MAPPER = new EmploymentModelMapper();

    private static final String TABLE_NAME = "employment";
    private static final String DEFAULT_TABLE_ALIAS = "emp";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "employment_id", Integer.class, Employment::setEmploymentId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "establishment_id", Integer.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "start_date", LocalDate.class, Employment::setStartDate),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "end_date", LocalDate.class, Employment::setEndDate),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "role", String.class, Employment::setRole),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public EmploymentModelMapper() {
        super(Employment.class);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getDefaultTableAlias() {
        return DEFAULT_TABLE_ALIAS;
    }

    @Override
    public List<ColumnMapping> getColumnMappings() {
        return COLUMN_MAPPINGS;
    }

    @Override
    public Employment mapRow(ResultSet resultSet, int i) throws SQLException {
        final Employment employment = super.mapRow(resultSet, i);
        if (employment != null) {
            EstablishmentModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(employment::setCompany);
        }
        return employment;
    }
}
