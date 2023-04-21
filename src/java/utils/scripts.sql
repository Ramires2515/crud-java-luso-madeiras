create table profissao(
    codigoprofissao serial not null primary key,
    nomeprofissao varchar not null
);

create table pessoa(
    codigopessoa serial not null primary key,
    nomepessoa varchar not null,
    datanascimentopessoa date not null,
    cpfpessoa varchar not null unique,
    senhapessoa varchar not null
);

create table paciente(
    codigopaciente serial not null primary key,
    codigopessoa int not null unique references pessoa(codigopessoa),
    numerocartaosuspaciente varchar not null,
    statuspaciente boolean not null,
    codigoprofissao int not null references profissao(codigoprofissao)
);

create table medico(
    codigomedico serial not null primary key,
    codigopessoa int not null unique references pessoa(codigopessoa),    
    crmmedico varchar not null,
    statusmedico boolean not null
);

create table consulta(
    codigoconsulta serial not null primary key,
    dataconsulta date not null,
    horarioconsulta time not null,
    situacaoconsulta varchar not null,
    observacoesconsulta varchar not null,
    codigopaciente int not null references paciente(codigopaciente),
    codigomedico int not null references medico(codigomedico)
);