-- Table: public.shout

-- DROP TABLE public.shout;

CREATE TABLE public.shout
(
    id bigint NOT NULL,
    mime character varying(255) COLLATE pg_catalog."default",
    shout_date date,
    shout_entry character varying(800) COLLATE pg_catalog."default",
    shout_image oid,
    shout_ip character varying(255) COLLATE pg_catalog."default",
    shout_lat double precision NOT NULL,
    shout_long double precision NOT NULL,
    shout_small_image bytea,
    CONSTRAINT shout_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.shout
    OWNER to mdbkmwhgjbkkdc;

