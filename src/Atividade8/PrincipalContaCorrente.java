package Atividade8;

import Atividade8.controller.ContaController;
import Atividade8.model.Cliente;
import Atividade8.model.ContaCorrente;

public class PrincipalContaCorrente {

    public static void main(String[] args) {

        ContaController controller = new ContaController();

        Cliente cliente1 = new Cliente("Divanildo Simões", "da Silva", "000.000.000-000");
        Cliente cliente2 = new Cliente("Divanildo", "Filho", "111.111.111-111");

        ContaCorrente conta1 = new ContaCorrente(1001, 500.0, "30/04/2025", cliente1);
        ContaCorrente conta2 = new ContaCorrente(1002, 200.0, "30/04/2025", cliente2);

        System.out.println("Titular da Conta 1: " + conta1.getCliente().getNome());

        controller.exibirExtrato(conta1);

        controller.depositar(conta1, 300);
        controller.exibirExtrato(conta1);

        controller.sacar(conta1, 100);
        controller.exibirExtrato(conta1);

        controller.transferir(conta1, conta2, 400);
        controller.exibirExtrato(conta1);
        controller.exibirExtrato(conta2);

        controller.transferir(conta1, conta2, 9999);
    }
}