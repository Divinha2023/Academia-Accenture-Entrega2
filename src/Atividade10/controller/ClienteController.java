package controller;

import model.Cliente;
import java.util.*;

public class ClienteController {

    private Map<String, Cliente> clientes = new LinkedHashMap<>();

    public String adicionarCliente(String id, String nome, String email) {
        return clientes.containsKey(id)
                ? "Erro: ID '" + id + "' já cadastrado."
                : cadastrar(id, nome, email);
    }

    private String cadastrar(String id, String nome, String email) {
        clientes.put(id, new Cliente(id, nome, email));
        return "Cliente '" + nome + "' cadastrado com sucesso.";
    }

    public Cliente buscarPorId(String id) {
        return clientes.get(id);
    }

    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }
}