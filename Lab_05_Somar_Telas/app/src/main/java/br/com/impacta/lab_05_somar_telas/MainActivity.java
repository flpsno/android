package br.com.impacta.lab_05_somar_telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int PROCESSO_DE_SOMAR = 10;

    private Context context;
    //
    private EditText et_x;
    private EditText et_y;
    private TextView tv_soma;
    private Button btn_somar;

    private String mensagemErro;

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
        et_x = (EditText) findViewById(R.id.telainicial_et_x);
        et_y = (EditText) findViewById(R.id.telainicial_et_y);
        //
        tv_soma = (TextView) findViewById(R.id.telainicial_tv_soma);
        //
        btn_somar = (Button) findViewById(R.id.telainicial_btn_somar);
    }

    private void inicializarAcao() {
        btn_somar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    chamarSoma();
                } else {
                    exibirMensagemErro();
                }
            }
        });
    }

    private boolean validacao() {
        int x = converterValores(et_x.getText().toString().trim());
        int y = converterValores(et_y.getText().toString().trim());
        //
        if (x < 0){
            mensagemErro = "Erro: x é Obrigatorio ou esta Invalido";
            //
            return false;
        }
        //
        if (y < 0){
            mensagemErro = "Erro: y é Obrigatorio ou esta Invalido";
            //
            return false;
        }
        //
        return true;
    }

    private int converterValores(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return -1;
        }
    }

    private void chamarSoma() {
        Intent mIntent = new Intent(context, SegundaTela.class);
        mIntent.putExtra(Constantes.PARAMETRO_X, Integer.parseInt(et_x.getText().toString()));
        mIntent.putExtra(Constantes.PARAMETRO_Y, Integer.parseInt(et_y.getText().toString()));
        //
        startActivityForResult(mIntent, PROCESSO_DE_SOMAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PROCESSO_DE_SOMAR:
                processarRetorno(resultCode, data);
                break;
            default:
                break;
        }


    }

    private void processarRetorno(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int resultado = data.getIntExtra(Constantes.PARAMETRO_SOMA, 0);
            //
            tv_soma.setText(String.valueOf(resultado));
        } else {
            tv_soma.setText("Cancelado!!!");
        }

    }

    private void exibirMensagemErro() {
        Toast.makeText(
                context,
                mensagemErro,
                Toast.LENGTH_SHORT
        ).show();
    }


}
