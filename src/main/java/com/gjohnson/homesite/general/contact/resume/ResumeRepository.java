package com.gjohnson.homesite.general.contact.resume;

import com.gjohnson.homesite.database.BaseRepository;
import com.gjohnson.homesite.database.Condition;
import com.gjohnson.homesite.database.DataModelMapperConfiguration;
import com.gjohnson.homesite.database.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ResumeRepository extends BaseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ResumeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            DataModelMapperConfiguration configuration) {
        super(configuration);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Resume> retrieveByContactId(Integer contactId) {
        final Query query = new Query()
                .selectAll()
                .from(Resume.class)
                .where(Resume.class, Condition.equals("contact_id", ":contact_id"));
        final var resumes = this.namedParameterJdbcTemplate.query(build(query),
                new MapSqlParameterSource("contact_id", contactId), getMapper(Resume.class));

        if (resumes.size() > 1) {
            throw new IncorrectResultSizeDataAccessException("Too many default contacts found.", 1, resumes.size());
        }

        return resumes.size() == 1 ? Optional.of(resumes.get(0)) : Optional.empty();
    }

    public Resume createResume(Integer contactId, Resume resume) {
        final var params = new MapSqlParameterSource("contact_id", contactId)
                .addValue("document_name", resume.getDocumentName())
                .addValue("document", resume.getDocument())
                .addValue("file_type", resume.getFileType())
                .addValue("file_extension", resume.getFileExtension());
        this.namedParameterJdbcTemplate.update(
                "INSERT INTO resume (contact_id, document_name, document, file_type, file_extension)"
                    + " VALUES (:contact_id, :document_name, :document, :file_type, :file_extension)",
                params);

        return this.retrieveByContactId(contactId).orElseThrow();
    }
}
