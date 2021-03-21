package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;
import com.gjohnson.homesite.general.establishment.EstablishmentModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EducationModelMapper extends DataModelMapper<Education> {

    public static final EducationModelMapper MAPPER = new EducationModelMapper();

    private static final String TABLE_NAME = "education";
    private static final String DEFAULT_TABLE_ALIAS = "e";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "education_id", Integer.class, Education::setEducationId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "education_type_id", Integer.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "name", String.class, Education::setName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "sub_name", String.class, Education::setSubName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "completion_date", LocalDate.class, Education::setCompletionDate),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "establishment_id", Integer.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public EducationModelMapper() {
        super(Education.class);
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
    public Education mapRow(ResultSet resultSet, int i) throws SQLException {
        final Education education = super.mapRow(resultSet, i);
        if (education != null) {
            EducationTypeModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(education::setEducationType);
            EstablishmentModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(education::setInstitution);
            DegreeDetailModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(education::setDegreeDetail);
        }
        return education;
    }
}
