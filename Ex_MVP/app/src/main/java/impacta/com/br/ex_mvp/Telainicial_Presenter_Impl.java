package impacta.com.br.ex_mvp;

import android.content.Context;

/**
 * Created by nalmir on 19/11/2016.
 */
public class Telainicial_Presenter_Impl implements Telainicial_Presenter {

    private Context context;
    //
    private Telainicial_View mView;
    private Dao<Usuario> mModel;

    public Telainicial_Presenter_Impl(
            Context context,
            Telainicial_View mView,
            Dao<Usuario> mModel
    ) {

        this.context = context;
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void ativarCancelar() {
        // Limpeza de Banco
        // Limpeza das Estruturas de Prefenrence
        //
        mView.processoCancelar();
    }

    @Override
    public void ativarLog(String nome, String senha) {
        mView.exibirValidacao(
                "ValidacaoLogin",
                validarUsuario(nome, senha)
        );

    }

    private String validarUsuario(String nome, String senha) {
        if (nome.trim().length() == 0){
            return "Erro: Nome é Obrigatório!!! ";
        }
        //
        if (senha.trim().length() == 0){
            return "Erro: Senha é Obrigatória!!! ";
        }
        //
        if ( !nome.equalsIgnoreCase("Hugo") ||
                !senha.equals("T123")){
            return "Erro: Credenciais Invalidas!!! ";
        }
        //
        return "Credenciais Validas!!! ";
    }

    @Override
    public void processamentoBack(String titulo, String mensagem) {
        // Limpar o Banco
        // Remover diretõrio
        // Fazer Limpeza Registros velhos
        //
        mView.exibirAlertaSaida(titulo, mensagem);
    }
}
