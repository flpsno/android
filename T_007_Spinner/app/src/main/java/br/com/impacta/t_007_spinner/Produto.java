package br.com.impacta.t_007_spinner;

/**
 * Created by nalmir on 27/08/2016.
 */
public class Produto extends Object{

    private long idproduto;
    private String nome;
    private double preco;

    public Produto() {
        this.idproduto = -1;
        this.nome = "sem nome";
        this.preco = 0;
    }

    public Produto(long idproduto, String nome, double preco) {
        this.idproduto = idproduto;
        this.nome = nome;
        this.preco = preco;
    }

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
