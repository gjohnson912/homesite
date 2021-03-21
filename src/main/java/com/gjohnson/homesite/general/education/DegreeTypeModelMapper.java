package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class DegreeTypeModelMapper extends DataModelMapper<DegreeType> {

    public static final DegreeTypeModelMapper MAPPER = new DegreeTypeModelMapper();

    private static final String TABLE_NAME = "degree_type";
    private static final String DEFAULT_TABLE_ALIAS = "dt";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "degree_type_id", Integer.class, null, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "name", String.class, DegreeType::setName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "acronym", String.class, DegreeType::setAcronym),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "display_name", String.class, DegreeType::setDisplayName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "description", String.class, EducationType::setDescription),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public DegreeTypeModelMapper() {
        super(DegreeType.class);
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
