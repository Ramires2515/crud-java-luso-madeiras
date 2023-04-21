package model;

public class Medico extends Pessoa {

    private int codigoMedico;
    private String crmMedico;
    private boolean statusMedico;

    public Medico() {

    }

    public Medico(int codigoMedico, String crmMedico, boolean statusMedico, int codigoPessoa, String nomePessoa, String dataNascimentoPessoa, String cpfPessoa, String senhaPessoa) {
        super(codigoPessoa, nomePessoa, dataNascimentoPessoa, cpfPessoa, senhaPessoa);
        this.codigoMedico = codigoMedico;
        this.crmMedico = crmMedico;
        this.statusMedico = statusMedico;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public boolean isStatusMedico() {
        return statusMedico;
    }

    public void setStatusMedico(boolean statusMedico) {
        this.statusMedico = statusMedico;
    }
}