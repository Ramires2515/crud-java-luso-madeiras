<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <h1>Profissões</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <hr/>
        <a class="btn btn-success" href="NovaProfissao">Nova</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Voltar</a>
        <hr/>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Descrição</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="profissao" items="${profissoes}">
                    <tr>
                        <td>${profissao.codigoProfissao}</td>
                        <td>${profissao.nomeProfissao}</td>
                        <td><a class="btn btn-info" href="ConsultarProfissao?codigoProfissao=${profissao.codigoProfissao}">Alterar</a></td>
                        <td><a class="btn btn-danger" href="ExcluirProfissao?codigoProfissao=${profissao.codigoProfissao}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/rodape.jsp"/>