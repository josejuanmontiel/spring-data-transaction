CREATE SEQUENCE public.UNIQUE_ID INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999;

CREATE TABLE public.sequence (
	id int8 NULL,	
	optlock int8 NOT NULL,
	name varchar(255) NULL,
	value int8 NULL,
	CONSTRAINT sequence_pk PRIMARY KEY (name,value)
);

CREATE TABLE public.sequence_lost (
	id int8 NULL,	
	optlock int8 NOT NULL,
	name varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT sequence_lost_pk PRIMARY KEY (id)
);
