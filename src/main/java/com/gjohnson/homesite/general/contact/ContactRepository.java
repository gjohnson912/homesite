package com.gjohnson.homesite.general.contact;

import com.gjohnson.homesite.database.BaseRepository;
import com.gjohnson.homesite.database.Condition;
import com.gjohnson.homesite.database.DataModelMapperConfiguration;
import com.gjohnson.homesite.database.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository extends BaseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataModelMapperConfiguration mapperConfiguration) {
        super(mapperConfiguration);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Contact> retrieveDefaultContact() {
        final Query query = new Query()
                .selectAll()
                .from(Contact.class)
                .where(Contact.class, Condition.equals("system_wide_default", ":system_wide_default"));
        final List<Contact> contacts = this.namedParameterJdbcTemplate.query(build(query),
                new MapSqlParameterSource("system_wide_default", true), ContactModelMapper.MAPPER);

        if (contacts.size() == 1) {
            return Optional.of(contacts.get(0));

        } else if (contacts.size() == 0) {
            return Optional.empty();

        } else {
            throw new IncorrectResultSizeDataAccessException("Too many default contacts found.", 1, contacts.size());
        }
    }
}
