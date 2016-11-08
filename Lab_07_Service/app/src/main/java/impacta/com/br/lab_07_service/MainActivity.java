package impacta.com.br.lab_07_service;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private EditText et_contagem;
    private TextView tv_contagem;
    //
    private Button btn_ativar;

    private String mensagem;

    private ContadorReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel(savedInstanceState);
        inicializarAcao();
        //
        receiver = new ContadorReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constantes.EVENTO);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        //
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        //
        super.onDestroy();
    }

    private void inicializarVariavel(Bundle savedInstanceState) {
        context = getBaseContext();
        //
        et_contagem = (EditText) findViewById(R.id.et_contagem);
        tv_contagem = (TextView) findViewById(R.id.tv_contagem);
        //
        btn_ativar = (Button) findViewById(R.id.btn_ativar);
        //
        if (savedInstanceState != null){
            et_contagem.setText(
                    savedInstanceState.getString(STATUS_ET)
            );
            //
            tv_contagem.setText(
                    savedInstanceState.getString(STATUS_TV)

            );
            //
            btn_ativar.setEnabled(savedInstanceState.getBoolean(STATUS_BT));
        }
    }

    private void inicializarAcao() {
        btn_ativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacao()) {
                    ativarServico();
                } else {
                    exirbirErro();
                }
            }
        });
    }

    private boolean validacao() {
        mensagem = "";
        //
        if (et_contagem.getText().toString().trim().length() == 0) {
            mensagem = "Erro: Contagem é Obrigatório!!!";
            //
            return false;
        }
        //
        try {

            Integer.parseInt(et_contagem.getText().toString());

        } catch (Exception e) {
            mensagem = "Erro: Número Inválido!!!";
            //
            return false;
        }
        //
        return true;
    }

    private void ativarServico() {
        btn_ativar.setEnabled(false);
        //
        Intent mIntent = new Intent(context, ContadorService.class);
        mIntent.putExtra(
                Constantes.PARAMETRO,
                Integer.parseInt(
                        et_contagem.getText().toString()
                )
        );
        //
        startService(mIntent);
    }

    private void exirbirErro() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(R.drawable.alert);
        alerta.setTitle("Validacao");
        alerta.setMessage(mensagem);
        alerta.setPositiveButton("Ok", null);
        //
        alerta.show();
    }

    private class ContadorReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int tipo;
            int indice;
            int repeticao;

            Bundle bundle = intent.getExtras();
            //
            if (bundle != null) {
                tipo = bundle.getInt(Constantes.TIPO);
                indice = bundle.getInt(Constantes.VALOR);
                repeticao = bundle.getInt(Constantes.VALOR_REPETICAO);
            } else {
                tipo = Constantes.TIPO_FIM_PROCESSO;
                indice = -1;
                repeticao = -1;
            }
            //
            analisarRetorno(tipo, indice, repeticao);
        }
    }

    private void analisarRetorno(int tipo, int indice, int repeticao) {
        switch (tipo){
            case Constantes.TIPO_ATUALIZAR:
                et_contagem.setText(String.valueOf(repeticao));
                tv_contagem.setText(String.valueOf(indice));
                btn_ativar.setEnabled(false);
                //
                break;
            case Constantes.TIPO_FIM_PROCESSO:
                btn_ativar.setEnabled(true);
                //
                break;
            default:
                tv_contagem.setText("Nao Implementado");
                btn_ativar.setEnabled(true);
                //
                break;
        }
    }


    // Recuperacao Status da Tela
    private static final String STATUS_ET = "status_et";
    private static final String STATUS_TV = "status_tv";
    private static final String STATUS_BT = "status_bt";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATUS_ET, et_contagem.getText().toString());
        outState.putString(STATUS_TV, tv_contagem.getText().toString());
        outState.putBoolean(STATUS_BT, btn_ativar.isEnabled());
        //
        super.onSaveInstanceState(outState);
    }
}
