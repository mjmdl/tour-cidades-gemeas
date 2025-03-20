BEGIN;

CREATE SCHEMA Gemeas;
CREATE SCHEMA Ext;

CREATE EXTENSION postgis SCHEMA Ext;

CREATE SEQUENCE Gemeas.Sq_UsuarioId START WITH 1;
CREATE TABLE Gemeas.Usuario (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_UsuarioId'),
  Nome VARCHAR NOT NULL,
  Email VARCHAR NOT NULL,
  Senha VARCHAR NOT NULL,
  Admin BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT Pk_Usuario PRIMARY KEY (Id),
  CONSTRAINT Uq_UsuarioEmail UNIQUE (Email)
);
ALTER SEQUENCE Gemeas.Sq_UsuarioId OWNED BY Gemeas.Usuario.Id;

CREATE SEQUENCE Gemeas.Sq_PontoTuristicoId START WITH 1;
CREATE TABLE Gemeas.PontoTuristico (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_PontoTuristicoId'),
  Nome VARCHAR NOT NULL,
  Posicao Ext.GEOMETRY NOT NULL,
  Pontuacao BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT Pk_PontoTuristico PRIMARY KEY (Id)
);
ALTER SEQUENCE Gemeas.Sq_PontoTuristicoId OWNED BY Gemeas.PontoTuristico.Id;

CREATE SEQUENCE Gemeas.Sq_PontoTuristicoDicasId START WITH 1
CREATE TABLE Gemeas.PontoTuristicoDicas (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_PontoTuristicoDicasId'),
  Descricao VARCHAR NOT NULL,
  Pontuacao BIGINT NOT NULL,
  PontoTuristicoId BIGINT NOT NULL,
  CONSTRAINT Pk_PontoTuristicoDicas PRIMARY KEY (Id),
  CONSTRAINT Fk_PontoTuristico FOREIGN KEY (PontoTuristicoId) REFERENCES Gemeas.PontoTuristico(Id)
);
ALTER SEQUENCE Gemeas.Sq_PontoTuristicoDicasId OWNED BY Gemeas.PontoTuristicoDicas.Id;

CREATE SEQUENCE Gemeas.Sq_UsuarioDicasId START WITH 1;
CREATE TABLE Gemeas.UsuarioDicas (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_UsuarioDicasId'),
  UsuarioId BIGINT NOT NULL,
  PontoTuristicoDicasId BIGINT NOT NULL,
  Momento TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT Pk_UsuarioDicas PRIMARY KEY (Id),
  CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Gemeas.Usuario (Id),
  CONSTRAINT Fk_PontoTuristicoDicas FOREIGN KEY (PontoTuristicoDicasId) REFERENCES Gemeas.PontoTuristicoDicas (Id),
  CONSTRAINT Uq_UsuarioPontoTuristicoDicas UNIQUE (UsuarioId, PontoTuristicoDicasId)
);
ALTER SEQUENCE Gemeas.Sq_UsuarioDicasId OWNED BY Gemeas.UsuarioDicas.Id;

CREATE SEQUENCE Gemeas.Sq_PontoTuristicoUsuarioId START WITH 1;
CREATE TABLE Gemeas.PontoTuristicoUsuario (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_PontoTuristicoUsuarioId'),
  PontoTuristicoId BIGINT NOT NULL,
  UsuarioId BIGINT NOT NULL,
  Momento TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  Pontuacao BIGINT,
  CONSTRAINT Pk_PontoTuristicoUsuario PRIMARY KEY (Id),
  CONSTRAINT Fk_PontoTuristico FOREIGN KEY (PontoTuristicoId) REFERENCES Gemeas.PontoTuristico (Id),
  CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Gemeas.Usuario (Id),
  CONSTRAINT Uq_PontoTuristicoUsuario UNIQUE (PontoTuristicoId, UsuarioId)
);
ALTER SEQUENCE Gemeas.Sq_PontoTuristicoUsuarioId OWNED BY Gemeas.PontoTuristicoUsuario.Id;

