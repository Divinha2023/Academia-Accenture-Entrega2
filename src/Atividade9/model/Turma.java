package Atividade9.model;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private List<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void exibirRelatorioIndividual() {
        System.out.println("========== RELATÓRIO INDIVIDUAL ==========");
        alunos.forEach(a -> System.out.println(a.getRelatorio()));
    }

    public void exibirEstatisticas() {
        double maior = alunos.stream().mapToDouble(Aluno::getMedia).max().getAsDouble();
        double menor = alunos.stream().mapToDouble(Aluno::getMedia).min().getAsDouble();
        double media = alunos.stream().mapToDouble(Aluno::getMedia).average().getAsDouble();

        System.out.println("========== ESTATÍSTICAS DA TURMA ==========");
        System.out.printf("Maior média : %.2f%n", maior);
        System.out.printf("Menor média : %.2f%n", menor);
        System.out.printf("Média geral : %.2f%n", media);
    }

    public void exibirDistribuicao() {
        long aprovados = alunos.stream().filter(a -> a.getSituacao().equals("APROVADO")).count();
        long recuperacao = alunos.stream().filter(a -> a.getSituacao().equals("RECUPERAÇÃO")).count();
        long reprovados = alunos.stream().filter(a -> a.getSituacao().equals("REPROVADO")).count();

        System.out.println("========== DISTRIBUIÇÃO DE RESULTADOS ==========");
        System.out.println("Aprovados   : " + aprovados);
        System.out.println("Recuperação : " + recuperacao);
        System.out.println("Reprovados  : " + reprovados);
    }

    public void exibirMelhoresAlunos() {
        double maior = alunos.stream().mapToDouble(Aluno::getMedia).max().getAsDouble();

        System.out.println("========== MELHOR(ES) ALUNO(S) ==========");
        alunos.stream()
                .filter(a -> a.getMedia() == maior)
                .forEach(a -> System.out.printf("%s | Média: %.2f%n", a.getNome(), a.getMedia()));
    }
}