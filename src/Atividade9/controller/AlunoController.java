package Atividade9.controller;

import Atividade9.exception.NomeInvalidoException;
import Atividade9.exception.NotaInvalidaException;

import java.util.Scanner;


public class AlunoController {

    private Scanner scanner = new Scanner(System.in);

    public String lerNome() {
        String nome;

        while (true) {
            try {
                System.out.print("Nome do aluno: ");
                nome = scanner.nextLine().trim();

                nome = (nome.length() < 3)
                        ? erroNome(nome)
                        : nome;

                return nome;

            } catch (NomeInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String erroNome(String nome) throws NomeInvalidoException {
        throw new NomeInvalidoException(
                nome.length() == 0
                        ? "Nome inválido! Não pode ser vazio."
                        : "Nome inválido! Mínimo 3 caracteres."
        );
    }

    public double lerNota(int num) {
        double nota;

        while (true) {
            try {
                System.out.print("Nota " + num + ": ");
                nota = Double.parseDouble(scanner.nextLine().replace(",", "."));

                nota = (nota < 0 || nota > 100)
                        ? erroNota(nota)
                        : nota;

                return nota;

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
            } catch (NotaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private double erroNota(double nota) throws NotaInvalidaException {
        throw new NotaInvalidaException(
                nota < 0
                        ? "Nota não pode ser negativa."
                        : "Nota não pode ser maior que 100."
        );
    }

    public boolean continuar() {
        System.out.print("Deseja adicionar outro aluno? (s/n): ");
        String resp = scanner.nextLine().trim();
        return resp.equalsIgnoreCase("s");
    }
}