package impacta.com.br.a_final.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nalmir on 26/11/2016.
 */
public class Paciente {

    @SerializedName("codigo")
    private long idpaciente;
    private String nome;

    public long getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(long idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
