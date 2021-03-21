-- Primary contact
insert into public.contact (contact_id, first_name, last_name, email_address, system_wide_default) VALUES (1, 'Greg', 'Johnson', 'gjohnson912@gmail.com', true);

-- Type seeding
INSERT INTO public.degree_type (degree_type_id, name, acronym, display_name, description) VALUES (1, 'Associate', 'A.', 'Associate', 'Sub-Bachelor degree (Undergraduate)');
INSERT INTO public.degree_type (degree_type_id, name, acronym, display_name, description) VALUES (2, 'Bachelor', 'B.', 'Bachelor', 'Bachelor''s degree (Undergraduate)');
INSERT INTO public.degree_type (degree_type_id, name, acronym, display_name, description) VALUES (3, 'Master', 'M.', 'Master', 'Master''s degree (Graduate)');
INSERT INTO public.degree_type (degree_type_id, name, acronym, display_name, description) VALUES (4, 'Doctor', 'D.', 'Doctor', 'Doctorate degree (Graduate)');

INSERT INTO public.education_type (education_type_id, name, description) VALUES (1, 'Degree', 'Higher level education degree.');
INSERT INTO public.education_type (education_type_id, name, description) VALUES (2, 'Course', 'Single course.');
INSERT INTO public.education_type (education_type_id, name, description) VALUES (3, 'Certification', 'Single certification.');


INSERT INTO public.address (address_id, primary_street, secondary_street, city, state, zip_base) VALUES (1, '106 E Main St', null, 'Spring Arbor', 'MI', '49283');
INSERT INTO public.address (address_id, primary_street, secondary_street, city, state, zip_base) VALUES (2, '6101 Anacapri Blvd.', null, 'Lansing', 'MI', '48917');
INSERT INTO public.address (address_id, primary_street, secondary_street, city, state, zip_base) VALUES (3, '2164 University Park Dr.', 'Suite 200', 'Okemos', 'MI', '48864');

INSERT INTO public.establishment (establishment_id, name, sub_name, website, address_id) VALUES (1, 'Spring Arbor University', null, null, 1);
INSERT INTO public.establishment (establishment_id, name, sub_name, website, address_id) VALUES (2, 'Kunz, Leigh and Associates', null, 'https://kunzleigh.com', 3);
INSERT INTO public.establishment (establishment_id, name, sub_name, website, address_id) VALUES (3, 'Auto-Owners Insurance', null, 'https://www.auto-owners.com', 2);

INSERT INTO public.education (education_id, education_type_id, contact_id, name, sub_name, completion_date, establishment_id) VALUES (1, 1, 1, 'Bachelor of Arts', null, '2012-05-01', 1);
INSERT INTO public.degree_detail (degree_detail_id, education_id, degree_type_id, major, minor) VALUES (1, 1, 2, 'Physics-Mathematics', 'Computer Science');

INSERT INTO public.employment (employment_id, establishment_id, contact_id, start_date, end_date, role) VALUES (1, 3, 1, '2012-06-01', '2016-09-01', 'Sr. Software Developer');
INSERT INTO public.employment (employment_id, establishment_id, contact_id, start_date, end_date, role) VALUES (2, 2, 1, '2016-09-01', null, 'Senior Software Developer');