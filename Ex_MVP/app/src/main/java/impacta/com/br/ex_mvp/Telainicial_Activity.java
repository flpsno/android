package impacta.com.br.ex_mvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Telainicial_Activity extends AppCompatActivity implements Telainicial_View{

    private Context context;
    //
    private EditText et_nome;
    private EditText et_senha;
    //
    private Button btn_cancelar;
    private Button btn_log;

    private Telainicial_Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        mPresenter = new Telainicial_Presenter_Impl(
                context,
                this,
                new UsuarioDao()
        );
        //
        et_nome = (EditText)
                findViewById(R.id.telainicial_et_nome);
        et_senha = (EditText)
                findViewById(R.id.telainicial_et_senha);
        //
        btn_cancelar = (Button)
                findViewById(R.id.telainicial_btn_cancelar);
        btn_log = (Button)
                findViewById(R.id.telainicial_btn_log);
    }

    private void inicializarAcao() {
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.ativarCancelar();
                // Limpa a Tela
                //processoCancelar();
            }
        });
        //
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.ativarLog(
                        et_nome.getText().toString(),
                        et_senha.getText().toString()
                );
            }
        });
    }

    @Override
    public void onBackPressed() {
        mPresenter.processamentoBack("Saida do Sistema", "Deseja realmente Sair do Sistema?");
        //exibirAlertaSaida("Saida do Sistema", "Deseja realmente Sair do Sistema?");
    }

    @Override
    public void exibirValidacao(String titulo, String mensagem) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setPositiveButton("Ok", null);
        //
        alerta.show();
    }

    @Override
    public void exibirAlertaSaida(String titulo, String mensagem) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        //
        alerta.setNegativeButton("Nao", null);
        //
        alerta.show();
    }

    @Override
    public void processoCancelar() {
        et_nome.setText("");
        et_senha.setText("");
        //
        et_nome.requestFocus();
    }
}
