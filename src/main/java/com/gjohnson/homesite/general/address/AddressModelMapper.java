package com.gjohnson.homesite.general.address;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class AddressModelMapper extends DataModelMapper<Address> {

    public static final AddressModelMapper MAPPER = new AddressModelMapper();

    private static final String TABLE_NAME = "address";
    private static final String DEFAULT_TABLE_ALIAS = "a";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "address_id", Integer.class, Address::setAddressId, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "primary_street", String.class, Address::setPrimaryStreet),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "secondary_street", String.class, Address::setSecondaryStreet),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "city", String.class, Address::setCity),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "state", String.class, Address::setState),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "zip_base", String.class, Address::setZipBase),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "zip_ext", String.class, Address::setZipExt),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "created_time", LocalDateTime.class, null),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "modified_time", LocalDateTime.class, null));

    public AddressModelMapper() {
        super(Address.class);
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
