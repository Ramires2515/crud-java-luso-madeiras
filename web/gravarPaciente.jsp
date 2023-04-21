<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card">
    <div class="card-header text-center">
        <h1>Novo paciente</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <div class="text-center">
            <hr/>
            <a class="btn btn-secondary" href="ListarPaciente">Voltar</a>
            <hr/>
        </div>
        <form action="GravarPaciente" method="post">
            <div class="row">
                <div class="form-group col-1">
                    <label for="codigoPaciente">Código:</label>
                    <input class="form-control" type="text" id="codigoPaciente" name="codigoPaciente" readonly value="${paciente.codigoPaciente == 0 ? "" : paciente.codigoPaciente}"/>
                </div>
                <div class="form-group col-1">
                    <label for="codigoPessoa">Código:</label>
                    <input class="form-control" type="text" id="codigoPessoa" name="codigoPessoa" readonly value="${paciente.codigoPessoa == 0 ? "" : paciente.codigoPessoa}"/>
                </div>
                <div class="form-group col-4">
                    <label for="nomePessoa">Nome:</label>
                    <input class="form-control" type="text" id="nomePessoa" name="nomePessoa" placeholder="Nome" required value="${paciente.nomePessoa}"/>
                </div>
                <div class="form-group col-3">
                    <label for="dataNascimentoPessoa">Data de nascimento:</label>
                    <input class="form-control" type="date" id="dataNascimentoPessoa" name="dataNascimentoPessoa" required value="${paciente.dataNascimentoPessoa}"/>
                </div>
                <div class="form-group col-3">
                    <label for="cpfPessoa">CPF:</label>
                    <input class="form-control" type="text" id="cpfPessoa" name="cpfPessoa" placeholder="CPF" data-mask="000.000.000-00" required value="${paciente.cpfPessoa}"/>
                </div>
            </div>
            <div class="row mt-3">
                <div class="form-group col-4">
                    <label for="numeroCartaoSusPaciente">Nº do cartão do SUS:</label>
                    <input class="form-control" type="text" id="numeroCartaoSusPaciente" name="numeroCartaoSusPaciente" required placeholder="Nº do cartão do SUS" value="${paciente.numeroCartaoSusPaciente}"/>
                </div>
                <div class="form-group col-4">
                    <label>Status:</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="ativo" name="statusPaciente" required ${paciente.statusPaciente ? "checked" : ""} value="Ativo"/>
                        <label for="ativo" class="form-check-label">Ativo</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="inativo" name="statusPaciente" required ${!paciente.statusPaciente ? "checked" : ""} value="Inativo"/>
                        <label for="inativo" class="form-check-label">Inativo</label>
                    </div>
                </div>
                <div class="form-group col-4">
                    <label for="codigoProfissao">Profissao:</label>
                    <select id="codigoProfissao" name="codigoProfissao" class="form-control" required>
                        <option value="">Selecione...</option>
                        <c:forEach var="profissao" items="${profissoes}">
                            <option value="${profissao.codigoProfissao}" ${profissao.codigoProfissao == paciente.profissao.codigoProfissao ? "selected" : ""}>${profissao.nomeProfissao}</option>
                        </c:forEach>
                    </select>
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