package hospital.digital.entity;

public interface Usuario {
    public abstract Long getId();
    public abstract String getNome();
    public abstract String getEmail();
    public abstract String getSenha();
    public abstract String getCpf();
    public abstract String getTelefone();
    public abstract Roles getRoles();
    public abstract int calcularIdade();
}
