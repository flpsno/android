package impacta.com.br.ex_mvp;

/**
 * Created by nalmir on 19/11/2016.
 */
public interface Telainicial_Presenter {

    void ativarCancelar();

    void ativarLog(String nome, String senha);

    void processamentoBack(String titulo, String mensagem);

}