CREATE SEQUENCE Gemeas.Sq_ParceriasId START WITH 1;
CREATE TABLE Gemeas.Parcerias (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_ParceriasId'),
  Nome VARCHAR NOT NULL,
  Posicao Ext.GEOMETRY NOT NULL,
  Pontuacao BIGINT NOT NULL DEFAULT 0,
  ValorMinimo NUMERIC(8,2),
  ValorMaximo NUMERIC(8,2),
  CONSTRAINT Pk_Parceria PRIMARY KEY (Id),
  CONSTRAINT Ck_ValorMinMax CHECK (
    ((ValorMinimo IS NULL) <> (ValorMaximo IS NULL)) 
    OR ValorMinimo <= ValorMaximo
  )
);
ALTER SEQUENCE Gemeas.Sq_ParceriasId OWNED BY Gemeas.Parcerias.Id;

CREATE SEQUENCE Gemeas.Sq_PatrociniosId START WITH 1;
CREATE TABLE Gemeas.Patrocinios (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_PatrociniosId'),
  ParceriaId BIGINT NOT NULL,
  PontoTuristicoId BIGINT NOT NULL,
  Evento VARCHAR NOT NULL,
  ValorPorVisita NUMERIC(8,2),
  CONSTRAINT Pk_Patrocinios PRIMARY KEY (Id),
  CONSTRAINT Fk_Parceria FOREIGN KEY (ParceriaId) REFERENCES Gemeas.Parcerias (Id),
  CONSTRAINT Fk_PontoTuristicoId FOREIGN KEY (PontoTuristicoId) REFERENCES Gemeas.PontoTuristico (Id)
);
ALTER SEQUENCE Gemeas.Sq_PatrociniosId OWNED BY Gemeas.Patrocinios.Id;

CREATE SEQUENCE Gemeas.Sq_EventosId START WITH 1;
CREATE TABLE Gemeas.Eventos (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_EventosId'),
  Nome VARCHAR NOT NULL,
  DataInicio TIMESTAMPTZ NOT NULL,
  DataFinal TIMESTAMPTZ NOT NULL,
  CONSTRAINT Pk_Eventos PRIMARY KEY (Id),
  CONSTRAINT Ck_DataInicioAnteriorOuIgualAhDataFinal CHECK (DataInicio <= DataFinal)
);
ALTER SEQUENCE Gemeas.Sq_EventosId OWNED BY Gemeas.Eventos.Id;

CREATE SEQUENCE Gemeas.Sq_UsuarioPontuacaoId START WITH 1;
CREATE TABLE Gemeas.UsuarioPontuacao (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_UsuarioPontuacaoId'),
  UsuarioId BIGINT NOT NULL,
  EventoId BIGINT NOT NULL,
  Pontuacao BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT Pk_UsuarioPontuacao PRIMARY KEY (Id),
  CONSTRAINT Fk_Usuario FOREIGN KEY (UsuarioId) REFERENCES Gemeas.Usuario (Id),
  CONSTRAINT Fk_Evento FOREIGN KEY (EventoId) REFERENCES Gemeas.Eventos (Id)
);
ALTER SEQUENCE Gemeas.Sq_UsuarioPontuacaoId OWNED BY Gemeas.UsuarioPontuacao.Id;

CREATE SEQUENCE Gemeas.Sq_EventosPremiacaoId START WITH 1;
CREATE TABLE Gemeas.EventosPremiacao (
  Id BIGINT NOT NULL DEFAULT NEXTVAL('Gemeas.Sq_EventosPremiacaoId'),
  EventoId BIGINT NOT NULL,
  Posicao BIGINT NOT NULL DEFAULT 1,
  Premio VARCHAR NOT NULL,
  CONSTRAINT Pk_EventosPremiacao PRIMARY KEY (Id),
  CONSTRAINT Fk_Evento FOREIGN KEY (EventoId) REFERENCES Gemeas.Eventos (Id)
);
ALTER SEQUENCE Gemeas.Sq_EventosPremiacaoId OWNED BY Gemeas.EventosPremiacao.Id;

COMMIT;








