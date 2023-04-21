<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <h1>Médicos</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <hr/>
        <a class="btn btn-success" href="NovoMedico">Novo</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Voltar</a>
        <hr/>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Data de nascimento</th>
                    <th>CPF</th>
                    <th>Nº do CRM</th>
                    <th>Status</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="medico" items="${medicos}">
                    <tr>
                        <td>${medico.codigoMedico}</td>
                        <td>${medico.nomePessoa}</td>
                        <td>${medico.dataNascimentoPessoa}</td>
                        <td>${medico.cpfPessoa}</td>
                        <td>${medico.crmMedico}</td>
                        <td>${medico.statusMedico ? "Ativo" : "Inativo"}</td>
                        <td><a class="btn btn-info" href="ConsultarMedico?codigoMedico=${medico.codigoMedico}">Alterar</a></td>
                        <td><a class="btn btn-danger" href="ExcluirMedico?codigoMedico=${medico.codigoMedico}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/rodape.jsp"/>