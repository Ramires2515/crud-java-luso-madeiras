<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card">
    <div class="card-header text-center">
        <h1>Nova profissão</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <div class="text-center">
            <hr/>
            <a class="btn btn-secondary" href="ListarProfissao">Voltar</a>
            <hr/>
        </div>
        <form action="GravarProfissao" method="post">
            <div class="row">
                <div class="form-group col-6">
                    <label for="codigoProfissao">Código:</label>
                    <input class="form-control" type="text" id="codigoProfissao" name="codigoProfissao" readonly value="${profissao.codigoProfissao == 0 ? "" : profissao.codigoProfissao}"/>
                </div>
                <div class="form-group col-6">
                    <label for="nomeProfissao">Nome:</label>
                    <input class="form-control" type="text" id="nomeProfissao" name="nomeProfissao" placeholder="Descrição" required value="${profissao.nomeProfissao}"/>
                </div>
            </div>
            <div class="form-group text-center">
                <hr/>
                <button class="btn btn-success" type="submit">Gravar</button>
                <hr/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/rodape.jsp"/>