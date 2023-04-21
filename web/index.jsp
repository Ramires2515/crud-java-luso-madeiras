<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="cabecalho.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <h1>Menu principal</h1>
    </div>
    <div class="card-body">
        <a href="ListarProfissao" class="btn btn-primary">Profissões</a>
        <a href="ListarPaciente" class="btn btn-secondary">Pacientes</a>
        <a href="ListarMedico" class="btn btn-success">Médicos</a>
        <a href="ListarConsulta" class="btn btn-info">Consultas</a>
    </div>
</div>
<jsp:include page="rodape.jsp"/>