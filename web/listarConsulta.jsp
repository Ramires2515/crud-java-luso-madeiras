<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="/cabecalho.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <h1>Consultas</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <hr/>
        <a class="btn btn-success" href="NovaConsulta">Nova</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Voltar</a>
        <hr/>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Data</th>
                    <th>Horário</th>
                    <th>Situação</th>
                    <th>Médico</th>
                    <th>Paciente</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="consulta" items="${consultas}">
                    <tr>
                        <td>${consulta.codigoConsulta}</td>
                        <td>${consulta.dataConsulta}</td>
                        <td>${consulta.horarioConsulta}</td>
                        <td>${consulta.situacaoConsulta}</td>
                        <td>${consulta.medico.nomePessoa}</td>
                        <td>${consulta.paciente.nomePessoa}</td>
                        <td><a class="btn btn-info" href="ConsultarConsulta?codigoConsulta=${consulta.codigoConsulta}">Alterar</a></td>
                        <td><a class="btn btn-danger" href="ExcluirConsulta?codigoConsulta=${consulta.codigoConsulta}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/rodape.jsp"/>