package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class EducationTypeModelMapper extends DataModelMapper<EducationType> {

    public static final EducationTypeModelMapper MAPPER = new EducationTypeModelMapper();

    private static final String TABLE_NAME = "education_type";
    private static final String DEFAULT_TABLE_ALIAS = "et";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "education_type_id", Integer.class, EducationType::setEducationTypeId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "name", String.class, EducationType::setName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "description", String.class, EducationType::setDescription),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public EducationTypeModelMapper() {
        super(EducationType.class);
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
}
