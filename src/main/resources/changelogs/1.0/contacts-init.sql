CREATE SEQUENCE contact_id_seq start 1;

CREATE TABLE public.contacts
(
    id integer NOT NULL DEFAULT nextval('contact_id_seq'::regclass),
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT contact_pkey PRIMARY KEY (id)
);
