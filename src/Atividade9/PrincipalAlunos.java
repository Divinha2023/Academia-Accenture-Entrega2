package Atividade9;

import Atividade9.controller.AlunoController;
import Atividade9.model.Aluno;
import Atividade9.model.Turma;

public class PrincipalAlunos {

    public static void main(String[] args) {

        AlunoController controller = new AlunoController();
        Turma turma = new Turma();

        boolean continuar = true;

        while (continuar) {

            System.out.println("--------------------------------");

            String nome = controller.lerNome();
            double n1 = controller.lerNota(1);
            double n2 = controller.lerNota(2);
            double n3 = controller.lerNota(3);

            turma.adicionarAluno(new Aluno(nome, new double[]{n1, n2, n3}));

            continuar = controller.continuar();
        }

        System.out.println();
        turma.exibirRelatorioIndividual();

        System.out.println();
        turma.exibirEstatisticas();

        System.out.println();
        turma.exibirDistribuicao();

        System.out.println();
        turma.exibirMelhoresAlunos();
    }
}