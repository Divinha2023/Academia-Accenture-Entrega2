package view;

import model.Produto;
import java.util.List;

public class MenuView {

    public static void exibirMenuPrincipal() {
        System.out.println("\n============================================");
        System.out.println("   SIMULADOR DE PROCESSAMENTO DE PEDIDOS    ");
        System.out.println("============================================");
        System.out.println("--- PRODUTOS ---");
        System.out.println("1  - Adicionar produto");
        System.out.println("2  - Listar produtos por SKU");
        System.out.println("3  - Listar produtos por preço");
        System.out.println("--- CLIENTES ---");
        System.out.println("4  - Adicionar cliente");
        System.out.println("--- PEDIDOS ---");
        System.out.println("5  - Criar pedido");
        System.out.println("6  - Reservar estoque");
        System.out.println("7  - Pagar pedido");
        System.out.println("8  - Cancelar pedido");
        System.out.println("--- RELATÓRIOS ---");
        System.out.println("9  - Faturamento total (pedidos PAGO)");
        System.out.println("10 - Top 3 produtos mais vendidos");
        System.out.println("11 - Faturamento por categoria");
        System.out.println("12 - Clientes com maior número de pedidos");
        System.out.println("0  - Sair");
        System.out.println("============================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println("\n" + mensagem);
    }

    public static void exibirProdutos(List<Produto> produtos) {
        System.out.println("\n---------- LISTA DE PRODUTOS ----------");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
        System.out.println("---------------------------------------");
    }
}