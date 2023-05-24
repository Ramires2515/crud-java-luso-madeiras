package model;

public class Usuario {

    private int codigoUsuario;
    private String nomeUsuario;
    private String userName;
    private String senhaUsuario;
    private boolean statusUsuario;

    public Usuario() {

    }

    public Usuario(int codigoUsuario, String userName, String senhaUsuario, boolean statusUsuario) {
        this.codigoUsuario = codigoUsuario;
        this.userName = userName;
        this.senhaUsuario = senhaUsuario;
        this.statusUsuario = statusUsuario;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public boolean isStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}