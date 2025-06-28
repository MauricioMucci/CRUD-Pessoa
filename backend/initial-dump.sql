CREATE TABLE public.pessoa
(
    id_pessoa          SERIAL4                                            NOT NULL,
    nome               VARCHAR(255)                                       NOT NULL,
    nascimento         DATE                                               NOT NULL,
    cpf                VARCHAR(14)                                        NOT NULL,
    email              VARCHAR(255)                                       NOT NULL,
    criacao_registro   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alteracao_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_pessoa PRIMARY KEY (id_pessoa),
    CONSTRAINT uk_pessoa_cpf UNIQUE (cpf),
    CONSTRAINT uk_pessoa_email UNIQUE (email)
);

CREATE
OR REPLACE FUNCTION update_alteracao_registro_timestamp() RETURNS TRIGGER AS
$$
BEGIN
    NEW.alteracao_registro
= CURRENT_TIMESTAMP;
RETURN new;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trg_pessoa_alteracao_registro
    BEFORE UPDATE
    ON public.pessoa
    FOR EACH ROW EXECUTE function update_alteracao_registro_timestamp();

CREATE TABLE public.endereco
(
    id_endereco SERIAL4      NOT NULL,
    cep         INTEGER      NOT NULL,
    rua         VARCHAR(255) NOT NULL,
    numero      INTEGER      NOT NULL CHECK ( numero >= 0 ),
    cidade      VARCHAR(255) NOT NULL,
    estado      VARCHAR(255) NOT NULL,
    id_pessoa   INTEGER      NOT NULL,
    CONSTRAINT pk_endereco PRIMARY KEY (id_endereco),
    CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa
        ON DELETE CASCADE
);
