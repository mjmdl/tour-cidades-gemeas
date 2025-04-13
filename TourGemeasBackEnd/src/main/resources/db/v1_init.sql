BEGIN;

CREATE SCHEMA Tour;

CREATE SEQUENCE Tour.Sq_UsuarioId START WITH 1;
CREATE TABLE Tour.Usuario (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_UsuarioId'),
	Nome VARCHAR NOT NULL,
	Email VARCHAR NOT NULL,
	Senha VARCHAR NOT NULL,
	Admin BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT Pk_Usuario PRIMARY KEY (Id),
	CONSTRAINT Uq_UsuarioEmail UNIQUE (Email)
);
ALTER SEQUENCE Tour.Sq_UsuarioId OWNED BY Tour.Usuario.Id;

CREATE SEQUENCE Tour.Sq_PontoTuristicoId START WITH 1;
CREATE TABLE Tour.PontoTuristico (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_PontoTuristicoId'),
	Nome VARCHAR NOT NULL,
	Latitude NUMERIC NOT NULL,
	Longitude NUMERIC NOT NULL,
	Pontuacao BIGINT NOT NULL DEFAULT 0,
	CONSTRAINT Pk_PontoTuristico PRIMARY KEY (Id)
);
ALTER SEQUENCE Tour.Sq_PontoTuristicoId OWNED BY Tour.PontoTuristico.Id;

CREATE SEQUENCE Tour.Sq_PontoTuristicoDicasId START WITH 1;
CREATE TABLE Tour.PontoTuristicoDicas (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_PontoTuristicoDicasId'),
	Descricao VARCHAR NOT NULL,
	Pontuacao BIGINT NOT NULL,
	PontoTuristicoId BIGINT NOT NULL,
	CONSTRAINT Pk_PontoTuristicoDicas PRIMARY KEY (Id),
	CONSTRAINT Fk_PontoTuristico FOREIGN KEY (PontoTuristicoId) REFERENCES Tour.PontoTuristico(Id)
);
ALTER SEQUENCE Tour.Sq_PontoTuristicoDicasId OWNED BY Tour.PontoTuristicoDicas.Id;

CREATE SEQUENCE Tour.Sq_UsuarioDicasId START WITH 1;
CREATE TABLE Tour.UsuarioDicas (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_UsuarioDicasId'),
	UsuarioId BIGINT NOT NULL,
	PontoTuristicoDicasId BIGINT NOT NULL,
	Momento TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	CONSTRAINT Pk_UsuarioDicas PRIMARY KEY (Id),
	CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Tour.Usuario (Id),
	CONSTRAINT Fk_PontoTuristicoDicas FOREIGN KEY (PontoTuristicoDicasId) REFERENCES Tour.PontoTuristicoDicas (Id),
	CONSTRAINT Uq_UsuarioPontoTuristicoDicas UNIQUE (UsuarioId, PontoTuristicoDicasId)
);
ALTER SEQUENCE Tour.Sq_UsuarioDicasId OWNED BY Tour.UsuarioDicas.Id;

CREATE SEQUENCE Tour.Sq_PontoTuristicoUsuarioId START WITH 1;
CREATE TABLE Tour.PontoTuristicoUsuario (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_PontoTuristicoUsuarioId'),
	PontoTuristicoId BIGINT NOT NULL,
	UsuarioId BIGINT NOT NULL,
	Momento TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	Pontuacao BIGINT,
	CONSTRAINT Pk_PontoTuristicoUsuario PRIMARY KEY (Id),
	CONSTRAINT Fk_PontoTuristico FOREIGN KEY (PontoTuristicoId) REFERENCES Tour.PontoTuristico (Id),
	CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Tour.Usuario (Id),
	CONSTRAINT Uq_PontoTuristicoUsuario UNIQUE (PontoTuristicoId, UsuarioId)
);
ALTER SEQUENCE Tour.Sq_PontoTuristicoUsuarioId OWNED BY Tour.PontoTuristicoUsuario.Id;

