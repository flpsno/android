package br.com.impacta.t_025_http;

/**
 * Created by nalmir on 15/10/2016.
 */
public class Contato {

    private long idcontato;
    private String nome;

    public Contato() {
        this.idcontato = -1;
        this.nome = "sem nome";
    }

    public Contato(long idcontato, String nome) {
        this.idcontato = idcontato;
        this.nome = nome;
    }

    public long getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(long idcontato) {
        this.idcontato = idcontato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
