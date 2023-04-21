<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <h1>Pacientes</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <hr/>
        <a class="btn btn-success" href="NovoPaciente">Novo</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Voltar</a>
        <hr/>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Data de nascimento</th>
                    <th>CPF</th>
                    <th>Nº Cartão SUS</th>
                    <th>Atividade principal</th>
                    <th>Status</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="paciente" items="${pacientes}">
                    <tr>
                        <td>${paciente.codigoPaciente}</td>
                        <td>${paciente.nomePessoa}</td>
                        <td>${paciente.dataNascimentoPessoa}</td>
                        <td>${paciente.cpfPessoa}</td>
                        <td>${paciente.numeroCartaoSusPaciente}</td>
                        <td>${paciente.profissao.nomeProfissao}</td>
                        <td>${paciente.statusPaciente ? "Ativo" : "Inativo"}</td>
                        <td><a class="btn btn-info" href="ConsultarPaciente?codigoPaciente=${paciente.codigoPaciente}">Alterar</a></td>
                        <td><a class="btn btn-danger" href="ExcluirPaciente?codigoPaciente=${paciente.codigoPaciente}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/rodape.jsp"/>