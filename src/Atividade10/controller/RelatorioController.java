package controller;

import model.*;
import java.util.*;
import java.util.stream.*;

public class RelatorioController {

    private List<Pedido> pedidos;

    public RelatorioController(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // Faturamento total somente de pedidos com status PAGO
    public double faturamentoTotal() {
        return pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO)
                .mapToDouble(Pedido::getTotal)
                .sum();
    }

    // Top 3 produtos mais vendidos por quantidade em pedidos PAGO
    public List<Map.Entry<String, Integer>> top3Produtos() {
        return pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO)
                .flatMap(p -> p.getItens().stream())
                .collect(Collectors.groupingBy(
                        i -> i.getProduto().getNome(),
                        Collectors.summingInt(ItemPedido::getQuantidade)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    // Faturamento agrupado por categoria em pedidos PAGO
    public Map<String, Double> faturamentoPorCategoria() {
        return pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO)
                .flatMap(p -> p.getItens().stream())
                .collect(Collectors.groupingBy(
                        i -> i.getProduto().getCategoria(),
                        Collectors.summingDouble(ItemPedido::getSubtotal)
                ));
    }

    // Clientes ordenados pelo número total de pedidos
    public List<Map.Entry<String, Long>> clientesComMaisPedidos() {
        return pedidos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCliente().getNome(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }
}