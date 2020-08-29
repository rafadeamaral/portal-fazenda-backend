CREATE TABLE servico_historico (
  id_servico_historico  BIGINT PRIMARY KEY AUTO_INCREMENT,
  id_autorizador        INT NOT NULL,
  id_servico            INT NOT NULL,
  tp_status             SMALLINT,
  dh_historico          TIMESTAMP NOT NULL,

  FOREIGN KEY(id_autorizador)   REFERENCES autorizador(id_autorizador),
  FOREIGN KEY(id_servico)       REFERENCES servico(id_servico)
);