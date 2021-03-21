package com.gjohnson.homesite.general.contact;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class ContactModelMapper extends DataModelMapper<Contact> {

    public static final ContactModelMapper MAPPER = new ContactModelMapper();

    private static final String TABLE_NAME = "contact";
    private static final String DEFAULT_TABLE_ALIAS = "c";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "contact_id", Integer.class, Contact::setContactId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "first_name", String.class, Contact::setFirstName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "last_name", String.class, Contact::setLastName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "email_address", String.class, Contact::setEmailAddress),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "system_wide_default", Boolean.class, Contact::setSystemWideDefault),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public ContactModelMapper() {
        super(Contact.class);
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
