package Atividade8.controller;

import Atividade8.model.ContaCorrente;

public class ContaController {

    public void depositar(ContaCorrente conta, double valor) {
        if (conta.depositar(valor)) {
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido.");
        }
    }

    public void sacar(ContaCorrente conta, double valor) {
        if (conta.sacar(valor)) {
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public void transferir(ContaCorrente origem, ContaCorrente destino, double valor) {
        if (origem.transferir(valor, destino)) {
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Transferência cancelada! Saldo insuficiente.");
        }
    }

    public void exibirExtrato(ContaCorrente conta) {
        System.out.println("========= EXTRATO =========");
        System.out.println("Titular : " + conta.getCliente().getNome() + " " + conta.getCliente().getSobrenome());
        System.out.println("CPF     : " + conta.getCliente().getCpf());
        System.out.println("Conta   : " + conta.getNumero());
        System.out.println("Data    : " + conta.getData());
        System.out.println("Saldo   : R$ " + conta.getSaldo());
        System.out.println("===========================");
    }
}