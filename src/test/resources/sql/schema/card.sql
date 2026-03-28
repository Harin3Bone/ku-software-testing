DROP TABLE IF EXISTS public.card;

CREATE TABLE public.card
(
    id                uuid                                               NOT NULL
        CONSTRAINT card_pk PRIMARY KEY,
    title             VARCHAR(50)                                        NOT NULL,
    description       text                     DEFAULT ''::CHARACTER VARYING,
    status            VARCHAR(20)                                        NOT NULL,
    created_timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by        VARCHAR(30)              DEFAULT 'SYSTEM'::CHARACTER VARYING NOT NULL,
    updated_timestamp TIMESTAMP WITH TIME ZONE                           NOT NULL,
    updated_by        VARCHAR(30)              DEFAULT 'SYSTEM'::CHARACTER VARYING NOT NULL
);

