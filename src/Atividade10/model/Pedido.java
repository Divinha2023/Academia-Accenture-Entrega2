package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contadorId = 1;

    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private double desconto;
    private double total;

    public Pedido(Cliente cliente) {
        this.id       = contadorId++;
        this.cliente  = cliente;
        this.itens    = new ArrayList<>();
        this.status   = StatusPedido.PENDENTE;
        this.desconto = 0;
        this.total    = 0;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    // Aplica 10% de desconto se o subtotal ultrapassar R$ 500
    public double calcularTotal() {
        double subtotal = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
        this.desconto   = subtotal > 500 ? subtotal * 0.10 : 0;
        this.total      = subtotal - desconto;
        return total;
    }

    public int getId()                  { return id; }
    public Cliente getCliente()         { return cliente; }
    public List<ItemPedido> getItens()  { return itens; }
    public StatusPedido getStatus()     { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public double getDesconto()         { return desconto; }
    public double getTotal()            { return total; }
}