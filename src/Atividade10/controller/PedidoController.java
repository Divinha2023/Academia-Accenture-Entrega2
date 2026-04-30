package controller;

import model.*;
import java.util.*;

public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String criarPedido(Cliente cliente, List<ItemPedido> itens) {
        String erroEstoque = verificarEstoque(itens);
        return erroEstoque != null ? erroEstoque : criar(cliente, itens);
    }

    private String verificarEstoque(List<ItemPedido> itens) {
        return itens.stream()
                .filter(i -> i.getProduto().getEstoque() < i.getQuantidade())
                .findFirst()
                .map(i -> "Estoque insuficiente para: " + i.getProduto().getNome())
                .orElse(null);
    }

    private String criar(Cliente cliente, List<ItemPedido> itens) {
        Pedido pedido = new Pedido(cliente);
        itens.forEach(pedido::adicionarItem);
        pedido.calcularTotal();
        pedidos.add(pedido);
        return String.format(
                "Pedido #%d criado. Subtotal: R$ %.2f | Desconto: R$ %.2f | Total: R$ %.2f",
                pedido.getId(),
                pedido.getTotal() + pedido.getDesconto(),
                pedido.getDesconto(),
                pedido.getTotal()
        );
    }

    public String reservarEstoque(int idPedido) {
        Pedido pedido = buscarPorId(idPedido);
        return pedido == null                                  ? "Pedido #" + idPedido + " não encontrado."
                : pedido.getStatus() != StatusPedido.PENDENTE     ? "Pedido #" + idPedido + " não está PENDENTE."
                  : reservar(pedido);
    }

    private String reservar(Pedido pedido) {
        pedido.getItens().forEach(item ->
                item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantidade())
        );
        pedido.setStatus(StatusPedido.RESERVADO);
        return "Estoque reservado para o Pedido #" + pedido.getId() + ".";
    }

    public String pagarPedido(int idPedido, boolean sucesso) {
        Pedido pedido = buscarPorId(idPedido);
        return pedido == null                                  ? "Pedido #" + idPedido + " não encontrado."
                : pedido.getStatus() != StatusPedido.RESERVADO    ? "Pedido #" + idPedido + " não está RESERVADO."
                  : processar(pedido, sucesso);
    }

    private String processar(Pedido pedido, boolean sucesso) {
        pedido.setStatus(sucesso ? StatusPedido.PAGO : StatusPedido.FALHOU);
        return sucesso
                ? String.format("Pagamento aprovado! Pedido #%d marcado como PAGO. Total: R$ %.2f",
                pedido.getId(), pedido.getTotal())
                : "Pagamento recusado. Pedido #" + pedido.getId() + " marcado como FALHOU.";
    }

    public String cancelarPedido(int idPedido) {
        Pedido pedido = buscarPorId(idPedido);
        return pedido == null ? "Pedido #" + idPedido + " não encontrado."
                : (pedido.getStatus() != StatusPedido.PENDENTE && pedido.getStatus() != StatusPedido.RESERVADO)
                  ? "Pedido #" + idPedido + " não pode ser cancelado. Status atual: " + pedido.getStatus()
                  : cancelar(pedido);
    }

    private String cancelar(Pedido pedido) {
        boolean eraReservado = pedido.getStatus() == StatusPedido.RESERVADO;
        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.getItens().forEach(item ->
                item.getProduto().setEstoque(
                        eraReservado
                                ? item.getProduto().getEstoque() + item.getQuantidade()
                                : item.getProduto().getEstoque()
                )
        );
        return "Pedido #" + pedido.getId() + " cancelado."
                + (eraReservado ? " Estoque liberado." : "");
    }

    public Pedido buscarPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}