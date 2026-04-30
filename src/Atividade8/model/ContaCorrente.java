package Atividade8.model;

public class ContaCorrente {

    private int numero;
    private double saldo;
    private String data;
    private Cliente cliente;

    public ContaCorrente(int numero, double saldo, String data, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.data = data;
        this.cliente = cliente;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transferir(double valor, ContaCorrente destino) {
        if (saldo - valor < 0) {
            return false;
        }
        saldo -= valor;
        destino.saldo += valor;
        return true;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }
}