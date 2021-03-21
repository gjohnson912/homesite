package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.database.BaseRepository;
import com.gjohnson.homesite.database.Condition;
import com.gjohnson.homesite.database.DataModelMapperConfiguration;
import com.gjohnson.homesite.database.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationRepository extends BaseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EducationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataModelMapperConfiguration mapperConfiguration) {
        super(mapperConfiguration);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Education> getEducationList() {
        final Query query = new Query()
                .selectAll()
                .from(Education.class)
                .join(EducationType.class, Condition.equals("education_type_id", "education_type_id"), Education.class);
        return this.namedParameterJdbcTemplate.query(build(query), getMapper(Education.class));
    }

    public List<Education> getEducationListByContact(Integer contactId) {
        final Query query = new Query()
                .selectAll()
                .from(Education.class)
                .join(EducationType.class, Condition.equals("education_type_id", "education_type_id"), Education.class)
                .where(Education.class, Condition.equals("contact_id", ":contact_id"));
        return this.namedParameterJdbcTemplate.query(build(query), new MapSqlParameterSource("contact_id", contactId), getMapper(Education.class));
    }
}
