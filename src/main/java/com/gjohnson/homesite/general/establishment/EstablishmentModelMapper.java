package com.gjohnson.homesite.general.establishment;

import com.gjohnson.homesite.database.ColumnMapping;
import com.gjohnson.homesite.database.DataModelMapper;
import com.gjohnson.homesite.general.address.AddressModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstablishmentModelMapper extends DataModelMapper<Establishment> {

    public static final EstablishmentModelMapper MAPPER = new EstablishmentModelMapper();

    private static final String TABLE_NAME = "establishment";
    private static final String DEFAULT_TABLE_ALIAS = "est";
    private static final List<ColumnMapping> COLUMN_MAPPINGS = List.of(
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "establishment_id", Integer.class, null, true),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "name", String.class, Establishment::setName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "sub_name", String.class, Establishment::setSubName),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "website", String.class, Establishment::setWebsite),
            new ColumnMapping<>(DEFAULT_TABLE_ALIAS, "address_id", Integer.class, null)
    );

    public EstablishmentModelMapper() {
        super(Establishment.class);
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
    public Establishment mapRow(ResultSet resultSet, int i) throws SQLException {
        final Establishment establishment = super.mapRow(resultSet, i);
        if (establishment != null) {
            AddressModelMapper.MAPPER.mapRowIfPresent(resultSet, i).ifPresent(establishment::setAddress);
        }
        return establishment;
    }
}
