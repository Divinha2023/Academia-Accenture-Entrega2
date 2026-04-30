package model;

public class Produto {

    private String sku;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String sku, String nome, String categoria, double preco, int estoque) {
        this.sku = sku;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getSku()          { return sku; }
    public String getNome()         { return nome; }
    public String getCategoria()    { return categoria; }
    public double getPreco()        { return preco; }
    public int getEstoque()         { return estoque; }
    public void setEstoque(int e)   { this.estoque = e; }

    @Override
    public String toString() {
        return String.format(
                "SKU: %-10s | Nome: %-20s | Categoria: %-15s | Preço: R$ %8.2f | Estoque: %d",
                sku, nome, categoria, preco, estoque
        );
    }
}