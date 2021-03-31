create table contact (
    contact_id serial primary key,
    first_name varchar(200),
    last_name varchar(200) not null,
    email_address varchar(400),
    system_wide_default boolean not null default false,
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table address (
    address_id serial primary key,
    primary_street varchar(200) not null,
    secondary_street varchar(200),
    city varchar(200) not null,
    state varchar(50) not null,
    zip_base varchar(5) not null,
    zip_ext varchar(4),
    created_time timestamp default now() not null,
    modified_time timestamp
);

create table education_type (
    education_type_id serial primary key,
    name varchar(25) not null,
    description varchar(100),
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table degree_type (
    degree_type_id serial primary key,
    name varchar(100) not null,
    acronym varchar(25) not null,
    display_name varchar(100),
    description varchar(100),
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table establishment (
    establishment_id serial primary key,
    name varchar(200) not null,
    sub_name varchar(200),
    website varchar(200),
    address_id integer references address(address_id),
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table education (
    education_id serial primary key,
    education_type_id integer references education_type(education_type_id) not null,
    contact_id integer references contact(contact_id) not null,
    name varchar(200) not null,
    sub_name varchar(200),
    completion_date date not null,
    created_time timestamp default now() not null,
    modified_time timestamp,
    establishment_id integer references establishment(establishment_id)
);

create table degree_detail (
    degree_detail_id serial primary key,
    education_id integer references education(education_id) not null,
    degree_type_id integer references degree_type(degree_type_id) not null,
    major varchar(100),
    minor varchar(100),
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table employment (
    employment_id serial primary key,
    establishment_id integer references establishment(establishment_id) not null,
    contact_id integer references contact(contact_id) not null,
    role varchar(100) not null,
    start_date date not null,
    end_date date,
    created_time timestamp not null default now(),
    modified_time timestamp
);

create table resume (
    resume_id serial primary key,
    contact_id integer references contact(contact_id) not null,
    document_name varchar(100) not null,
    document bytea not null,
    file_type varchar(100),
    file_extension varchar(25),
    created_time timestamp not null default now(),
    modified_time timestamp
);

grant all privileges on all tables in schema public to homesite_app;
grant all privileges on all sequences in schema public to homesite_app;