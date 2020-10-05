create table ordem_servico(
   id char(36) not null,
   cliente char(36) not null,
   equipamento char(36) not null,
   problema char(36) not null,
   primary key (id)
);

create table ordem_servico_situacao (
   ordem_servico char(36) not null,
   ocorrido_em timestamp not null,
   status varchar(16) not null,
   responsavel_por char(36),
   constraint fk_ordem_servico_situacao foreign key (ordem_servico) references ordem_servico (id)
);
