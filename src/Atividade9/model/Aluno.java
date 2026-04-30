package Atividade9.model;

public class Aluno {

    private String nome;
    private double[] notas;
    private double media;

    public Aluno(String nome, double[] notas) {
        this.nome = nome;
        this.notas = notas;
        this.media = (notas[0] + notas[1] + notas[2]) / 3.0;
    }

    public String getNome() {
        return nome;
    }

    public double[] getNotas() {
        return notas;
    }

    public double getMedia() {
        return media;
    }

    public String getSituacao() {
        return media >= 70 ? "APROVADO"
                : media >= 50 ? "RECUPERAÇÃO"
                : "REPROVADO";
    }

    public String getRelatorio() {
        return String.format(
                "%s | Notas: %.0f, %.0f, %.0f | Média: %.2f | %s",
                nome, notas[0], notas[1], notas[2], media, getSituacao()
        );
    }
}