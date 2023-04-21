<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/cabecalho.jsp"/>
<div class="card">
    <div class="card-header text-center">
        <h1>Novo médico</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <div class="text-center">
            <hr/>
            <a class="btn btn-secondary" href="ListarMedico">Voltar</a>
            <hr/>
        </div>
        <form action="GravarMedico" method="post">
            <div class="row">
                <div class="form-group col-1">
                    <label for="codigoMedico">Código:</label>
                    <input class="form-control" type="text" id="codigoMedico" name="codigoMedico" readonly value="${medico.codigoMedico == 0 ? "" : medico.codigoMedico}"/>
                </div>
                <div class="form-group col-1">
                    <label for="codigoPessoa">Código:</label>
                    <input class="form-control" type="text" id="codigoPessoa" name="codigoPessoa" readonly value="${medico.codigoPessoa == 0 ? "" : medico.codigoPessoa}"/>
                </div>
                <div class="form-group col-4">
                    <label for="nomePessoa">Nome:</label>
                    <input class="form-control" type="text" id="nomePessoa" name="nomePessoa" placeholder="Nome" required value="${medico.nomePessoa}"/>
                </div>
                <div class="form-group col-3">
                    <label for="dataNascimentoPessoa">Data de nascimento:</label>
                    <input class="form-control" type="date" id="dataNascimentoPessoa" name="dataNascimentoPessoa" required value="${medico.dataNascimentoPessoa}"/>
                </div>
                <div class="form-group col-3">
                    <label for="cpfPessoa">CPF:</label>
                    <input class="form-control" type="text" id="cpfPessoa" name="cpfPessoa" placeholder="CPF" data-mask="000.000.000-00" required value="${medico.cpfPessoa}"/>
                </div>
            </div>
            <div class="row mt-3">
                <div class="form-group col-6">
                    <label for="crmMedico">CRM:</label>
                    <input class="form-control" type="text" id="crmMedico" name="crmMedico" placeholder="Nº do CRM" required value="${medico.crmMedico}"/>
                </div>
                <div class="form-group col-6">
                    <label>Status:</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="ativo" name="statusMedico" required ${medico.statusMedico ? "checked" : ""} value="Ativo"/>
                        <label for="ativo" class="form-check-label">Ativo</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="inativo" name="statusMedico" required ${!medico.statusMedico ? "checked" : ""} value="Inativo"/>
                        <label for="inativo" class="form-check-label">Inativo</label>
                    </div>
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