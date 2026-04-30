package model;

public class Cliente {

    private String id;
    private String nome;
    private String email;

    public Cliente(String id, String nome, String email) {
        this.id    = id;
        this.nome  = nome;
        this.email = email;
    }

    public String getId()    { return id; }
    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Nome: %-20s | E-mail: %s", id, nome, email);
    }
}