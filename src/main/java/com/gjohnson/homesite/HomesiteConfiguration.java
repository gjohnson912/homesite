package com.gjohnson.homesite;

import com.gjohnson.homesite.database.DataModelMapperConfiguration;
import com.gjohnson.homesite.general.address.AddressModelMapper;
import com.gjohnson.homesite.general.contact.ContactModelMapper;
import com.gjohnson.homesite.general.contact.resume.ResumeModelMapper;
import com.gjohnson.homesite.general.education.DegreeDetailModelMapper;
import com.gjohnson.homesite.general.education.DegreeTypeModelMapper;
import com.gjohnson.homesite.general.education.EducationModelMapper;
import com.gjohnson.homesite.general.education.EducationTypeModelMapper;
import com.gjohnson.homesite.general.employment.EmploymentModelMapper;
import com.gjohnson.homesite.general.establishment.EstablishmentModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class HomesiteConfiguration {

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataModelMapperConfiguration modelMapperConfiguration() {
        return new DataModelMapperConfiguration(Map.ofEntries(
                Map.entry(AddressModelMapper.MAPPER.getSupportedClass(), AddressModelMapper.MAPPER),
                Map.entry(ContactModelMapper.MAPPER.getSupportedClass(), ContactModelMapper.MAPPER),
                Map.entry(DegreeDetailModelMapper.MAPPER.getSupportedClass(), DegreeDetailModelMapper.MAPPER),
                Map.entry(DegreeTypeModelMapper.MAPPER.getSupportedClass(), DegreeTypeModelMapper.MAPPER),
                Map.entry(EducationTypeModelMapper.MAPPER.getSupportedClass(), EducationTypeModelMapper.MAPPER),
                Map.entry(EducationModelMapper.MAPPER.getSupportedClass(), EducationModelMapper.MAPPER),
                Map.entry(EstablishmentModelMapper.MAPPER.getSupportedClass(), EstablishmentModelMapper.MAPPER),
                Map.entry(EmploymentModelMapper.MAPPER.getSupportedClass(), EmploymentModelMapper.MAPPER),
                Map.entry(ResumeModelMapper.MAPPER.getSupportedClass(), ResumeModelMapper.MAPPER)));
    }
}
