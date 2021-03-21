package com.gjohnson.homesite.general.employment;

import com.gjohnson.homesite.database.BaseRepository;
import com.gjohnson.homesite.database.Condition;
import com.gjohnson.homesite.database.DataModelMapperConfiguration;
import com.gjohnson.homesite.database.Query;
import com.gjohnson.homesite.general.address.Address;
import com.gjohnson.homesite.general.establishment.Establishment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmploymentRepository extends BaseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EmploymentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataModelMapperConfiguration mapperConfiguration) {
        super(mapperConfiguration);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Employment> getEmploymentList() {
        final Query query = new Query()
                .selectAll()
                .from(Employment.class)
                .join(Establishment.class, Condition.equals("establishment_id", "establishment_id"), Employment.class)
                .join(Address.class, Condition.equals("address_id", "address_id"), Establishment.class);
        return this.namedParameterJdbcTemplate.query(build(query), getMapper(Employment.class));
    }

    public List<Employment> getEmploymentListByContact(Integer contactId) {
        final Query query = new Query()
                .selectAll()
                .from(Employment.class)
                .join(Establishment.class, Condition.equals("establishment_id", "establishment_id"), Employment.class)
                .join(Address.class, Condition.equals("address_id", "address_id"), Establishment.class)
                .where(Employment.class, Condition.equals("contact_id", ":contact_id"));
        return this.namedParameterJdbcTemplate.query(build(query), new MapSqlParameterSource("contact_id", contactId), getMapper(Employment.class));
    }
}
