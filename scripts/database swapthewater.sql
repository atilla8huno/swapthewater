CREATE TABLE voluntario
(
  id serial NOT NULL,
  nome character varying(45) NOT NULL,
  CONSTRAINT voluntario_pkey PRIMARY KEY (id )
);

CREATE TABLE trocas_agua
(
  id serial NOT NULL,
  data_troca timestamp without time zone NOT NULL,
  id_voluntario integer NOT NULL,
  CONSTRAINT trocas_agua_pkey PRIMARY KEY (id ),
  CONSTRAINT trocas_agua_id_voluntario_fkey1 FOREIGN KEY (id_voluntario)
      REFERENCES voluntario (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

