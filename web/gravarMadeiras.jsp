<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/cabecalho.jsp"/>
<div class="card">
    <div class="card-header text-center">
        <h1>Nova Madeira</h1>
    </div>
    <div class="card-body">
        <c:if test="${mensagem != null}">
            <div class="alert alert-primary text-center" role="alert">
                ${mensagem}
            </div>
        </c:if>
        <div class="text-center">
            <hr/>
            <a class="btn btn-secondary" href="ListarConsulta">Voltar</a>
            <hr/>
        </div>
        <form action="GravarConsulta" method="post">
            <div class="row">
                <div class="form-group col-3">
                    <label for="codigoConsulta">Código:</label>
                    <input class="form-control" type="text" id="codigoConsulta" name="codigoConsulta" readonly value="${consulta.codigoConsulta == 0 ? "" : consulta.codigoConsulta}"/>
                </div>
                <div class="form-group col-3">
                    <label for="dataConsulta">Data:</label>
                    <input class="form-control" type="date" id="dataConsulta" name="dataConsulta" required value="${consulta.dataConsulta}"/>
                </div>
                <div class="form-group col-3">
                    <label for="horarioConsulta">Horário:</label>
                    <input class="form-control" type="time" id="horarioConsulta" name="horarioConsulta" required value="${consulta.horarioConsulta}"/>
                </div>
                <div class="form-group col-3">
                    <label for="situacaoConsulta">Situação:</label>
                    <select class="form-control" id="situacaoConsulta" name="situacaoConsulta" required>
                        <option value="Concluída" ${consulta.situacaoConsulta == "Concluída" ? "selected" : ""}>Concluída</option>
                        <option value="Agendada" ${consulta.situacaoConsulta == "Agendada" ? "selected" : ""}>Agendada</option>
                        <option value="Cancelada" ${consulta.situacaoConsulta == "Cancelada" ? "selected" : ""}>Cancelada</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-6">
                    <label for="codigoPaciente">Paciente:</label>
                    <select class="form-control" id="codigoPaciente" name="codigoPaciente" required>
                        <option value="">Selecione...</option>
                        <c:forEach var="paciente" items="${pacientes}">
                            <option value="${paciente.codigoPaciente}" ${paciente.codigoPaciente == consulta.paciente.codigoPaciente ? "selected" : ""}>${paciente.nomePessoa}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-6">
                    <label for="codigoMedico">Médico:</label>
                    <select class="form-control" id="codigoMedico" name="codigoMedico" required>
                        <option value="">Selecione...</option>
                        <c:forEach var="medico" items="${medicos}">
                            <option value="${medico.codigoMedico}" ${medico.codigoMedico == consulta.medico.codigoMedico ? "selected" : ""}>${medico.nomePessoa}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mt-3">
                <div class="form-group col-12">
                    <label for="observacoesConsulta">Observações:</label>
                    <textarea class="form-control" id="observacoesConsulta" name="observacoesConsulta" placeholder="Observações" required>${consulta.observacoesConsulta}</textarea>
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
