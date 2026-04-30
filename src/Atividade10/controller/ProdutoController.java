package controller;

import model.Produto;
import java.util.*;

public class ProdutoController {

    private Map<String, Produto> produtos = new LinkedHashMap<>();

    public String adicionarProduto(String sku, String nome, String categoria, double preco, int estoque) {
        return produtos.containsKey(sku)
                ? "Erro: SKU '" + sku + "' já cadastrado."
                : cadastrar(sku, nome, categoria, preco, estoque);
    }

    private String cadastrar(String sku, String nome, String categoria, double preco, int estoque) {
        produtos.put(sku, new Produto(sku, nome, categoria, preco, estoque));
        return "Produto '" + nome + "' cadastrado com sucesso.";
    }

    public List<Produto> listarPorSku() {
        List<Produto> lista = new ArrayList<>(produtos.values());
        lista.sort(Comparator.comparing(Produto::getSku));
        return lista;
    }

    public List<Produto> listarPorPreco() {
        List<Produto> lista = new ArrayList<>(produtos.values());
        lista.sort(Comparator.comparingDouble(Produto::getPreco));
        return lista;
    }

    public Produto buscarPorSku(String sku) {
        return produtos.get(sku);
    }
}