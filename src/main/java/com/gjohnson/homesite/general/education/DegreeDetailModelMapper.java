package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DegreeDetailModelMapper extends DataModelMapper<DegreeDetail> {

    public static final DegreeDetailModelMapper MAPPER = new DegreeDetailModelMapper();

    private static final String TABLE_NAME = "degree_detail";
    private static final String DEFAULT_TABLE_ALIAS = "dd";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "degree_detail_id", Integer.class, DegreeDetail::setDegreeDetailId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "education_id", Integer.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "degree_type_id", Integer.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "major", String.class, DegreeDetail::setMajor),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "minor", String.class, DegreeDetail::setMinor),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public DegreeDetailModelMapper() {
        super(DegreeDetail.class);
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
    public DegreeDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        final DegreeDetail degreeDetail = super.mapRow(resultSet, i);
        if (degreeDetail != null) {
            DegreeTypeModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(degreeDetail::setDegreeType);
        }
        return degreeDetail;
    }
}
