package com.gjohnson.homesite.general.contact.resume;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class ResumeModelMapper extends DataModelMapper<Resume> {

    public static final ResumeModelMapper MAPPER = new ResumeModelMapper();

    private static final String TABLE_NAME = "resume";
    private static final String DEFAULT_TABLE_ALIAS = "r";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "resume_id", Integer.class, Resume::setResumeId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "document", byte[].class, Resume::setDocument),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "document_name", String.class, Resume::setDocumentName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "file_extension", String.class, Resume::setFileExtension),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "file_type", String.class, Resume::setFileType),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public ResumeModelMapper() {
        super(Resume.class);
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
