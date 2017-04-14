CREATE SEQUENCE contact_audit_id_seq start 1;

CREATE TABLE public.contactaudit
(
    id integer NOT NULL DEFAULT nextval('contact_audit_id_seq'::regclass),
    contact_id integer NOT NULL,
    event_date timestamp(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    change_type varchar(10) NOT NULL,
    origin_value varchar(1024) NOT NULL,
	user_name varchar(50) NOT NULL,
    CONSTRAINT contact_audit_pkey PRIMARY KEY (id)
);