CREATE SEQUENCE Tour.Sq_ParceriasId START WITH 1;
CREATE TABLE Tour.Parcerias (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_ParceriasId'),
	Nome VARCHAR NOT NULL,
	Latitude NUMERIC NOT NULL,
	Longitude NUMERIC NOT NULL,
	Pontuacao BIGINT NOT NULL DEFAULT 0,
	ValorMinimo NUMERIC(8,2),
	ValorMaximo NUMERIC(8,2),
	CONSTRAINT Pk_Parceria PRIMARY KEY (Id),
	CONSTRAINT Ck_ValorMinMax CHECK (
		((ValorMinimo IS NULL) <> (ValorMaximo IS NULL)) 
		OR ValorMinimo <= ValorMaximo
	)
);
ALTER SEQUENCE Tour.Sq_ParceriasId OWNED BY Tour.Parcerias.Id;

CREATE SEQUENCE Tour.Sq_PatrociniosId START WITH 1;
CREATE TABLE Tour.Patrocinios (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_PatrociniosId'),
	ParceriaId BIGINT NOT NULL,
	PontoTuristicoId BIGINT NOT NULL,
	EventoId BIGINT NOT NULL,
	ValorPorVisita NUMERIC(8,2),
	CONSTRAINT Pk_Patrocinios PRIMARY KEY (Id),
	CONSTRAINT Fk_Parceria FOREIGN KEY (ParceriaId) REFERENCES Tour.Parcerias (Id),
	CONSTRAINT Fk_EventoId FOREIGN KEY (EventoId) REFERENCES Tour.Evento (Id),
	CONSTRAINT Fk_PontoTuristicoId FOREIGN KEY (PontoTuristicoId) REFERENCES Tour.PontoTuristico (Id)
);
ALTER SEQUENCE Tour.Sq_PatrociniosId OWNED BY Tour.Patrocinios.Id;

CREATE SEQUENCE Tour.Sq_EventosId START WITH 1;
CREATE TABLE Tour.Eventos (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_EventosId'),
	Nome VARCHAR NOT NULL,
	DataInicio TIMESTAMPTZ NOT NULL,
	DataFinal TIMESTAMPTZ NOT NULL,
	CONSTRAINT Pk_Eventos PRIMARY KEY (Id),
	CONSTRAINT Ck_DataInicioAnteriorOuIgualAhDataFinal CHECK (DataInicio <= DataFinal)
);
ALTER SEQUENCE Tour.Sq_EventosId OWNED BY Tour.Eventos.Id;

CREATE SEQUENCE Tour.Sq_UsuarioPontuacaoId START WITH 1;
CREATE TABLE Tour.UsuarioPontuacao (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_UsuarioPontuacaoId'),
	UsuarioId BIGINT NOT NULL,
	EventoId BIGINT NOT NULL,
	Pontuacao BIGINT NOT NULL DEFAULT 0,
	CONSTRAINT Pk_UsuarioPontuacao PRIMARY KEY (Id),
	CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Tour.Usuario (Id),
	CONSTRAINT Fk_Evento FOREIGN KEY (EventoId) REFERENCES Tour.Eventos (Id)
);
ALTER SEQUENCE Tour.Sq_UsuarioPontuacaoId OWNED BY Tour.UsuarioPontuacao.Id;

CREATE SEQUENCE Tour.Sq_EventosPremiacaoId START WITH 1;
CREATE TABLE Tour.EventosPremiacao (
	Id BIGINT NOT NULL DEFAULT NEXTVAL('Tour.Sq_EventosPremiacaoId'),
	EventoId BIGINT NOT NULL,
	Posicao BIGINT NOT NULL DEFAULT 1,
	Premio VARCHAR NOT NULL,
	CONSTRAINT Pk_EventosPremiacao PRIMARY KEY (Id),
	CONSTRAINT Fk_Evento FOREIGN KEY (EventoId) REFERENCES Tour.Eventos (Id)
);
ALTER SEQUENCE Tour.Sq_EventosPremiacaoId OWNED BY Tour.EventosPremiacao.Id;

COMMIT;
