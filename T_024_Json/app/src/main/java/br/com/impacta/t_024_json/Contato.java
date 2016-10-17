package br.com.impacta.t_024_json;

import org.json.JSONObject;

/**
 * Created by nalmir on 15/10/2016.
 */
public class Contato {

    private static final String IDCONTATO = "idcontato";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";

    private long idcontato;
    private String nome;
    private String telefone;

    public Contato() {
        this.idcontato = -1;
        this.nome = "sem nome";
        this.telefone = "sem telefone";
    }

    public Contato(long idcontato, String nome, String telefone) {
        this.idcontato = idcontato;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato(JSONObject jsonObject) {
        try{
            this.idcontato = jsonObject.getLong(IDCONTATO);
            this.nome = jsonObject.getString(NOME);
            this.telefone = jsonObject.getString(TELEFONE);

        } catch (Exception e){
            this.idcontato = -1;
            this.nome = "sem nome";
            this.telefone = "sem telefone";
        }
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

    // ArrayAdapter possa usar essa classe
    @Override
    public String toString() {
        return this.nome;
    }

    public JSONObject toJSONObject() {
        try {
            JSONObject jsonObject = new JSONObject();
            //
            jsonObject.put(IDCONTATO, idcontato);
            jsonObject.put(NOME, nome);
            jsonObject.put(TELEFONE, telefone);
            //
            return jsonObject;

        } catch (Exception e) {
            return null;
        }
    }
}
