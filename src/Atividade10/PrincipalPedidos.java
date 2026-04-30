import controller.*;
import model.*;
import view.MenuView;

import java.util.*;

public class PrincipalPedidos {

    static Scanner scanner         = new Scanner(System.in);
    static ProdutoController  produtoCtrl  = new ProdutoController();
    static ClienteController  clienteCtrl  = new ClienteController();
    static PedidoController   pedidoCtrl   = new PedidoController();
    static RelatorioController relatorioCtrl;

    public static void main(String[] args) {
        relatorioCtrl = new RelatorioController(pedidoCtrl.getPedidos());
        int opcao = -1;

        while (opcao != 0) {
            MenuView.exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
            }
        }

        System.out.println("Encerrando o sistema. Até logo!");
        scanner.close();
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1  -> adicionarProduto();
            case 2  -> MenuView.exibirProdutos(produtoCtrl.listarPorSku());
            case 3  -> MenuView.exibirProdutos(produtoCtrl.listarPorPreco());
            case 4  -> adicionarCliente();
            case 5  -> criarPedido();
            case 6  -> reservarEstoque();
            case 7  -> pagarPedido();
            case 8  -> cancelarPedido();
            case 9  -> relatorioFaturamento();
            case 10 -> relatorioTop3();
            case 11 -> relatorioCategoria();
            case 12 -> relatorioClientes();
            case 0  -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida! Escolha entre 0 e 12.");
        }
    }

    private static void adicionarProduto() {
        System.out.print("SKU: ");
        String sku = scanner.nextLine().trim();
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine().replace(",", ".").trim());
        System.out.print("Estoque: ");
        int estoque = Integer.parseInt(scanner.nextLine().trim());
        MenuView.exibirMensagem(produtoCtrl.adicionarProduto(sku, nome, categoria, preco, estoque));
    }

    private static void adicionarCliente() {
        System.out.print("ID do cliente: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();
        MenuView.exibirMensagem(clienteCtrl.adicionarCliente(id, nome, email));
    }

    private static void criarPedido() {
        System.out.print("ID do cliente: ");
        Cliente cliente = clienteCtrl.buscarPorId(scanner.nextLine().trim());

        if (cliente == null) {
            MenuView.exibirMensagem("Cliente não encontrado.");
            return;
        }

        List<ItemPedido> itens = new ArrayList<>();
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("SKU do produto: ");
            Produto produto = produtoCtrl.buscarPorSku(scanner.nextLine().trim());

            if (produto == null) {
                System.out.println("Produto não encontrado.");
            } else {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(scanner.nextLine().trim());
                itens.add(new ItemPedido(produto, qtd));
                System.out.println("Item adicionado: " + produto.getNome() + " x" + qtd);
            }

            System.out.print("Adicionar mais itens? (s/n): ");
            continuar = scanner.nextLine().trim();
        }

        MenuView.exibirMensagem(
                itens.isEmpty()
                        ? "Nenhum item adicionado. Pedido não criado."
                        : pedidoCtrl.criarPedido(cliente, itens)
        );
    }

    private static void reservarEstoque() {
        System.out.print("Número do pedido: ");
        MenuView.exibirMensagem(pedidoCtrl.reservarEstoque(
                Integer.parseInt(scanner.nextLine().trim())
        ));
    }

    private static void pagarPedido() {
        System.out.print("Número do pedido: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Simular pagamento aprovado? (s/n): ");
        boolean sucesso = scanner.nextLine().trim().equalsIgnoreCase("s");
        MenuView.exibirMensagem(pedidoCtrl.pagarPedido(id, sucesso));
    }

    private static void cancelarPedido() {
        System.out.print("Número do pedido: ");
        MenuView.exibirMensagem(pedidoCtrl.cancelarPedido(
                Integer.parseInt(scanner.nextLine().trim())
        ));
    }

    private static void relatorioFaturamento() {
        System.out.printf("%nFaturamento total (pedidos PAGO): R$ %.2f%n",
                relatorioCtrl.faturamentoTotal());
    }

    private static void relatorioTop3() {
        System.out.println("\n---------- TOP 3 PRODUTOS MAIS VENDIDOS ----------");
        List<Map.Entry<String, Integer>> top3 = relatorioCtrl.top3Produtos();
        if (top3.isEmpty()) {
            System.out.println("Nenhum pedido pago registrado.");
        } else {
            int[] pos = {1};
            top3.forEach(e ->
                    System.out.printf("%dº - %-20s | Quantidade: %d%n", pos[0]++, e.getKey(), e.getValue())
            );
        }
    }

    private static void relatorioCategoria() {
        System.out.println("\n---------- FATURAMENTO POR CATEGORIA ----------");
        Map<String, Double> fat = relatorioCtrl.faturamentoPorCategoria();
        if (fat.isEmpty()) {
            System.out.println("Nenhum pedido pago registrado.");
        } else {
            fat.forEach((cat, valor) ->
                    System.out.printf("%-20s : R$ %.2f%n", cat, valor)
            );
        }
    }

    private static void relatorioClientes() {
        System.out.println("\n---------- CLIENTES COM MAIS PEDIDOS ----------");
        List<Map.Entry<String, Long>> clientes = relatorioCtrl.clientesComMaisPedidos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
        } else {
            clientes.forEach(e ->
                    System.out.printf("%-20s : %d pedido(s)%n", e.getKey(), e.getValue())
            );
        }
    }
}